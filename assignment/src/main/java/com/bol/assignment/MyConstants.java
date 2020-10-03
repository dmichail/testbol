package com.bol.assignment;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

public class MyConstants {

    public enum PlayerStatus { ONLINE, OFFLINE, INGAME, LOOKINGFORGAME };
    public enum GameStatus { NEWGAME, AWAITING_PLAYER, FULL };

    public static final int PLAYER1_START_INDEX = 0;
    public static final int Player1_end_kalaha_index = 6;

    public static final int Player2_start_index = 7;
    public static final int Player2_end_kalhala_index = 13;

    public static final List<Integer> initPits = Arrays.asList(6,6,6,6,6,6);
    public static final int initKalaha = 0;







}
