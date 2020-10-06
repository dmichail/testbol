package com.bol.assignment.dto;

import com.bol.assignment.MyConstants.PlayerStatus;
import lombok.Data;

import java.io.Serializable;


@Data
public class PlayerUpdateStatusDto implements Serializable {

    private PlayerStatus newStatus;

}
