package com.bol.interviews.kalaha.model;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.IntStream;

public class GameEngine {

    private boolean isPlayer1turn = false;
    private boolean isPlayer2turn = false;
    private ConcurrentMap<Integer, Pit> currentBoardMap;

    private void sowStones(Board currentBoard, Integer fromPosIdx){




    }

    //Returns null if not found
    private Pit getPit(Integer posIdx){
        return this.currentBoardMap.getOrDefault(posIdx, null);
    }

    private Integer getPitStones(Pit pit){
        return pit.getNoStones();
    }


    private Boolean randomStartmove(){
        return new Random().nextBoolean();
    }

    private void changeTurn(){
        if (isPlayer1turn || isPlayer2turn){
            isPlayer1turn = !isPlayer1turn;
            isPlayer2turn = !isPlayer2turn;
        }
    }








}
