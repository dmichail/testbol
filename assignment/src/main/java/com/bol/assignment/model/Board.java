package com.bol.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Board")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Board {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "boardId")
    private Long boardId;


    @OneToMany(mappedBy = "board")
    private List<Pit> pitList;


    @ManyToOne(targetEntity = Player.class)
    @JoinColumn(name = "p1Id", nullable = false)
    private Player player1;

    @ManyToOne(targetEntity = Player.class)
    @JoinColumn(name = "p2Id")
    private Player player2;

    @OneToOne
    @JoinColumn(name = "gameId", nullable = false)
    private Game game;


    public Board(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
    }

    public Board(Game game,Player player1, Player player2){
        this.game = game;
        this.player1 = player1;
        this.player2 = player2;
    }

}
