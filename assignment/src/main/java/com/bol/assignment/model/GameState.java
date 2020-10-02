package com.bol.assignment.model;

import lombok.Data;
import javax.persistence.*;
import java.util.Map;

@Entity
@Data
public class GameState {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "playerId")
    private Player currPlayerID;

    private String winner;


    private boolean gameOver;

    @ElementCollection
    @MapKeyColumn(name = "PlayerId")
    @CollectionTable(name = "StateMapping")
    private Map<Long, PlayerState> playerStateById;



    public GameState(Player currPlayerID, String winner, boolean gameOver, Map<Long, PlayerState> playerStateById){
        this.currPlayerID = currPlayerID;
        this.winner = winner;
        this.gameOver = gameOver;
        this.playerStateById = playerStateById;
    }

}
