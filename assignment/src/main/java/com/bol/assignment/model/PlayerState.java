package com.bol.assignment.model;

import com.bol.assignment.MyConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity(name = "playerstate")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerState implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonIgnore
    private long id;

    private int kalaha;

    @ElementCollection
    private List<Integer> pits;

    @OneToOne
    @JsonIgnore
    private Player player;


    public PlayerState(int kalaha, List<Integer> pits, Player player){
        this.kalaha = kalaha;
        this.pits = pits;
        this.player = player;
    }

    @PrePersist
    public void prePersist(){
        if (pits == null)
            pits = MyConstants.initPits;
    }

}
