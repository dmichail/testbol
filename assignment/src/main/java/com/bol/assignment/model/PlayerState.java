package com.bol.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerState implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int kalaha;

    @ElementCollection
    private List<Integer> pits;

    public PlayerState(int kalaha, List<Integer> pits){
        this.kalaha = kalaha;
        this.pits = pits;
    }



    @PrePersist
    public void prePersist(){
        if (pits == null)
            pits = Arrays.asList(6,6,6,6,6,6,0);
    }

}
