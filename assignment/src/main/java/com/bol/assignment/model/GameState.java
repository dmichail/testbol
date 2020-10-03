package com.bol.assignment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity(name = "gamestate")
@NoArgsConstructor
@Data
public class GameState implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @JsonIgnore
    private long id;

    private String currPlayerID;
    private String winner;
    private boolean gameOver;

    @ElementCollection
    @MapKeyColumn
    @CollectionTable(name = "StateMapping")
    private Map<Long, PlayerState> playerStateById;



    public GameState(String currPlayerID, String winner, boolean gameOver, Map<Long, PlayerState> playerStateById){
        this.currPlayerID = currPlayerID;
        this.winner = winner;
        this.gameOver = gameOver;
        this.playerStateById = playerStateById;
    }

}
