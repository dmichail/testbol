package com.bol.assignment.service;


import com.bol.assignment.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    public GameRepository gameRepository;


}
