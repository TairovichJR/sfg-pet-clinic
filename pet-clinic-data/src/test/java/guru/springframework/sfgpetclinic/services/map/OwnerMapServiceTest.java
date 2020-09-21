package guru.springframework.sfgpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.mapImpl.OwnerMapService;
import guru.springframework.sfgpetclinic.services.mapImpl.PetMapService;
import guru.springframework.sfgpetclinic.services.mapImpl.PetTypeMapService;

/**
 * Created by tairovich_jr on Sep 20, 2020
 */
class OwnerMapServiceTest {

	OwnerMapService ownerMapService;
	final Long ownerId = 1L;
	final String lastName = "Smith";

	@BeforeEach
	void setUp() {
		ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
		ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
	}
	
	@Test
	void findAll() {
		Set<Owner> ownerSet = ownerMapService.findAll();
		assertEquals(ownerId, ownerSet.size());
	}
	
	@Test
	void findById() {
		Owner owner = ownerMapService.findById(ownerId);
		assertEquals(ownerId, owner.getId());
	}
	
	@Test
	void saveExistingId() {
		
		Long id2 = 2L;
		Owner owner2 = Owner.builder().id(id2).build();
		Owner savedOwner = ownerMapService.save(owner2);
		assertEquals(id2, savedOwner.getId());
	}
	
	@Test
	void saveNoId() {
		Owner owner = Owner.builder().build();
		Owner savedOwner = ownerMapService.save(owner);
		assertNotNull(savedOwner);
		assertNotNull(savedOwner.getId());
	}
	
	@Test
	void delete() {
		ownerMapService.delete(ownerMapService.findById(ownerId));
		assertEquals(0, ownerMapService.findAll().size());
	}
	
	@Test
	void deleteById() {
		ownerMapService.deleteById(ownerId);
		assertEquals(0, ownerMapService.findAll().size());
	}
	
	@Test
	void findByLastName() {
		Owner owner = ownerMapService.findByLastName(lastName);
		assertNotNull(owner);
		assertEquals(ownerId, owner.getId());
	}
	
	@Test
	void findByLastNameNotFound() {
		Owner owner = ownerMapService.findByLastName("foo");
		assertNull(owner);
	}
	
}

