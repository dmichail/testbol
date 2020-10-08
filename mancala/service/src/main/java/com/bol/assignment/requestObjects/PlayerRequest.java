package com.bol.assignment.requestObjects;


import com.bol.assignment.MyConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerRequest{

    private long id;
    private String name;
    private MyConstants.PlayerStatus status;

    public PlayerRequest(String name){
        this.name = name;
        this.status = MyConstants.PlayerStatus.ONLINE;
    }

}
