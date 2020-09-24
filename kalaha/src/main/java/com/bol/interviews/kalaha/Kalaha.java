package com.bol.interviews.kalaha;

import com.bol.interviews.kalaha.model.Board;
import com.bol.interviews.kalaha.model.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.security.util.ByteArrayLexOrder;

@SpringBootApplication
public class Kalaha {

    public static void main(String[] args) {
        try {
            SpringApplication.run(Kalaha.class, args);
        }catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        }

        Player player1 = new Player("Mike");
        Player player2 = new Player("damon");










    }

}
