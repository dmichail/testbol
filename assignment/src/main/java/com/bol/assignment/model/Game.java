package com.bol.assignment.model;


import javax.persistence.*;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "turnId", nullable = false)
    private Player playerTurn;

    @Column(name = "isOver")
    private boolean isOver;

    @ManyToOne
    @JoinColumn(name = "winnerId")
    private Player winner;

}

