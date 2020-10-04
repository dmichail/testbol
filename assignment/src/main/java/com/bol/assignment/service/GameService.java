package com.bol.assignment.service;

import com.bol.assignment.MyConstants;
import com.bol.assignment.MyConstants.*;
import com.bol.assignment.model.Game;
import com.bol.assignment.model.GameState;
import com.bol.assignment.model.Player;
import com.bol.assignment.model.PlayerState;
import com.bol.assignment.repository.GameRepository;
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
        foundGame.getState().addPlayerState(loggedInState, loggedInPlayer.getId());

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

    public void changeTurn(GameState gameState, Long playerID){




    }


    public GameState updateGameStateByGameId(Long gameId, GameState updatedState){
        Game game = getGameById(gameId);

        game.setState(updatedState);

        gameRepository.save(game);

        return game.getState();
    }




    //region "Gameplay"

    public boolean checkTurnValid(GameState gameState, Long currentPlayerTurnID){
        return gameState.getCurrPlayerID() == currentPlayerTurnID;
    }


    public boolean checkIfValidMove(Integer pit){
        if (pit != MyConstants.P1_END_INDEX && pit != MyConstants.P2_END_INDEX
        && pit >= MyConstants.P1_START_INDEX && pit <= MyConstants.P2_END_INDEX){
            return true;
        }
        return false;
    }

    public boolean isP1Turn(Long currentPlayerTurnID, Long player1ID){
        if (currentPlayerTurnID == player1ID)
            return true;

        return false;
    }


    //TODO - Badly written method - Needs splitting in smaller methods
    public GameState sowStones(Game game, Integer pitId, boolean isP1turn){
        GameState currGameState = game.getState();
        Map<Long, PlayerState> stateMap = currGameState.getPlayerStateById();
        Long currentPlayerID = currGameState.getCurrPlayerID();
        Long player1ID = game.getPlayers().get(0).getId();
        Long player2ID = game.getPlayers().get(1).getId();

        List<Integer> p1Board = stateMap.get(player1ID).getPits();
        Integer p1Kalaha = stateMap.get(player1ID).getKalaha();

        List<Integer> p2Board = stateMap.get(player2ID).getPits();
        Integer p2Kalaha = stateMap.get(player2ID).getKalaha();

        System.out.println("P1 Board: "+ p1Board);
        System.out.println("P2 Board: "+ p2Board);

        /*List<Integer> completedBoard = ListUtils.union(p1Board, p2Board);
        System.out.println("Combined Board: ");*/

        Integer limitEnd;
        Integer distanceTillEnd;
        int stonesHand;
        int extraStones;

        if (isP1turn) {
            limitEnd = MyConstants.P1_END_INDEX;
            stonesHand = p1Board.get(pitId);
            p1Board.set(pitId, 0);

            System.out.println("ienai mesa sto p1");

            distanceTillEnd = limitEnd - pitId;
            boolean gotExtraStones = stonesHand > distanceTillEnd;
            boolean extraTurn = stonesHand == distanceTillEnd;
            boolean capture = stonesHand < distanceTillEnd;


            //sow p1
            for (int i=pitId+1;i<=limitEnd;i++){
                Integer idxStones = p1Board.get(i);
                p1Board.set(i,++idxStones);
                stonesHand--;
            }

            if (gotExtraStones){
                //not another turn or capture
                extraStones = stonesHand;
                //sow p2
                for (int i=0; i<=extraStones;i++){
                    if (i == 6)
                        continue;
                    Integer idxStones = p2Board.get(i);
                    p2Board.set(i, ++idxStones);
                    stonesHand--;
                }

                //isGameOver()

                //TODO - EDGE CASE - not sure if it will happen
                //take 8 or more stones from pit 5 of player 1
                //if sow, player 1 will get last stone of his firs pit -> capture?
                if (stonesHand == 0)
                    //changeTurn
                    gameStateService.changeTurn(game.getState().getId(), player2ID);

            }
            else if (extraTurn){


                if (stonesHand == 0)
                    //changeTurn
                    gameStateService.changeTurn(game.getState().getId(), player1ID);

            }

            System.out.println("meta allages P1:" + p1Board);
            System.out.println("meta allages P2:" + p2Board);


            //set pits both players
            stateMap.get(player1ID).setPits(p1Board);
            stateMap.get(player2ID).setPits(p2Board);

            //set kalaha both players
            stateMap.get(player1ID).setKalaha(p1Board.size() - 1);
            stateMap.get(player2ID).setKalaha(p2Board.size() - 1);

            currGameState.setPlayerStateById(stateMap);

            //set winner
            //set gameover

            GameState updatedstate = updateGameStateByGameId(game.getId(), currGameState);

            return updatedstate;




        }
        else {
            limitEnd = MyConstants.P2_END_INDEX;






        }

        return null;

    }



    public GameState playMove(Long gameId,Integer pit) {
        Game game = getGameById(gameId);


        GameState res = sowStones(game, pit, true);


        return res;




    }

        //endregion




}
