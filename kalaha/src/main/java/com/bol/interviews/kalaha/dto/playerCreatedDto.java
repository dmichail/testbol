package com.bol.interviews.kalaha.dto;


import java.io.Serializable;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class playerCreatedDto implements Serializable {

    private long playerId;

    /*public playerCreatedDto(long playerId) {
        this.playerId = playerId;
    }*/
}
