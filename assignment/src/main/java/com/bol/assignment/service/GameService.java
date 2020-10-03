package com.bol.assignment.service;

import com.bol.assignment.MyConstants;
import com.bol.assignment.MyConstants.*;
import com.bol.assignment.model.Game;
import com.bol.assignment.model.GameState;
import com.bol.assignment.model.Player;
import com.bol.assignment.model.PlayerState;
import com.bol.assignment.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

        //Get playerState
        PlayerState loggedInState = playerStateService.createPlayerState(loggedInPlayer.getId());

        //Create GameState
        GameState currGameState = gameStateService.createGameState(loggedInState);

        //Setup players
        List<Player> playerList = new ArrayList<>();
        playerList.add(loggedInPlayer);

        Game game = new Game(GameStatus.AWAITING_PLAYER, playerList,currGameState);
        gameRepository.save(game);

        return game;
    }

    public Game joinGame(Long gameId,Player loggedInPlayer){
        //get game
        Game foundGame = getGameById(gameId);

        //add player and create new playerstatus
        foundGame.addPlayer(loggedInPlayer);

        PlayerState loggedInState = playerStateService.createPlayerState(loggedInPlayer.getId());
        foundGame.getState().addPlayerState(loggedInState);

        foundGame.setGameStatus(GameStatus.FULL);

        gameRepository.save(foundGame);

        return foundGame;
    }

    public Game getGameById(Long id){
       return gameRepository.findById(id).orElseThrow(() -> new RuntimeException("cant find game with id: " + id));
    }

    public void deleteGame(Long id){
        gameRepository.deleteById(id);
    }

    public Optional<Game> getFirstGameByStatus(GameStatus gameStatus){
        return gameRepository.findByGameStatus(gameStatus)
                .stream().findFirst()
                .filter(game -> game.getGameStatus().toString() == gameStatus.toString());
    }

    public void updateGameStatus(Long gameId, GameStatus newStatus){
        Game foundGame = getGameById(gameId);
        foundGame.setGameStatus(newStatus);

        gameRepository.save(foundGame);
    }

    public GameState getGameStateByGameId(Long gameId){
        Game foundgame = getGameById(gameId);

        return foundgame.getState();
    }

    //region "Gameplay"

    public boolean checkTurnValid(GameState gameState, String currentPlayerTurnID){
        return gameState.getCurrPlayerID().equals(currentPlayerTurnID);
    }


    public boolean checkIfValidMove(Integer pit){
        if (pit != MyConstants.P1_END_INDEX && pit != MyConstants.P2_END_INDEX
        && pit >= MyConstants.P1_START_INDEX && pit <= MyConstants.P2_END_INDEX){
            return true;
        }
        return false;
    }

    public void sowStones(GameState gameState, Integer pitId){
        Map<Long, PlayerState> stateMap = gameState.getPlayerStateById();










    }



    public GameState playMove(Long gameId,Integer pit){
        GameState gameState = getGameStateByGameId(gameId);

        if (checkIfValidMove(pit)){






        }
        else{
            throw new RuntimeException("Not allowed move");
        }

        return gameState;
    }
















    //endregion







}
