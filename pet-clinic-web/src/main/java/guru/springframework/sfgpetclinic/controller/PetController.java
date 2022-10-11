package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@Controller
public class PetController
{
    private final PetService petService;
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;
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
}
