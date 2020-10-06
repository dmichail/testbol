package com.bol.assignment.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class loggedInUser {

    private Long id;
    private String name;

}