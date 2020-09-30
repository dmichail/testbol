package com.bol.assignment.model;


import javax.persistence.Entity;

@Entity(name = "Game")
public class Game {

    private long id;
    private Board board;
    private int turnOf;
    private boolean isOver;
    private Player winner;



}
