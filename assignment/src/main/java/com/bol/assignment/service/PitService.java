package com.bol.assignment.service;


import com.bol.assignment.exceptions.pit.PitNotFoundException;
import com.bol.assignment.model.Board;
import com.bol.assignment.model.Pit;
import com.bol.assignment.model.Player;
import com.bol.assignment.repository.PitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PitService {

    @Autowired
    public PitRepository pitRepository;


    public Pit addPit(Integer position, Board board, Boolean isKalaha){
        Pit newPit = new Pit(position, board, isKalaha);
        return pitRepository.save(newPit);
    }
    
    public void updatePit(Long pitId, Integer stones){
        Pit foundPit = pitRepository.findById(pitId).get();
        foundPit.setStones(stones);
        pitRepository.save(foundPit);
    }

    public void updateKalaha(Long pitId, Integer stonesKalaha) {
        Pit foundPit = pitRepository.findById(pitId).get();
        foundPit.setStonesKalaha(stonesKalaha);
        pitRepository.save(foundPit);
    }

    public Pit getPitById(Long pitId){
        Pit foundPit = pitRepository.findById(pitId).orElseThrow(() -> new PitNotFoundException(pitId));

        return foundPit;
    }



}
