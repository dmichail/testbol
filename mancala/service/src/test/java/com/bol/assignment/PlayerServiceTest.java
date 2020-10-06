package com.bol.assignment;


import com.bol.assignment.dto.PlayerDto;
import com.bol.assignment.model.Player;
import com.bol.assignment.repository.PlayerRepository;
import com.bol.assignment.service.PlayerService;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepositoryMock;

    private PlayerService playerService;


    @Before
    public void init(){
        playerService = new PlayerService(playerRepositoryMock);
    }

    @Test
    public void testCreatePlayer(){
        PlayerDto playerDto = new PlayerDto("mike");

        Player result = playerService.addPlayer(playerDto);

        assertEquals(result.getName(), "mike");
        assertEquals(result.getStatus(), MyConstants.PlayerStatus.ONLINE);

        verify(playerRepositoryMock, times(1)).save(any(Player.class));
    }



}
