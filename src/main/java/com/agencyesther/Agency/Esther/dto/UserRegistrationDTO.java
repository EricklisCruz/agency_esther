package com.agencyesther.Agency.Esther.dto;

import com.agencyesther.Agency.Esther.domain.entities.User;
import com.agencyesther.Agency.Esther.domain.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * @author Ericklis
 */
@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Email
    private String login;
    @NotBlank
    private String password;
    @Pattern(regexp = "^\\d{10,11}")
    private String lastPhone;
    private Integer age;
    private UserRole userRole;

    public UserRegistrationDTO(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.login = user.getLogin();
        this.password = user.getPasswordd();
        this.lastPhone = user.getLastPhone();
        this.age = user.getAge();
        this.userRole = user.getUserRole();
    }
}
