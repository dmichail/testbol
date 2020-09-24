package com.bol.interviews.kalaha.repository;


import com.bol.interviews.kalaha.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface playerRepository extends CrudRepository<Player, Long> {}
