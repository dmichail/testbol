package com.bol.assignment.service;

import com.bol.assignment.MyConstants;
import com.bol.assignment.MyConstants.*;
import com.bol.assignment.exceptions.game.GameNotFoundException;
import com.bol.assignment.exceptions.player.PlayerAlreadyExistsException;
import com.bol.assignment.model.Game;
import com.bol.assignment.model.GameState;
import com.bol.assignment.model.Player;
import com.bol.assignment.model.PlayerState;
import com.bol.assignment.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import org.apache.commons.collections.ListUtils;


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

    public Game joinGame(Long gameId, Player loggedInPlayer) throws PlayerAlreadyExistsException{
        Game foundGame = getGameById(gameId);

        //check if player exists already in the game
        if (!foundGame.getPlayers().contains(loggedInPlayer)){
            foundGame.addPlayer(loggedInPlayer);
        }else{
            throw new PlayerAlreadyExistsException(loggedInPlayer.getId());
        }

        PlayerState loggedInState = playerStateService.createPlayerState(loggedInPlayer.getId());
        foundGame.getState().addPlayerState(loggedInState, loggedInPlayer.getId());

        foundGame.setGameStatus(GameStatus.FULL);

        gameRepository.save(foundGame);

        return foundGame;
    }

    public Game getGameById(Long id){
       return gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
    }

    public void setWinner(Long gameId, String winner){
        Game found = getGameById(gameId);

        found.getState().setWinner(winner);

        gameRepository.save(found);
    }

    public void deleteGame(Long id){
        gameRepository.deleteById(id);
    }

    public Optional<Game> getFirstGameByStatus(GameStatus gameStatus){
        return gameRepository.findByGameStatus(gameStatus)
                .stream().findFirst()
                .filter(game -> game.getGameStatus().toString() == gameStatus.toString());
    }

    public Game updateGameStatus(Long gameId, GameStatus newStatus){
        Game foundGame = getGameById(gameId);
        foundGame.setGameStatus(newStatus);

        gameRepository.save(foundGame);

        return foundGame;
    }

    public Game updateGameStateByGameId(Long gameId, GameState updatedState){
        Game game = getGameById(gameId);

        game.setState(updatedState);

        gameRepository.save(game);

        return game;
    }




    //region "Gameplay"

    public boolean checkTurnValid(GameState gameState, Long currentPlayerTurnID){
        return gameState.getCurrPlayerID() == currentPlayerTurnID;
    }


    //limit - 6
    public boolean checkIfValidMove(Integer pit){
        if (pit != MyConstants.limitBoard && pit >= 0 && pit <= MyConstants.limitBoard){
            return true;
        }
        return false;
    }


    //limit - 13
    public boolean isValidMove(Integer pit, boolean isP1turn){
        if (pit >= MyConstants.P1_START_INDEX && pit < MyConstants.P1_END_INDEX && isP1turn) {
            return true;
        }else if (pit >= MyConstants.P2_START_INDEX && pit < MyConstants.P2_END_INDEX){
            return true;
        }

        return false;
    }


    public boolean isPlayer1Turn(Game currentGame)
    {
        Long currentPlayerID = currentGame.getState().getCurrPlayerID();
        Long p1ID = currentGame.getFirstPlayerId();

        if (currentPlayerID.equals(p1ID)){
            return true;
        }
        return false;
    }

    public boolean checkPitHasStones(Game game, Integer pit, Boolean isP1Turn){
        Integer stonesInPit;

        List<Integer> tempBoard = (List<Integer>) ListUtils.union(getP1pits(game), getP2pits(game));

        if (isP1Turn){
           stonesInPit = tempBoard.get(pit);
        }else{
           stonesInPit = tempBoard.get(pit);
        }

        if (stonesInPit > 0){
            return true;
        }
        return false;
    }

    public Game sowStones(Game game, Integer pitId, boolean isP1turn){
        GameState currGameState = game.getState();
        Integer stonesHand = -1;
        Long player1ID = game.getPlayers().get(0).getId();
        Long player2ID = game.getPlayers().get(1).getId();


        List<Integer> completeBoard = (List<Integer>) ListUtils.union(getP1pits(game), getP2pits(game));

        System.out.println(completeBoard);

        stonesHand = completeBoard.get(pitId);
        completeBoard.set(pitId, 0);

        System.out.println(stonesHand);

        Integer lastIndex = -1;
        Integer currIndex = pitId+1;
        while (stonesHand > 0){

            if (currIndex == 14){
                currIndex = 0;
            }

            if (isP1turn && currIndex == MyConstants.P2_END_INDEX) {
                //skip P2 kalaha
                //restart loop
                currIndex = 0;
                System.out.println("mhdenise");
                continue;
            }

            if (!isP1turn && currIndex == MyConstants.P1_END_INDEX) {
                //skil P1 kalaha in P2's turns
                continue;
            }



            Integer currStones = completeBoard.get(currIndex);

            completeBoard.set(currIndex, ++currStones);
            System.out.println(completeBoard);

            ++currIndex;
            stonesHand--;
        }
        System.out.println(lastIndex);
        lastIndex = currIndex;

        if (checkCapturePieces(completeBoard, lastIndex, isP1turn)){
           completeBoard = capture(completeBoard, lastIndex, isP1turn);
        }


        Game updatedGame = finishTurnAndSave(game, completeBoard, player1ID, player2ID);

        if (isGameOver(updatedGame)){
            updateGameStatus(updatedGame.getId(), GameStatus.FINISHED);
            setWinner(updatedGame.getId(),player1ID.toString());
        }

        //extra turn?
        if (isP1turn && lastIndex == MyConstants.P1_END_INDEX) {
            System.out.println("p1 another turn");
            updatedGame.getState().setCurrPlayerID(player1ID);
            gameStateService.changeTurn(updatedGame.getState().getId(), player1ID);
        } else if (!isP1turn && lastIndex == MyConstants.P2_END_INDEX) {
            System.out.println("p1 another turn");
            updatedGame.getState().setCurrPlayerID(player2ID);
            gameStateService.changeTurn(updatedGame.getState().getId(), player2ID);
        } else if (isP1turn){
            System.out.println("p1 changed turn. now p2");
            updatedGame.getState().setCurrPlayerID(player2ID);
            gameStateService.changeTurn(updatedGame.getState().getId(), player2ID);
        } else{
            System.out.println("p2 changed turn. now p1");
            updatedGame.getState().setCurrPlayerID(player1ID);
            gameStateService.changeTurn(updatedGame.getState().getId(), player1ID);
        }





        return updatedGame;
    }

    public Game finishTurnAndSave(Game game, List<Integer> completeBoard, Long p1ID, Long p2ID){
        GameState currGameState = game.getState();

        //split list in 2 seperate ones
        List<Integer> p1Board = new ArrayList<>(completeBoard.subList(0, MyConstants.totalPits /2 ));
        List<Integer> p2Board = new ArrayList<>(completeBoard.subList(MyConstants.totalPits/2, MyConstants.totalPits));

        System.out.println("P1 - Board: "+ p1Board);
        System.out.println("P2 - Board: "+ p2Board);


        currGameState.getPlayerStateById().get(p1ID).setPits(p1Board);
        currGameState.getPlayerStateById().get(p1ID).setKalaha(p1Board.get(MyConstants.limitBoard));

        currGameState.getPlayerStateById().get(p2ID).setPits(p2Board);
        currGameState.getPlayerStateById().get(p2ID).setKalaha(p2Board.get(MyConstants.limitBoard));

        Game updatedGame = updateGameStateByGameId(game.getId(), currGameState);


        return updatedGame;
    }




    public boolean checkCapturePieces(List<Integer> currBoard, int lastIndex, boolean isP1turn) {
        int lastPitStones = currBoard.get(lastIndex);

        if (lastPitStones == 1) {

            if (isP1turn && lastIndex <= MyConstants.P1_END_INDEX - 1){
                return true;
            }

            if (!isP1turn && lastIndex >= MyConstants.P2_START_INDEX && lastIndex <=MyConstants.P2_END_INDEX - 1){
                return true;
            }
        }

        return false;
    }

    public List<Integer> capture(List<Integer> currBoard, int lastIndex, boolean isP1turn) {
        int lastPitStones = currBoard.get(lastIndex);
        currBoard.set(lastIndex, 0);

        int opponentIndex  = MyConstants.map_OppositePits.get(lastIndex);
        int opponentStones = currBoard.get(opponentIndex);
        int currKalaha;
        int totalToAdd = lastPitStones + opponentStones;


        if (isP1turn) {
            currKalaha = currBoard.get(MyConstants.P1_END_INDEX);
            currBoard.set(MyConstants.P1_END_INDEX, currKalaha + totalToAdd);
        } else {
            currKalaha = currBoard.get(MyConstants.P2_END_INDEX);
            currBoard.set(MyConstants.P2_END_INDEX, currKalaha + totalToAdd);
        }

        return currBoard;
    }






    public Game playMove(Long gameId,Integer pit) {
        Game game = getGameById(gameId);
        boolean isP1Turn = isPlayer1Turn(game);

        if (isValidMove(pit,isP1Turn) && game.getGameStatus() != GameStatus.FINISHED && checkTurnValid(game.getState(), game.getState().getCurrPlayerID())) {

            if (checkPitHasStones(game, pit, isP1Turn)){
                return sowStones(game, pit, isP1Turn);
            }

        }
        //do nothing
        return game;
    }


    //TODO - Needs rewriting
    public boolean isGameOver(Game game){
        List<Player> playerList = game.getPlayers();
        boolean isDone = true;


        //P1
        if (game.getState().getCurrPlayerID() == playerList.get(0).getId()){
            List<Integer> p1Pits = getP1pits(game);
            for (int i=0; i <p1Pits.size() - 1;i++){
                if (p1Pits.get(i) > 0){
                    isDone = false;
                    break;
                }
            }
        }

        //P2
        if (game.getState().getCurrPlayerID() == playerList.get(1).getId()){
            List<Integer> p2Pits = getP2pits(game);
            for (int i=0; i <p2Pits.size() - 1;i++){
                if (p2Pits.get(i) > 0){
                    isDone = false;
                    break;
                }
            }
        }
        return isDone;

    }





   //endregion


    //region "Utils"

    public List<Integer> getP1pits(Game game){
        Long p1ID = game.getPlayers().get(0).getId();
        List<Integer> pits = game.getState().getPlayerStateById().get(p1ID).getPits();

        return pits;
    }

    public List<Integer> getP2pits(Game game) {
        Long p2ID = game.getPlayers().get(1).getId();
        List<Integer> pits = game.getState().getPlayerStateById().get(p2ID).getPits();
        return pits;
    }

    //endregion




}
