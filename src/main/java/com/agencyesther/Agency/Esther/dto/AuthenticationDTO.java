package com.agencyesther.Agency.Esther.dto;

import com.agencyesther.Agency.Esther.domain.entities.User;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Ericklis
 */

public record AuthenticationDTO(String login, String password) {

}
