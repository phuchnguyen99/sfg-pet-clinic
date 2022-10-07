package guru.spring.framework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.controller.OwnerController;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
public class OwnerControllerTest
{
    @Mock
    private OwnerService ownerService;

    @InjectMocks
    OwnerController controller;

    Set<Owner> owners;
    MockMvc mockMvc;

    @BeforeEach
    public void setUp()
    {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void findOwners() throws Exception
    {
        mockMvc.perform(get("/owners/find"))
               .andExpect(status().isOk())
               .andExpect(view().name("owners/findOwners"))
               .andExpect(model().attributeExists("owner"));
        verifyNoInteractions(ownerService);
    }

    @Test
    public void processFindReturnMany() throws Exception
    {
        when(ownerService.findByLastNameLike(anyString())).thenReturn(Arrays.asList(Owner.builder().id(1L).build(), Owner.builder().id(2L).build()));
        mockMvc.perform(get("/owners"))
               .andExpect(status().isOk())
               .andExpect(view().name("owners/ownersList"))
               .andExpect(model().attribute("owner", instanceOf(Owner.class)));
    }

    @Test
    public void processFindReturnOne() throws Exception
    {
        when(ownerService.findByLastNameLike(anyString())).thenReturn(Arrays.asList(Owner.builder().id(1L).build()));
        mockMvc.perform(get("/owners"))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    public void displayOwner() throws Exception
    {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());
        mockMvc.perform(get("/owners/123")).andExpect(status().isOk())
               .andExpect(view().name("owners/ownerDetails"))
               .andExpect(model().attribute("owner", hasProperty("id", is(1L))));
    }
}
