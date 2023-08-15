package com.agencyesther.Agency.Esther.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
/**
 * @author Ericklis
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Phone implements Serializable {

    private final static String BRAZIL_NUMBER = "^\\d{10,11}";
    @Serial
    private static final long serialVersionUID = 2880150552917432841L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    @Pattern(regexp = BRAZIL_NUMBER)
    private String number;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Phone(String number, User user) {
        this.number = number;
        this.user = user;
    }
}
