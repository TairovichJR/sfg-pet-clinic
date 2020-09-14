package guru.springframework.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.sfgpetclinic.model.PetType;

/**
 * Created by tairovich_jr on Sep 14, 2020
 */
public interface PetTypeRepository extends CrudRepository<PetType,Long> {

}
