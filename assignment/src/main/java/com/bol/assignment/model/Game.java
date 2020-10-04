package com.bol.assignment.model;


import com.bol.assignment.MyConstants.GameStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false)
    private long id;

    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    @OneToMany
    private List<Player> players;

    @OneToOne(cascade = CascadeType.MERGE)
    private GameState state;



    public Game(GameStatus gameStatus, List<Player> players, GameState state){
        this.gameStatus = gameStatus;
        this.players = players;
        this.state = state;
    }


    public void addPlayer(Player playerToAdd){
        this.players.add(playerToAdd);
    }

    @JsonIgnore
    public Long getFirstPlayerId(){
        return this.players.get(0).getId();
    }


    @JsonIgnore
    public String getCurrentPlayerName(){
        Long playerId = this.state.getCurrPlayerID();
        String playerName = this.players.get(Math.toIntExact(playerId)).getName();

        return playerName;
    }
}

