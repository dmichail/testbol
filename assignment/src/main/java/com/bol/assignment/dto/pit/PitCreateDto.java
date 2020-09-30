package com.bol.assignment.dto.pit;

import com.bol.assignment.model.Player;
import lombok.Data;
import lombok.Value;


@Value
public class PitCreateDto {

    private Integer position;
    private Player owner;
    private Boolean isKalaha;

}
