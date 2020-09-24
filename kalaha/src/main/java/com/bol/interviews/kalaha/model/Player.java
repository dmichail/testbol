package com.bol.interviews.kalaha.model;

import com.bol.interviews.kalaha.constants.constants.PlayerStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@Entity(name = "Player")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("playerId")
    @Column(name = "playerId")
    private long playerId;

    @NotBlank(message = "Name is mandatory")
    @Column(name="playerName")
    @JsonProperty("playerName")
    private String playerName;

    @JsonIgnore
    private PlayerStatus playerStatus;

    public Player(){}

    public Player(String name)
    {
        this.playerName = name;
    }

    public String getplayerName()
    {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public long getPlayerId() {
        return playerId;
    }

}
