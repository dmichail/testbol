package com.bol.assignment.model;


import com.bol.assignment.MyConstants.GameStatus;
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



}

