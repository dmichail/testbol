package com.bol.assignment;

import com.bol.assignment.controller.GameController;
import com.bol.assignment.controller.PlayerController;
import com.bol.assignment.model.Player;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ServiceApplicationTests {

    @Autowired
    public PlayerController playerController;

    @Autowired
    public GameController gameController;


    @Test
    public void contextLoads() throws Exception{
        assertThat(playerController).isNotNull();
        assertThat(gameController).isNotNull();
    }



}
