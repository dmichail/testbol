package com.bol.assignment.dto;


import com.bol.assignment.MyConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerDto implements Serializable {

    private long id;
    private String name;
    private MyConstants.PlayerStatus status;

    public PlayerDto(String name){
        this.name = name;
        this.status = MyConstants.PlayerStatus.ONLINE;
    }


}
