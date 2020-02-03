package com.psw.clinicalcentre.types;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface TypesRepository extends CrudRepository<TypesOfAppointments, Integer> {

    @Query("select distinct name from TypesOfAppointments")
    Set<String> findAllDistinctNames();

}
