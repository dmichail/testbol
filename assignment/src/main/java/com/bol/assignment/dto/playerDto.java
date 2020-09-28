package com.bol.assignment.dto;


import com.bol.assignment.constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class playerDto implements Serializable {

    private long id;
    private String name;
    private constants.PlayerStatus status;

}
