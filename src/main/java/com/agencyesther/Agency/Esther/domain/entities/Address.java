package com.agencyesther.Agency.Esther.domain.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
public class Address implements Serializable {
    @Serial
    private static final long serialVersionUID = 3439569796625003917L;

    private String address;
    private String area;
    private String number;
    private String zipCode;
    private String city;
    private String complement;
    private String state;

    public Address(String address, String area, String number, String zipCode, String city,
                   String complement, String state) {
        this.address = address;
        this.area = area;
        this.number = number;
        this.zipCode = zipCode;
        this.city = city;
        this.complement = complement;
        this.state = state;
    }
}
