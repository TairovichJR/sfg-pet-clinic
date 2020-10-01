package guru.springframework.sfgpetclinic.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import java.time.LocalDate;
import java.util.HashSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;

/**
 * Created by tairovich_jr on Oct 01, 2020
 */
@ExtendWith(MockitoExtension.class)
public class VisitControllerTest {

	private static final String PETS_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";
	private static final String REDIRECT_OWNERS_1 = "redirect:/owners/{ownerId}";
	private static final String YET_ANOTHER_VISIT_DESCRIPTION = "yet another visit";

	@Mock
	VisitService visitService;

	@Mock
	PetService petService;

	@InjectMocks
	VisitController controller;

	MockMvc mockMvc;

	private final String VISIT_URI = "/owners/1/pets/1/visits/new";
	
	@BeforeEach
	public void setUp() {
		Long petId = 1L;
        Long ownerId = 1L;
        when(petService.findById(anyLong()))
                .thenReturn(
                        Pet.builder()
                            .id(petId)
                            .birtDate(LocalDate.of(2018,11,13))
                            .name("Cutie")
                            .visits(new HashSet<>())
                            .owner(Owner.builder()
		                                .id(ownerId)
		                                .lastName("Doe")
		                                .firstName("Joe")
		                                .build())
                            .petType(PetType.builder()
                            			.name("Dog").build())
                            .build()
                );
 
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build(); 
	}

	@Test
	public void initNewVisitForm() throws Exception{
		mockMvc.perform(get(VISIT_URI))
				.andExpect(status().isOk())
				.andExpect(view().name(PETS_CREATE_OR_UPDATE_VISIT_FORM));
	}
	
	@Test
	public void processNewVisitForm() throws Exception{
		
		mockMvc.perform(post(VISIT_URI)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("date", "2020-11-13")
				.param("description", YET_ANOTHER_VISIT_DESCRIPTION))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name(REDIRECT_OWNERS_1))
			.andExpect(model().attributeExists("visit"));
	}
}