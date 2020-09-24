package com.bol.interviews.kalaha.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class playerBoardDto {

    private long id;
    private String name;
    private int kalaha;
    private List<Integer> pits;

}
