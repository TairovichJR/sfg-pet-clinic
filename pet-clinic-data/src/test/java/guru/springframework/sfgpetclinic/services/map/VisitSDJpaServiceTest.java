package guru.springframework.sfgpetclinic.services.map;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import guru.springframework.sfgpetclinic.services.springdatajpa.VisitSDJpaService;

/**
 * Created by tairovich_jr on Sep 22, 2020
 */
@ExtendWith(MockitoExtension.class)
public class VisitSDJpaServiceTest {

	@Mock
	VisitRepository visitRepository;
	
	@InjectMocks
	VisitSDJpaService service;
	
	@BeforeEach
	public void setUp() {
		
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
