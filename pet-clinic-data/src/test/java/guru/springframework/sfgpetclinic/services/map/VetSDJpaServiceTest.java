package guru.springframework.sfgpetclinic.services.map;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.repositories.VetRepository;
import guru.springframework.sfgpetclinic.services.springdatajpa.VetSDJpaService;

/**
 * Created by tairovich_jr on Sep 22, 2020
 */
@ExtendWith(MockitoExtension.class)
public class VetSDJpaServiceTest {

	private static final String FIRST_NAME = null;

	@Mock
	VetRepository vetRepository;
	
	@InjectMocks
	VetSDJpaService service;
	
	Vet returnVet;
	
	@BeforeEach
	public void setUp() {
		returnVet = Vet.builder().id(1L).firstName(FIRST_NAME).build();
	}
	
	@Test
	public void findAll() {
		
	}

	@Test
	public void findById() {
		
	}

	@Test
	public void save() {
		
	}

	@Test
	public void delete() {
		
	}

	@Test
	public void deleteById() {
		
	}

}
