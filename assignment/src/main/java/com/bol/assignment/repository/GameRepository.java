package com.bol.assignment.repository;


import com.bol.assignment.MyConstants.GameStatus;
import com.bol.assignment.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {


    /*List<Game> findGamesByStatus(GameStatus gameStatus);*/

}
