package guru.springframework.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.sfgpetclinic.model.Vet;

/**
 * Created by tairovich_jr on Sep 14, 2020
 */
public interface VetRepository extends CrudRepository<Vet,Long> {

}
