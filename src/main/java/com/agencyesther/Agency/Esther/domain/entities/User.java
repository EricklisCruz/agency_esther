package com.agencyesther.Agency.Esther.domain.entities;


import com.agencyesther.Agency.Esther.domain.enums.UserRole;
import com.agencyesther.Agency.Esther.dto.UserRegistrationDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = -4653148537342860490L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(unique = true,nullable = false)
    private String email;
    @Column(nullable = false)
    @JsonIgnore
    private String password;
    private Integer age;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Phone> phone = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole userRole;
    private String lastPhone;

    public User(String name, String surname, String email, String password, String lastPhone, Integer age, UserRole userRole) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.lastPhone = lastPhone;
        this.age = age;
        this.userRole = userRole;

    }

    public User(UserRegistrationDTO userRegistrationDTO) {
        this.name = userRegistrationDTO.getName();
        this.surname = userRegistrationDTO.getSurname();
        this.email = userRegistrationDTO.getEmail();
        this.password = userRegistrationDTO.getPassword();
        this.lastPhone = userRegistrationDTO.getLastPhone();
        this.age = userRegistrationDTO.getAge();
        this.userRole = userRegistrationDTO.getUserRole();
    }

    public void addPhone(Phone phon) {
        phon.setNumber(lastPhone);
        phon.setUser(this);
        if (phone == null) phone.add(phon);
    }
}
