package com.bol.assignment.service;


import com.bol.assignment.model.Pit;
import com.bol.assignment.model.Player;
import com.bol.assignment.repository.PitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PitService {

    @Autowired
    public PitRepository pitRepository;


    Pit addPit(Integer position, Player owner, Boolean isKalaha){
        Pit newPit = new Pit(position, owner, isKalaha);
        return pitRepository.save(newPit);
    }

    void updatePit(Long pitId, Integer stones){
        Pit foundPit = pitRepository.findById(pitId).get();
        foundPit.setStones(stones);
        pitRepository.save(foundPit);
    }

    void updateKalaha(Long pitId, Integer stonesKalaha) {
        Pit foundPit = pitRepository.findById(pitId).get();
        foundPit.setStonesKalaha(stonesKalaha);
        pitRepository.save(foundPit);
    }



}
