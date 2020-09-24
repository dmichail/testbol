package com.bol.interviews.kalaha.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.bol.interviews.kalaha.constants.constants.INIT_STONES_PIT;

public class Pit {

    private int noStones;
    private int positionIdx;
    private int belongsToPlayer;


    public Pit() {
        this.noStones = INIT_STONES_PIT;
    }

    public Pit(Integer positionIdx, Integer belongsToPlayer){
        this.noStones = INIT_STONES_PIT;
        this.positionIdx = positionIdx;
        this.belongsToPlayer = belongsToPlayer;
    }

    public Pit(Integer noStones, Integer positionIdx, Integer belongsToPlayer){
        this.noStones = noStones;
        this.positionIdx = positionIdx;
        this.belongsToPlayer = belongsToPlayer;
    }


    public void sow(){
        this.noStones++;
    }

    public void clear(){
        this.noStones = 0;
    }

    public Integer pickStones(){
        return this.noStones;
    }

    public int getNoStones() {
        return noStones;
    }
    public int getPositionIdx() {return positionIdx;}
    public int getBelongsToPlayer() {return belongsToPlayer;}

    public void setNoStones(int noStones) {
        this.noStones = noStones;
    }
    public void setPositionIdx(int positionIdx) {
        this.positionIdx = positionIdx;
    }
    public void setBelongsToPlayer(int belongsToPlayer) {
        this.belongsToPlayer = belongsToPlayer;
    }

    @Override
    public String toString(){
        return String.valueOf(this.noStones);
    }

}
