package com.bol.interviews.kalaha.dto;

import java.io.Serializable;


public class playerNameDto implements Serializable {

    public String playerName;

    public playerNameDto(){}
    public playerNameDto(String playerName){ this.playerName = playerName; }

    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
