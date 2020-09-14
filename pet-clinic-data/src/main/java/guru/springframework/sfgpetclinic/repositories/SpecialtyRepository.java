package guru.springframework.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.sfgpetclinic.model.Specialty;

/**
 * Created by tairovich_jr on Sep 14, 2020
 */
public interface SpecialtyRepository extends CrudRepository<Specialty, Long>{

}
