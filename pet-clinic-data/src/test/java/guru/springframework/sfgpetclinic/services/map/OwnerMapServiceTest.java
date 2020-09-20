package guru.springframework.sfgpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.*;

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
		
	@BeforeEach
	void setUp() {
		ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
		
	}
	
	@Test
	void findAll() {
		
	}
	
	@Test
	void findById() {
		
	}
	
	@Test
	void save() {
		
	}
	
	@Test
	void delete() {
		
	}
	
	@Test
	void deleteById() {
		
	}
	
	@Test
	void findByLastName() {
		
	}
}

