package com.bol.assignment.model;


import lombok.Data;

import javax.persistence.Entity;
import java.util.List;

@Entity(name = "Board")
@Data
public class Board {

    private Long boardId;
    private List<Pit> pitList;
    private Player player1;
    private Player player2;

    public Board(List<Pit> pitList1, Player player1, Player player2 ){
        this.pitList = pitList1;
        this.player1 = player1;
        this.player2 = player2;
    }

}
