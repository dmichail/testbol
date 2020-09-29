package com.bol.assignment.dto;

import com.bol.assignment.MyConstants;
import lombok.Data;

import java.io.Serializable;


@Data
public class PlayerUpdateStatusDto implements Serializable {

    private long playerId;
    private MyConstants.PlayerStatus newStatus;

}
