package com.bol.assignment.model;


import com.bol.assignment.MyConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "Pit")
public class Pit {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pitId")
    private long id;

    @Column(name="posIdx")
    private int position;

    @Column(name = "stones")
    private int stones;

    @Column(name = "isKalaha")
    private boolean isKalaha;

    @Column(name = "stonesKalaha")
    @Nullable
    private int stonesKalaha;

    @ManyToOne
    @JoinColumn(name = "boardId", nullable = false)
    private Board board;


    public Pit(Integer position, Integer stones, Board board, Boolean isKalaha, Integer stonesKalaha){
        this.position = position;
        this.stones = stones;
        this.board = board;
        this.isKalaha = isKalaha;
        this.stonesKalaha = stonesKalaha;
    }


    public Pit(Integer position, Board board, Boolean isKalaha) {
        this.position = position;
        this.stones = MyConstants.INIT_STONES_PIT;
        this.board = board;
        this.isKalaha = isKalaha;
        this.stonesKalaha = isKalaha ? MyConstants.INIT_STONES_KALHALA : null;
    }
}
