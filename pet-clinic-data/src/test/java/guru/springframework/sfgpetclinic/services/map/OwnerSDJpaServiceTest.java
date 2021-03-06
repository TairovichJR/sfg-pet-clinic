package guru.springframework.sfgpetclinic.services.map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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


import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.springdatajpa.OwnerSDJpaService;

/**
 * Created by tairovich_jr on Sep 21, 2020
 */
@ExtendWith(MockitoExtension.class)
public class OwnerSDJpaServiceTest {

	public static final String LAST_NAME = "Smith";
	
	@Mock
	OwnerRepository ownerRepository;
	@Mock
	PetRepository petRepository;
	@Mock
	PetTypeRepository petTypeRepository;
	
	@InjectMocks
	OwnerSDJpaService service;
	
	Owner returnOwner;
	
	@BeforeEach
	public void setUp() {
		returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
	}
	
	@Test
	public void findByLastName() {
		
		when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
		
		Owner smith = service.findByLastName(LAST_NAME);
		
		assertEquals(LAST_NAME, smith.getLastName());
		verify(ownerRepository).findByLastName(any());
	}

	@Test
	public void findAll() {
		Set<Owner> returnOwnerSet = new HashSet<>();
		returnOwnerSet.add(Owner.builder().id(1L).build());
		returnOwnerSet.add(Owner.builder().id(2L).build());
		
		when(ownerRepository.findAll()).thenReturn(returnOwnerSet);
		
		Set<Owner> owners = service.findAll();
		assertNotNull(owners);
		assertEquals(2, owners.size());
		
	}

	@Test
	public void findById() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
		
		Owner owner = service.findById(1L);
		assertNotNull(owner);
	}

	@Test
	public void findByIdNotFound() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		Owner owner = service.findById(1L);
		assertNull(owner);
	}
	
	@Test
	public void save() {
		Owner ownerToSave = Owner.builder().id(1L).build();
		
		when(ownerRepository.save(any())).thenReturn(returnOwner);
		
		Owner savedOwner = service.save(ownerToSave);
		assertNotNull(savedOwner);
		verify(ownerRepository).save(any());
	}

	@Test
	public void delete() {
		service.delete(returnOwner);
		verify(ownerRepository, times(1)).delete(any());
		
	}

	@Test
	public void deleteById() {
		service.deleteById(1L);
		verify(ownerRepository).deleteById(anyLong());
	}

}
