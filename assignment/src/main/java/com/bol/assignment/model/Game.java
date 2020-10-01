package com.bol.assignment.model;


import javax.persistence.*;

@Entity(name = "Game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @OneToOne
    @JoinColumn(name = "boardId", nullable = false)
    private Board board;

    @ManyToOne
    @JoinColumn(name = "turnId", nullable = false)
    private Player playerTurn;

    @Column(name = "isOver")
    private boolean isOver;

    @ManyToOne
    @JoinColumn(name = "winnerId")
    private Player winner;

}

