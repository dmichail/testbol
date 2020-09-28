package com.bol.assignment.dto;

import com.bol.assignment.constants;
import lombok.Data;

import java.io.Serializable;


@Data
public class playerUpdateStatusDto implements Serializable {

    private long playerId;
    private constants.PlayerStatus newStatus;

}
