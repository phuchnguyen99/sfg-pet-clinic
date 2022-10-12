package guru.spring.framework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.controller.PetController;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
public class PetControllerShould
{
    @Mock
    private PetService petService;
    @Mock
    private OwnerService ownerService;
    @Mock
    private PetTypeService petTypeService;
    @InjectMocks
    PetController petController;
    MockMvc mockMvc;
    Owner owner;
    Set<PetType> petTypes;

    @BeforeEach
    public void setUp()
    {
        owner = Owner.builder().id(1L).build();
        petTypes = new HashSet<>();
        petTypes.add(PetType.builder().name("Dog").id(1L).build());
        petTypes.add(PetType.builder().name("Cat").id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
    public void initCreationForm() throws Exception
    {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(get("/owners/1/pets/new"))
               .andExpect(status().isOk())
               .andExpect(model().attributeExists("owner"))
               .andExpect(model().attributeExists("pet"))
               .andExpect(view().name("pets/createOrUpdatePetForm"));
    }

    @Test
    public void processCreationForm() throws Exception
    {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(post("/owners/1/pets/new"))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/owners/1"));
        verify(petService).save(any());
    }

    @Test
    public void initUpdateForm() throws Exception
    {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(get("/owners/1/pets/1/edit"))
               .andExpect(status().isOk())
               .andExpect(model().attribute("owner", hasProperty("id", is(1L))))
               .andExpect(view().name("pets/createOrUpdatePetForm"));
    }

    @Test
    public void processUpdateForm() throws Exception
    {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);
        mockMvc.perform(post("/owners/1/pets/2/edit"))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/owners/1"));
    }
}
