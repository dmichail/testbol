package com.bol.assignment.requestObjects;


import lombok.Data;

import java.util.List;

@Data
public class PlayerStateRequest {

    private int kalaha;
    private List<Integer> pits;
}
