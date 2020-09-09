package guru.springframework.sfgpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
	}


	@Override
	public void run(String... args) throws Exception {
		
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);
		
		PetType cat = new PetType();
		dog.setName("Cat");
		PetType savedCatPetType = petTypeService.save(cat);
		
		Owner michael = new Owner();
		michael.setFirstName("Michael");
		michael.setLastName("Weostn");
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
		
		System.out.println("Loaded Owners....");
		
		Vet vet1 = new Vet();
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");
		
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setFirstName("Jessie");
		vet2.setLastName("Porter");
		
		vetService.save(vet2);
		
		
		System.out.println("Loaded Vets...");
		
		
		
		
	}

	

}
