package guru.springframework.sfgpetclinic.services.map;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.springdatajpa.PetTypeSDJpaService;

/**
 * Created by tairovich_jr on Sep 22, 2020
 */
@ExtendWith(MockitoExtension.class)
public class PetTypeSDJpaServiceTest {

	public static final String NAME = "Dog";
	
	@Mock
	PetTypeRepository petTypeRepository;
	
	@InjectMocks
	PetTypeSDJpaService service;
	
	PetType returnPetType;
	
	@BeforeEach
	public void setUp() {
		returnPetType  = PetType.builder().id(1L).name(NAME).build();
	}
	
	@Test
	public void findAll() {
		Set<PetType> returnPetTypes = new HashSet<>();
		returnPetTypes.add(PetType.builder().id(1L).build());
		returnPetTypes.add(PetType.builder().id(2L).build());
		
		when(petTypeRepository.findAll()).thenReturn(returnPetTypes);
		
		Set<PetType> petTypes = service.findAll();
		
		assertEquals(2, petTypes.size());
	}

	@Test
	public void findById() {
		when(petTypeRepository.findById(anyLong())).thenReturn(Optional.of(returnPetType));
		
		PetType petType = service.findById(1L);
		assertNotNull(petType);
		assertEquals(1L, petType.getId());
		assertEquals(NAME, petType.getName());
	}

	@Test
	public void save() {
		PetType petTypeToSave = PetType.builder().id(1L).name(NAME).build();
		when(petTypeRepository.save(any())).thenReturn(returnPetType);
		
		PetType savedPetType = service.save(petTypeToSave);
		
		assertNotNull(savedPetType);
		assertEquals(1L, savedPetType.getId());
		assertEquals(NAME, savedPetType.getName());
	}

	@Test
	public void delete() {
		service.delete(returnPetType);
		verify(petTypeRepository,times(1)).delete(any());
	}

	@Test
	public void deleteById() {
		service.deleteById(1L);
		verify(petTypeRepository, times(1)).deleteById(anyLong());
	}
}
