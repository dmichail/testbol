package com.bol.assignment.service;


import com.bol.assignment.MyConstants;
import com.bol.assignment.model.Game;
import com.bol.assignment.model.GameState;
import com.bol.assignment.model.Player;
import com.bol.assignment.model.PlayerState;
import com.bol.assignment.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    @Autowired
    public GameRepository gameRepository;

    private PlayerStateService playerStateService;
    private GameStateService gameStateService;
    private PlayerService playerService;

    @Autowired
    public GameService(PlayerStateService playerStateService, GameStateService gameStateService, PlayerService playerService){
        this.playerStateService = playerStateService;
        this.gameStateService = gameStateService;
        this.playerService = playerService;
    }


    public Game createGame(Player loggedInPlayer){
        //Get loggedInPlayer
        PlayerState loggedInState = playerStateService.createPlayerState(loggedInPlayer.getId());

        //Create GameState
        GameState currGameState = gameStateService.createGameState(loggedInState);

        //Setup players
        List<Player> playerList = new ArrayList<>();
        playerList.add(loggedInPlayer);

        Game game = new Game();
        game.setPlayers(playerList);
        game.setGameStatus(MyConstants.GameStatus.AWAITING_PLAYER);
        game.setState(currGameState);

        gameRepository.save(game);

        return game;
    }



    public Game getGameById(Long id){
       return gameRepository.findById(id).orElseThrow(() -> new RuntimeException("cant find game with id: " + id));
    }

    public void deleteGame(Long id){
        gameRepository.deleteById(id);
    }


    public GameState playMove(Integer pit){
        GameState gameState = new GameState();


        return gameState;
    }


    /*public List<Game> getGamesByStatus(MyConstants.GameStatus gameStatus){
        return gameRepository.findGamesByStatus(gameStatus);

    }*/






}
