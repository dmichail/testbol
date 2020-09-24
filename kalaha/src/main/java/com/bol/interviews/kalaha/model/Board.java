package com.bol.interviews.kalaha.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Board")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Board implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("boardId")
    private long boardId;

    @JsonProperty("p1Id")
    @ManyToOne(cascade = CascadeType.ALL,
           targetEntity = Player.class)
    @JoinColumn(name = "p1Id")
    private Player player1;

    //, insertable=false, updatable=false

    @JsonProperty("p2Id")
    @ManyToOne(cascade = CascadeType.ALL,
            targetEntity = Player.class)
    @JoinColumn(name = "p2Id")
    private Player player2;

    @JsonProperty("k1")
    @Column(name = "k1")
    private int k1;

    @JsonProperty("k2")
    @Column(name = "k2")
    private int k2;

    @JsonProperty("pits1")
    @Column(name = "pits1")
    private String pits1;

    @JsonProperty("pits2")
    @Column(name = "pits2")
    private String pits2;

    public long getBoardId() {
        return boardId;
    }

    public void setBoardId(long boardId) {
        this.boardId = boardId;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int getK1() {
        return k1;
    }

    public void setK1(int k1) {
        this.k1 = k1;
    }

    public int getK2() {
        return k2;
    }

    public void setK2(int k2) {
        this.k2 = k2;
    }

    public String getPits1() {
        return pits1;
    }

    public void setPits1(String pits1) {
        this.pits1 = pits1;
    }

    public String getPits2() {
        return pits2;
    }

    public void setPits2(String pits2) {
        this.pits2 = pits2;
    }


    public Board(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
    }
}
