package guru.springframework.sfgpetclinic.services.map;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.services.springdatajpa.PetSDJpaService;

/**
 * Created by tairovich_jr on Sep 22, 2020
 */

@ExtendWith(MockitoExtension.class)
public class PetSDJpaServiceTest {

	public static final String NAME = "Rosco";
	
	@Mock
	PetRepository petRepository;
	
	@InjectMocks
	PetSDJpaService service;
	
	Pet returnPet;
	
	@BeforeEach
	public void setUp() {
		returnPet = Pet.builder().id(1L).name(NAME).build();
	}
	
	@Test
	public void findAll() {
		Set<Pet> returnPets = new HashSet<>();
		returnPets.add(Pet.builder().id(1L).build());
		returnPets.add(Pet.builder().id(2L).build());
		
		when(petRepository.findAll()).thenReturn(returnPets);
		
		Set<Pet> pets = service.findAll();
		
		assertNotNull(pets);
		assertEquals(2, pets.size());
	}

	@Test
	public void findById() {
		when(petRepository.findById(anyLong())).thenReturn(Optional.of(returnPet));
		Pet pet = service.findById(1L);
		assertNotNull(pet);
		assertEquals(1L, pet.getId());
	}

	@Test
	public void save() {
		Pet petToSave = Pet.builder().id(1L).name(NAME).build();
		when(petRepository.save(any())).thenReturn(returnPet);
		
		Pet savedPet = service.save(petToSave);
		
		assertNotNull(savedPet);
		assertEquals(NAME, savedPet.getName());
		verify(petRepository,times(1)).save(any());
	}

	@Test
	public void delete() {
		service.delete(returnPet);
		verify(petRepository,timeout(1)).delete(any());
	}

	@Test
	public void deleteById() {
		service.deleteById(1L);
		verify(petRepository).deleteById(anyLong());
	}

	
	
	
	
}
