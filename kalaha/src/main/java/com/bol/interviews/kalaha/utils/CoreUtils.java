package com.bol.interviews.kalaha.utils;

import com.bol.interviews.kalaha.model.Pit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CoreUtils {


    public static List<Integer> convertPitDBtoList(String pitString) {
        return Arrays.stream(pitString.split(";"))
                .map(x -> Integer.valueOf(x))
                .collect(Collectors.toList());
    }

    public static String convertPitListToDB(List<Integer> intList){
        return intList.stream().map(ele -> ele.toString())
                .collect(Collectors.joining(";"));



    }


}

