package com.bol.assignment.repository;


import com.bol.assignment.model.GameState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameStateRepository extends CrudRepository<GameState, Long> {
}
