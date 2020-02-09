package com.psw.clinicalcentre.scores;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ScoreRepository extends CrudRepository<Score, Integer> {

    @Query("select s from Score s " +
            "where s.user.id = :id ")
    Set<Score> findAllForUserId(@Param("id") Integer id);

}
