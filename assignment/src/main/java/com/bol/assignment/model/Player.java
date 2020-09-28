package com.bol.assignment.model;


import com.bol.assignment.constants.PlayerStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PlayerStatus status;

    @OneToMany(mappedBy = "ownerId")
    private List<Pit> pitList;


    public Player(String _name){
        this.name = _name;
        this.status = PlayerStatus.ONLINE;
    }

}
