package com.bol.interviews.kalaha.repository;


import com.bol.interviews.kalaha.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface gameRepository extends CrudRepository<Game, Long> {}

