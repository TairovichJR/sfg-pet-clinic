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
import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import guru.springframework.sfgpetclinic.services.springdatajpa.SpecialtySDJpaService;

/**
 * Created by tairovich_jr on Sep 22, 2020
 */
@ExtendWith(MockitoExtension.class)
public class SpecialtySDJpaServiceTest {

	private static final String DESCRIPTION = "Surgery";

	@Mock
	SpecialtyRepository specialtyRepository;
	
	@InjectMocks
	SpecialtySDJpaService service;
	
	Specialty returnSpecialty;
	
	@BeforeEach
	public void setUp() {
		returnSpecialty = Specialty.builder().id(1L).description(DESCRIPTION).build();
	}
	
	@Test
	public void findAll() {
		Set<Specialty> returnSpecialties = new HashSet<>();
		returnSpecialties.add(Specialty.builder().id(1L).build());
		returnSpecialties.add(Specialty.builder().id(2L).build());
		returnSpecialties.add(Specialty.builder().id(3L).build());
		
		when(specialtyRepository.findAll()).thenReturn(returnSpecialties);
		
		Set<Specialty> specialties = service.findAll();
		
		assertNotNull(specialties);
		assertEquals(3, specialties.size());
	}

	@Test
	public void findById() {
		when(specialtyRepository.findById(anyLong())).thenReturn(Optional.of(returnSpecialty));
		
		Specialty specialty = service.findById(1L);
		assertNotNull(specialty);
	}

	@Test
	public void findByWrongId() {
		when(specialtyRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		Specialty specialty = service.findById(1L);
		assertNull(specialty);
	}
	
	@Test
	public void save() {
		Specialty specialtyToSave = Specialty.builder().id(1L).description(DESCRIPTION).build();
		
		when(specialtyRepository.save(any())).thenReturn(returnSpecialty);
		
		Specialty savedSpecialty = service.save(specialtyToSave);
		
		assertNotNull(savedSpecialty);
		assertEquals(1L, savedSpecialty.getId());
	}
	
	@Test
	public void delete() {
		service.delete(returnSpecialty);
		verify(specialtyRepository).delete(any());
	}

	@Test
	public void deleteById() {
		service.deleteById(1L);
		verify(specialtyRepository).deleteById(anyLong());
	}

}
