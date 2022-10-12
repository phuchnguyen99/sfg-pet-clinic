package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController
{
    private final PetService petService;
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;
    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    public PetController(final PetService petService, final OwnerService ownerService,
                         final PetTypeService petTypeService)
    {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetType()
    {
        return this.petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") String ownerId)
    {
        Owner foundOwner = ownerService.findById(Long.valueOf(ownerId));
        return foundOwner;
    }

    @InitBinder("owner")
    public void initOwnerBuild(WebDataBinder dataBinder)
    {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner, ModelMap modelMap)
    {
        Pet pet = Pet.builder().build();
        pet.setOwner(owner);
        owner.getPets().add(pet);
        modelMap.put("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, Pet pet, BindingResult result, ModelMap modelMap)
    {
        if(StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null)
        {
            result.rejectValue("name", "duplicate", "already exists");
        }

        owner.getPets().add(pet);
        if(result.hasErrors())
        {
            modelMap.put("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }
        petService.save(pet);
        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/" + savedOwner.getId();
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model modelMap)
    {
        Pet pet = this.petService.findById(petId);
        modelMap.addAttribute("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(Pet pet, BindingResult result, Owner owner, Model model)
    {
        if(result.hasErrors())
        {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }
        owner.addPet(pet);
        petService.save(pet);
        return "redirect:/owners/" + owner.getId();
    }
}
