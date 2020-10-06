package com.bol.assignment;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyConstants {

    public enum PlayerStatus { ONLINE, OFFLINE, INGAME, LOOKINGFORGAME };
    public enum GameStatus { AWAITING_PLAYER, FULL, FINISHED };

    public static final int P1_START_INDEX = 0;
    public static final int P1_END_INDEX = 6;

    public static final int P2_START_INDEX = 7;
    public static final int P2_END_INDEX = 13;

    public static final int initKalaha = 0;
    public static final int limitBoard = 6;
    public static final int totalPits = 14;

    public static final List<Integer> initPits = Arrays.asList(6,6,6,6,6,6,initKalaha);

    public static final Map<Integer, Integer> map_OppositePits_limit6 = Map.of(
            0, 5,
            1, 4,
            2, 3,
            3, 2,
            4, 1,
            5, 0
    );

    public static final Map<Integer, Integer> map_OppositePits = Map.ofEntries(
            Map.entry(0,13),
            Map.entry(1,12),
            Map.entry(2,11),
            Map.entry(3,10),
            Map.entry(4,9),
            Map.entry(5,8),
            Map.entry(7,6),
            Map.entry(8,5),
            Map.entry(9,4),
            Map.entry(10,3),
            Map.entry(11,2),
            Map.entry(12,1)
    );




}
