package com.bol.interviews.kalaha.utils;

import com.bol.interviews.kalaha.model.Pit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CoreUtils {


    public static List<Pit> convertPitDBtoList(String pitString, Integer Owner) {
        List<Pit> resultPit = new ArrayList<>();
        String[] split = pitString.split(";");
        for (int i=0; i < split.length; i++){
            resultPit.add(new Pit(Integer.valueOf(split[i]), i, Owner));

        }

        return resultPit;

    }

    public static String convertPitListToDB(List<Pit> pitList){
        return pitList.stream().map(ele -> ele.toString())
                .collect(Collectors.joining(";"));



    }


}

