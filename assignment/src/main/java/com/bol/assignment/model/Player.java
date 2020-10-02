package com.bol.assignment.model;


import com.bol.assignment.MyConstants.PlayerStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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

    /*@Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;*/

    @OneToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private PlayerState playerState;






}
