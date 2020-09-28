package com.bol.assignment.repository;

import com.bol.assignment.constants;
import com.bol.assignment.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface playerRepository extends CrudRepository<Player, Long> {

}
