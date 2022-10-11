package guru.springframework.sfgpetclinic.controller;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController
{
    private final OwnerService ownerService;
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    public OwnerController(final OwnerService ownerService)
    {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder)
    {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"","/", "/index", "/index.html"})
    public String getOwners(Model model)
    {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model)
    {
        if(owner.getLastName() == null)
        {
            owner.setLastName("");
        }
        List<Owner> results = ownerService.findByLastNameLike("%"  + owner.getLastName() + "%");
        if(results.isEmpty())
        {
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        }
        else if(results.size() == 1)
        {
            owner = results.iterator().next();
            return "redirect:/owners/" + owner.getId();
        }
        else
        {
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    @RequestMapping({"/find",})
    public String findOwners(Model model)
    {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping({"/{ownerId}"})
    public ModelAndView showOwner(@PathVariable("ownerId") long ownerId)
    {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(this.ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping({"/new"})
    public String initCreationForm(Model model)
    {
        model.addAttribute("owner", Owner.builder().build());
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(Owner owner, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }
        final Owner savedOwner = this.ownerService.save(owner);
        return "redirect:/owners/" + savedOwner.getId();
    }

    @GetMapping("{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable("ownerId") String ownerId, Model model)
    {
        Owner foundOwner = this.ownerService.findById(Long.valueOf(ownerId));
        model.addAttribute("owner", foundOwner);
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerFor(Owner owner, BindingResult result,
                                        @PathVariable("ownerId") String ownerId)
    {
        if(result.hasErrors())
        {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }
        owner.setId(Long.valueOf(ownerId));
        final Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/" + savedOwner.getId();
    }
}
