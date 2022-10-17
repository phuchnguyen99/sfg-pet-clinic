package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

@Controller
public class VisitController
{
    private final VisitService visitService;
    private final PetService petService;
    private static final String VIEWS_VISIT_CREATE_OR_UPDATE_FORM = "pets/createOrUpdateVisitForm";
    public VisitController(final VisitService visitService,
                           final PetService petService)
    {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void setDataBinder(WebDataBinder dataBinder)
    {
        dataBinder.setDisallowedFields("id");
        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text)
            {
                setValue(LocalDate.parse(text));
            }
        });
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable("petId") long petId, Model model)
    {
        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);
        Visit visit = new Visit();
        pet.addVisit(visit);
        visit.setPet(pet);
        return visit;
    }

    @GetMapping("owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable("petId") long petId, Model model)
    {
        return VIEWS_VISIT_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@PathVariable("petId") long petId, @PathVariable("ownerId") long ownerId,
                                      Visit visit, BindingResult result)
    {
        if(result.hasErrors())
        {
            return VIEWS_VISIT_CREATE_OR_UPDATE_FORM;
        }
        visitService.save(visit);
        return "redirect:/owners/" + ownerId;
    }

}
