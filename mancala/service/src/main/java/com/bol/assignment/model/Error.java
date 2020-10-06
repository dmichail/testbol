package com.bol.assignment.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Error implements Serializable {

    private Integer code;
    private String message;

    @Override
    public String toString(){
        return "{HttpCode: " + this.code + "\n"
                + "message: " + this.message + "\n"+"}";

    }
}
