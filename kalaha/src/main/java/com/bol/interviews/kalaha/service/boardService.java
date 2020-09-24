package com.bol.interviews.kalaha.service;


import com.bol.interviews.kalaha.dto.boardDto;
import com.bol.interviews.kalaha.dto.updateBoardDto;
import com.bol.interviews.kalaha.model.Board;
import com.bol.interviews.kalaha.model.Pit;
import com.bol.interviews.kalaha.model.Player;
import com.bol.interviews.kalaha.repository.boardRepository;
import com.bol.interviews.kalaha.utils.CoreUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

import static com.bol.interviews.kalaha.constants.constants.*;
import static com.bol.interviews.kalaha.constants.constants.Player2;
import static com.bol.interviews.kalaha.utils.CoreUtils.convertPitListToDB;

@Service
public class boardService {

    @Autowired
    public boardRepository boardRepository;
    public ConcurrentHashMap<Integer, Pit> boardMap;


    Optional<Board> getBoardbyId(Long boardId){
        return boardRepository.findById(boardId);
    }

    Board createNewBoard(Player player1, Player player2){
        Board board = new Board();
        board.setPits1(INIT_PITS);
        board.setPits2(INIT_PITS);
        board.setK1(INIT_STONES_KALHALA);
        board.setK2(INIT_STONES_KALHALA);
        board.setPlayer1(player1);
        board.setPlayer2(player2);

        boardRepository.save(board);

        return board;
    }

    void updateBoard(updateBoardDto updateBoardDto){
        Board currBoard = getBoardbyId(updateBoardDto.getBoardId()).get();

        currBoard.setPits1(convertPitListToDB(getPlayer1Pits(currBoard.getBoardId())));
        currBoard.setPits2(convertPitListToDB(getPlayer2Pits(currBoard.getBoardId())));
        currBoard.setK1(updateBoardDto.getKalahaP1());
        currBoard.setK2(updateBoardDto.getKalahaP2());

        boardRepository.save(currBoard);

    }

    List<Pit> getPlayer1Pits(Long boardId) {
        Optional<Board> current_board = boardRepository.findById(boardId);

        return CoreUtils.convertPitDBtoList(current_board.get().getPits1(), Player1);
    }

    List<Pit> getPlayer2Pits(Long boardId) {
        Optional<Board> current_board = boardRepository.findById(boardId);

        return CoreUtils.convertPitDBtoList(current_board.get().getPits2(), Player2);
    }











    //Hashmap to represent the board index - Pit
    public ConcurrentHashMap<Integer, Pit> initBoard(){
        ConcurrentHashMap tempBoard = new ConcurrentHashMap();

        IntStream.rangeClosed(Player1_start_index, Player1_end_index)
                .forEach(x ->
                        tempBoard.put(x, new Pit(x, Player1))
                );

        IntStream.rangeClosed(Player2_start_index, Player2_end_index)
                .forEach(x ->
                        tempBoard.put(x, new Pit(x, Player2))
                );

        return tempBoard;
    }
    public ConcurrentHashMap<Integer, Pit> getBoardMap() {
        return boardMap;
    }












}
