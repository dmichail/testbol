package com.bol.assignment.repository;


import com.bol.assignment.model.PlayerState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerStateRepository extends CrudRepository<PlayerState, Long> {}
