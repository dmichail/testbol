package com.bol.interviews.kalaha.model;

import lombok.Builder;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity(name="Game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long gameId;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Board.class)
    @JoinColumn(name = "boardId", referencedColumnName = "boardId")
    private Board board;

    @Column(name = "turnOfWithId")
    private int turnOfWithId;

    @Column(name =  "isOver")
    private Boolean isOver;

    @Column(name = "winner")
    @Nullable
    private String winner;

    public Game(){}


    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getTurnOfWithId() {
        return turnOfWithId;
    }

    public void setTurnOfWithId(int turnOfWithId) {
        this.turnOfWithId = turnOfWithId;
    }

    public Boolean getIsOver() {
        return isOver;
    }

    public void setIsOver(Boolean isOver) {
        isOver = isOver;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
