package com.agencyesther.Agency.Esther.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsernameDTO {

    private String name;
    public UsernameDTO(String name) {
        this.name = name;
    }
}
