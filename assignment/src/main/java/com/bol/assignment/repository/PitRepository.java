package com.bol.assignment.repository;


import com.bol.assignment.model.Pit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PitRepository extends CrudRepository<Pit, Long> {}
