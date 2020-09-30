package guru.springframework.sfgpetclinic.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;

/**
 * Created by tairovich_jr on Sep 30, 2020
 */
@ExtendWith(MockitoExtension.class)
public class PetControllerTest {

	
	@Mock
	PetService petService;
	
	@Mock
	OwnerService ownerService;
	
	@Mock
	PetTypeService petTypeService;
	
	@InjectMocks
	PetController controller;
	
	MockMvc mockMvc;
	
	Owner owner;
	Set<PetType> petTypes;
	
	@BeforeEach
	public void setUp() {
		owner = Owner.builder().id(1L).build();
		
		petTypes = new HashSet<>();
		petTypes.add(PetType.builder().id(1L).name("Dog").build());
		petTypes.add(PetType.builder().id(2L).name("Cat").build());
		
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	
	@Test
	public void initCreationForm() throws Exception{
		when(ownerService.findById(anyLong())).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypes);
		
		 mockMvc.perform(get("/owners/1/pets/new"))
         			.andExpect(status().isOk())
         			.andExpect(model().attributeExists("owner"))
         			.andExpect(model().attributeExists("pet"))
         			.andExpect(view().name("pets/createOrUpdatePetForm"));
	}
	
	@Test
	public void processCreationForm() throws Exception {
		when(ownerService.findById(anyLong())).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypes);
		
		mockMvc.perform(post("/owners/1/pets/new"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/1"));
		
		verify(petService).save(any());
		
	}
	
	@Test
	public void initUpdateForm() throws Exception{
		when(ownerService.findById(anyLong())).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypes);
		when(petService.findById(anyLong())).thenReturn(Pet.builder().id(2L).build());
		
		mockMvc.perform(get("/owners/1/pets/2/edit"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("owner"))
				.andExpect(model().attributeExists("pet"))
				.andExpect(view().name("pets/createOrUpdatePetForm"));
	}
	
	@Test
	public void processUpdateForm() throws Exception {
		when(ownerService.findById(anyLong())).thenReturn(owner);
		when(petTypeService.findAll()).thenReturn(petTypes);
		
		mockMvc.perform(post("/owners/1/pets/2/edit"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/1"));
		
		verify(petService, times(1)).save(any());
	}
	
	
	
	
	
	
	
}
