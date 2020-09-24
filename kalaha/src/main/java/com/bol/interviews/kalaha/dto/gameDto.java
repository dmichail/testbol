package com.bol.interviews.kalaha.dto;


import com.bol.interviews.kalaha.dto.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class gameDto {
    private long id;
    private List<playerBoardDto> playerBoardDtoList;
    private int  turnOfWithId;
    private boolean isOver;
    private String winner;
}
