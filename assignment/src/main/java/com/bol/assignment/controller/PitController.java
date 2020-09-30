package com.bol.assignment.controller;


import com.bol.assignment.dto.pit.PitCreateDto;
import com.bol.assignment.model.Pit;
import com.bol.assignment.service.PitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PitController {

    @Autowired
    public PitService pitService;

    ModelMapper modelMapper = new ModelMapper();


    @PostMapping("/pits/")
    public Pit createPit(@RequestBody PitCreateDto pitCreateDto){
        Pit createdPit = pitService.addPit(pitCreateDto.getPosition(), pitCreateDto.getOwner(), pitCreateDto.getIsKalaha());
        return createdPit;
    }


    @GetMapping("/pits/{id}")
    public Pit getPitById(@PathVariable(value = "id", required = true) Long id){
        Pit found = pitService.getPitById(id);

        return found;
    }











}
