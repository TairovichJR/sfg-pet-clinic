package guru.springframework.sfgpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.VisitService;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private final SpecialtyService specialtyService;
	private final VisitService visitService;
	
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
			SpecialtyService specialtyService, VisitService visitService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialtyService = specialtyService;
		this.visitService = visitService;
	}


	@Override
	public void run(String... args) throws Exception {
		
		
		int count = petTypeService.findAll().size();
		
		if(count == 0) {
			loadData();
		}		
	}


	private void loadData() {
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);
		
		PetType cat = new PetType();
		cat.setName("Cat");
		PetType savedCatPetType = petTypeService.save(cat);
		
		Owner michael = new Owner();
		michael.setFirstName("Michael");
		michael.setLastName("Weston");
		michael.setAddress("123 Brickerel");
		michael.setCity("Miami");
		michael.setTelephone("224353546");
	
		Pet mikesPet = new Pet();
		mikesPet.setPetType(savedDogPetType);
		mikesPet.setOwner(michael);
		mikesPet.setBirthDate(LocalDate.now());
		mikesPet.setName("Rosco");
		michael.getPets().add(mikesPet);
		ownerService.save(michael);
		
		Owner fiona = new Owner();
		fiona.setFirstName("Fiona");
		fiona.setLastName("Glenanne");
		fiona.setAddress("123 Brickerel");
		fiona.setCity("Miami");
		fiona.setTelephone("86745454");
		
		Pet fionasCat = new Pet();
		fionasCat.setName("Fionas Cat");
		fionasCat.setOwner(fiona);
		fionasCat.setBirthDate(LocalDate.now());
		fionasCat.setPetType(savedCatPetType);
		fiona.getPets().add(fionasCat);
		
		ownerService.save(fiona);
		 
		System.out.println("--------Loaded Owners---------");
		
		Visit catVisit = new Visit();
		catVisit.setPet(fionasCat);
		catVisit.setDate(LocalDate.now());
		catVisit.setDescription("Sneezy Kitty");
		
		visitService.save(catVisit);
		int count = visitService.findAll().size();
		System.out.println("--------Loaded " + count + " Visits---------");
		
		/****** Specialty Objects *******/
		Specialty radiology = new Specialty();
		radiology.setDescription("Radiology");
		Specialty savedRadiology = specialtyService.save(radiology);
		
		Specialty surgery = new Specialty();
		surgery.setDescription("Surgery");
		Specialty savedSurgery = specialtyService.save(surgery);
		
		Specialty dentistry = new Specialty();
		dentistry.setDescription("Dentistry");
		Specialty savedDentistry = specialtyService.save(dentistry);
		
		/****** Vet Objects *******/
		Vet sam = new Vet();
		sam.setFirstName("Sam");
		sam.setLastName("Axe");
		sam.getSpecialties().add(savedRadiology);
		
		vetService.save(sam);
		
		Vet jessie = new Vet();
		jessie.setFirstName("Jessie");
		jessie.setLastName("Porter");
		jessie.getSpecialties().add(savedSurgery);
		
		vetService.save(jessie);
		
		Vet harry = new Vet();
		harry.setFirstName("Harry");
		harry.setLastName("Trump");
		harry.getSpecialties().add(savedDentistry);
		
		vetService.save(harry);
		
		System.out.println("--------Loaded Vets---------");
	}

	

}
