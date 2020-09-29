package com.bol.assignment.model;


import com.bol.assignment.MyConstants;
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

    @ManyToOne
    @JoinColumn(name = "id")
    private Player owner;
    @Column(name = "isKalaha")
    private boolean isKalaha;
    @Column(name = "stonesKalaha")
    @Nullable
    private int stonesKalaha;


    public Pit(Integer position, Integer stones, Player owner, Boolean isKalaha, Integer stonesKalaha){
        this.position = position;
        this.stones = stones;
        this.owner = owner;
        this.isKalaha = isKalaha;
        this.stonesKalaha = stonesKalaha;
    }


    public Pit(Integer position, Player owner, Boolean isKalaha) {
        this.position = position;
        this.stones = MyConstants.INIT_STONES_PIT;
        this.owner = owner;
        this.isKalaha = isKalaha;
        this.stonesKalaha = isKalaha ? MyConstants.INIT_STONES_KALHALA : null;
    }
}
