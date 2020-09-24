package com.bol.interviews.kalaha.dto;


import com.bol.interviews.kalaha.model.Pit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class updateBoardDto {

    private long boardId;
    private int kalahaP1;
    private int kalahaP2;
    private List<Pit> pitsP1;
    private List<Pit> pitsP2;

}
