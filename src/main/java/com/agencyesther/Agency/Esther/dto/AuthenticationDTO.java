package com.agencyesther.Agency.Esther.dto;

import com.agencyesther.Agency.Esther.domain.entities.User;
import lombok.Getter;

@Getter
public class AuthenticationDTO {

    private String login;
    private String password;

    public AuthenticationDTO(User user) {
        this.login = user.getEmail();
        this.password = user.getPassword();
    }
}
