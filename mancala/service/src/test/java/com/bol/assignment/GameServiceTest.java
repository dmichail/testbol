package com.bol.assignment;


import com.bol.assignment.model.Game;
import com.bol.assignment.model.Player;
import com.bol.assignment.repository.GameRepository;
import com.bol.assignment.service.GameService;
import com.bol.assignment.service.GameStateService;
import com.bol.assignment.service.PlayerService;
import com.bol.assignment.service.PlayerStateService;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockSettings;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
public class GameServiceTest {


    @Mock
    private GameRepository gameRepositoryMock;

    private GameService gameService;
    private PlayerStateService playerStateService;
    private GameStateService gameStateService;
    private PlayerService playerService;


    @Before
    public void init(){
        gameService = new GameService(playerStateService, gameStateService, playerService);
    }

    @Test
    public void testCreateNewGame(){
        Player mockPlayer = mock(Player.class);

        Game result = gameService.createGame(mockPlayer);

        assertEquals(result.getPlayers().get(0).getName(), mockPlayer.getName());
        assertEquals(result.getPlayers().get(0).getStatus(), mockPlayer.getStatus());
        assertEquals(result.getGameStatus(), MyConstants.GameStatus.AWAITING_PLAYER);

        verify(gameRepositoryMock, times(1)).save(any(Game.class));

    }

}
