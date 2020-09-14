package guru.springframework.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.sfgpetclinic.model.Pet;

/**
 * Created by tairovich_jr on Sep 14, 2020
 */
public interface PetRepository extends CrudRepository<Pet, Long> {

}
