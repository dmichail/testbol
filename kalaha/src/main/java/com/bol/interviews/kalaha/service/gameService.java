package com.bol.interviews.kalaha.service;


import com.bol.interviews.kalaha.model.Board;
import com.bol.interviews.kalaha.model.Game;
import com.bol.interviews.kalaha.model.Player;
import com.bol.interviews.kalaha.repository.gameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class gameService {

    @Autowired
    public gameRepository gameRepository;

    @Autowired
    public boardService boardService;


    Game getGameById(Long gameId) {
        return gameRepository.findById(gameId).get();
    }


    Game createGame(Player player1, Player player2){
       Game game = new Game();
       Board boardCreated  = boardService.createNewBoard(player1, player2);
       game.setBoard(boardCreated);
       game.setIsOver(false);
       game.setTurnOfWithId(1); //player 1 starts
       game.setWinner(null);

       gameRepository.save(game);

       return game;

    }










}
