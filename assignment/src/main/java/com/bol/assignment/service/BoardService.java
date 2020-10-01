package com.bol.assignment.service;


import com.bol.assignment.exceptions.board.BoardNotFoundException;
import com.bol.assignment.model.Board;
import com.bol.assignment.model.Player;
import com.bol.assignment.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    public BoardRepository boardRepository;

    public Board createBoard(Player player1, Player player2){
        Board board = new Board(player1, player2);
        boardRepository.save(board);

        return board;
    }

    public Board getBoardById(Long boardId){
        return boardRepository.findById(boardId).orElseThrow(() -> new BoardNotFoundException(boardId));
    }








}
