package guru.spring.framework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.controller.VisitController;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
public class VisitControllerTest
{
    private static final String PETS_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";
    private static final String REDIRECT_OWNER_1 = "redirect:/owners/1";
    private final UriTemplate visitsUriTemplate = new UriTemplate("/owners/{ownerId}/pets/{petId}/visits/new");
    @Mock
    private PetService petService;
    @Mock
    private VisitService visitService;
    @InjectMocks
    private VisitController visitController;

    private MockMvc mockMvc;
    private final Map<String, String> uriVariables = new HashMap<>();
    private URI visitUri;

    @BeforeEach
    public void setUp()
    {
        when(petService.findById(anyLong())).thenReturn(
            Pet.builder().id(1L).birthDate(LocalDate.of(2022, 10, 17))
                        .name("Cutie").visits(new HashSet<>()).petType(PetType.builder().name("Dog").build())
                        .owner(Owner.builder().id(1L).firstName("Doe").lastName("Joe").build()).build());
        uriVariables.put("ownerId", new Long(1L).toString());
        uriVariables.put("petId", new Long(1L).toString());
        visitUri = visitsUriTemplate.expand(uriVariables);
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @AfterEach
    public void tearDown()
    {
        uriVariables.clear();
    }

    @Test
    public void initNewVistForm() throws Exception
    {
        mockMvc.perform(get(visitUri))
               .andExpect(status().isOk())
               .andExpect(view().name(PETS_CREATE_OR_UPDATE_VISIT_FORM));
    }


    @Test
    public void processNewVisitForm() throws Exception
    {
        mockMvc.perform(post(visitUri)
                        .param("date", "2022-11-19"))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name(REDIRECT_OWNER_1))
               .andExpect(model().attributeExists("visit"));
    }
}
