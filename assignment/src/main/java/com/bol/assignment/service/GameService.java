package com.bol.assignment.service;

import com.bol.assignment.MyConstants;
import com.bol.assignment.MyConstants.*;
import com.bol.assignment.exceptions.game.GameNotFoundException;
import com.bol.assignment.model.Game;
import com.bol.assignment.model.GameState;
import com.bol.assignment.model.Player;
import com.bol.assignment.model.PlayerState;
import com.bol.assignment.repository.GameRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.id.IntegralDataTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import org.apache.commons.collections.ListUtils;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


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

    public Game joinGame(Long gameId,Player loggedInPlayer){
        Game foundGame = getGameById(gameId);
        foundGame.addPlayer(loggedInPlayer);

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


    public boolean checkIfValidMove(Integer pit){
        if (pit != MyConstants.limitBoard && pit >= 0 && pit <= MyConstants.limitBoard){
            return true;
        }
        return false;
    }

    public boolean isPlayer1Turn(Game currentGame)
    {
        Long currentPlayerID = currentGame.getState().getCurrPlayerID();
        Long p1ID = currentGame.getFirstPlayerId();

        if (currentPlayerID == p1ID){
            return true;
        }
        return false;
    }

    //TODO - Really Badly written method - Needs splitting in smaller methods - Duplicate code
    public Game sowStones(Game game, Integer pitId, boolean isP1turn){
        GameState currGameState = game.getState();
        Map<Long, PlayerState> stateMap = currGameState.getPlayerStateById();
        Long player1ID = game.getPlayers().get(0).getId();
        Long player2ID = game.getPlayers().get(1).getId();
        /*String currentPlayerName = game.getCurrentPlayerName();*/


        List<Integer> p1Board = stateMap.get(player1ID).getPits();
        List<Integer> p2Board = stateMap.get(player2ID).getPits();

        Integer limitEnd = MyConstants.limitBoard;
        Integer distanceTillEnd;
        int stonesHand;


        if (isP1turn) {
            //P1 Movement
            int flag;
            stonesHand = p1Board.get(pitId);
            p1Board.set(pitId, 0);

            distanceTillEnd = limitEnd - pitId;
            boolean gotExtraStones = stonesHand > distanceTillEnd;
            boolean extraTurn = stonesHand == distanceTillEnd;
            boolean capture = stonesHand < distanceTillEnd;


            //sow p1
            flag = pitId+1;
            while (stonesHand > 0 && distanceTillEnd > 0){
                Integer idxStones = p1Board.get(flag);
                p1Board.set(flag,++idxStones);
                stonesHand--;
                distanceTillEnd--;
                flag++;
            }


            if (gotExtraStones){
                //not another turn or capture
                //sow p2
                flag = 0;
                while (stonesHand > 0){
                    if (flag == 6)
                        continue;
                    Integer idxStones = p2Board.get(flag);
                    p2Board.set(flag, ++idxStones);

                    stonesHand--;
                    flag++;
                }

                //TODO - EDGE CASE - not sure if it will happen
                //take 8 or more stones from pit 5 of player 1
                //if sow, player 1 will get last stone of his firs pit -> capture?



            }
            else if (extraTurn){
                if (stonesHand == 0)
                    //changeTurn
                    gameStateService.changeTurn(game.getState().getId(), player1ID);

            }
            else if (capture){
                //Maybe capture - does not apply in all scenarios
                //map_OppositePits
                int last_index_pit = flag;
                int last_index_stones = p1Board.get(last_index_pit);
                if (last_index_stones == 1){
                    int opponent_index = MyConstants.map_OppositePits.get(last_index_pit);
                    int opponent_stones = p2Board.get(opponent_index);
                    p1Board.set(opponent_index, 0);
                    p2Board.set(last_index_pit, 0);

                    p2Board.set( p1Board.size()-1, last_index_stones + opponent_stones);

                }



            }

            //set pits both players
            stateMap.get(player1ID).setPits(p1Board);
            stateMap.get(player2ID).setPits(p2Board);

            //set kalaha both players
            stateMap.get(player1ID).setKalaha(p1Board.get(p1Board.size() - 1));
            stateMap.get(player2ID).setKalaha(p2Board.get(p2Board.size() - 1));

            currGameState.setPlayerStateById(stateMap);

            Game updatedGame = updateGameStateByGameId(game.getId(), currGameState);

            if (isGameOver(updatedGame)){
                updateGameStatus(updatedGame.getId(), GameStatus.FINISHED);
                setWinner(updatedGame.getId(),player1ID.toString());
            }

            gameStateService.changeTurn(game.getState().getId(), player2ID);

            return updatedGame;

        }
        else {
            //P2 Movement

            stonesHand = p2Board.get(pitId);
            p2Board.set(pitId, 0);

            distanceTillEnd = limitEnd - pitId;
            boolean gotExtraStones = stonesHand > distanceTillEnd;
            boolean extraTurn = stonesHand == distanceTillEnd;
            boolean capture = stonesHand < distanceTillEnd;

            //sow p2
            int flag = pitId+1;
            while (stonesHand > 0 && distanceTillEnd > 0){
                Integer idxStones = p2Board.get(flag);
                p2Board.set(flag,++idxStones);
                stonesHand--;
                distanceTillEnd--;
                flag++;
            }


            if (gotExtraStones){
                //not another turn or capture
                //sow p2
                flag = 0;
                while (stonesHand > 0){
                    if (flag == 6)
                        continue;
                    Integer idxStones = p1Board.get(flag);
                    p1Board.set(flag, idxStones++);

                    stonesHand--;
                    flag++;
                }

                //TODO - EDGE CASE - SAME FOR Player2



            }else if (extraTurn){
                if (stonesHand == 0)
                    //changeTurn
                    gameStateService.changeTurn(game.getState().getId(), player2ID);

            }else {
                //Maybe capture - does not apply in all scenarios
                //map_OppositePits
                int last_index_pit = flag;
                int last_index_stones = p2Board.get(last_index_pit);
                if (last_index_stones == 1){
                    int opponent_index = MyConstants.map_OppositePits.get(last_index_pit);
                    int opponent_stones = p1Board.get(opponent_index);
                    p1Board.set(opponent_index, 0);
                    p2Board.set(last_index_pit, 0);

                    p2Board.set( p1Board.size()-1, last_index_stones + opponent_stones);

                }

                gameStateService.changeTurn(game.getState().getId(), player1ID);



            }

            //set pits both players
            stateMap.get(player1ID).setPits(p1Board);
            stateMap.get(player2ID).setPits(p2Board);

            //set kalaha both players
            stateMap.get(player1ID).setKalaha(p1Board.get(p1Board.size() - 1));
            stateMap.get(player2ID).setKalaha(p2Board.get(p2Board.size() - 1));

            currGameState.setPlayerStateById(stateMap);

            //set winner
            //set gameover
            Game updatedGame = updateGameStateByGameId(game.getId(), currGameState);

            if (isGameOver(updatedGame)){
                updateGameStatus(updatedGame.getId(), GameStatus.FINISHED);
                setWinner(updatedGame.getId(),player2ID.toString());
            }

            gameStateService.changeTurn(game.getState().getId(), player1ID);

            return updateGameStateByGameId(game.getId(), currGameState);


        }

    }


    public Game playMove(Long gameId,Integer pit) {
        Game game = getGameById(gameId);

        //Validate pitId is from 0 until 5 -  (kalaha = 6)
        if (checkIfValidMove(pit) && game.getGameStatus() != GameStatus.FINISHED && checkTurnValid(game.getState(), game.getState().getCurrPlayerID())) {
            return sowStones(game, pit, isPlayer1Turn(game));
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
