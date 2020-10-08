package com.bol.assignment.requestObjects;

import com.bol.assignment.MyConstants.PlayerStatus;
import lombok.Data;


@Data
public class PlayerUpdateStatusRequest {

    private PlayerStatus newStatus;
}
