package com.bol.interviews.kalaha.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class boardDto {

    private List<playerBoardDto> playerBoardDtoList;

}
