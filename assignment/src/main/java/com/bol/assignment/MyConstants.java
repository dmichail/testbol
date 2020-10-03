package com.bol.assignment;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

public class MyConstants {

    public enum PlayerStatus { ONLINE, OFFLINE, INGAME, LOOKINGFORGAME };
    public enum GameStatus { AWAITING_PLAYER, FULL, FINISHED };

    public static final int P1_START_INDEX = 0;
    public static final int P1_END_INDEX = 6;

    public static final int P2_START_INDEX = 7;
    public static final int P2_END_INDEX = 13;

    public static final List<Integer> initPits = Arrays.asList(6,6,6,6,6,6);
    public static final int initKalaha = 0;







}
