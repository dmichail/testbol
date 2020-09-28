package com.bol.assignment.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Pit {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="posIdx")
    private int position;
    @Column(name = "stones")
    private int stones;

    @ManyToOne
    @JoinColumn(name = "ownerId")
    private Player owner;
    @Column(name = "isKalaha")
    private boolean isKalaha;
    @Column(name = "stonesKalaha")
    @Nullable
    private int stonesKalaha;




}
