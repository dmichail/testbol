package com.bol.assignment;


import com.bol.assignment.model.Player;
import com.bol.assignment.model.PlayerState;
import com.bol.assignment.repository.PlayerStateRepository;
import com.bol.assignment.service.PlayerStateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.bol.assignment.MyConstants.initKalaha;
import static com.bol.assignment.MyConstants.initPits;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class PlayerStateServiceTest {




    @Mock
    private PlayerStateRepository playerStateRepositoryMock;

    private PlayerStateService playerStateService;


    @Before
    public void init() {playerStateService = new PlayerStateService();}

    @Test
    public void testCreateNewPlayerState(){
        Player mockPlayer = new Player(0, "test", MyConstants.PlayerStatus.OFFLINE);

        PlayerState result = playerStateService.createPlayerState(mockPlayer.getId());

        assertEquals(result.getPlayer(), mockPlayer);
        assertEquals(result.getKalaha(), MyConstants.initKalaha);
        assertEquals(result.getPits(), initPits);

    }



}
