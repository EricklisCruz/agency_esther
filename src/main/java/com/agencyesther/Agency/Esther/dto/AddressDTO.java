package com.agencyesther.Agency.Esther.dto;

import com.agencyesther.Agency.Esther.domain.entities.Address;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {

    private static final String ZIP_CODE = "^\\d{5}-\\d{3}";
    private static final String BRAZIL_STATE = "[A-Z][A-Z]";

    @NotNull
    private String address;
    @NotNull
    private String area;
    @NotNull
    private String number;
    @NotNull
    @Pattern(regexp = ZIP_CODE)
    private String zipCode;
    @NotNull
    private String city;
    private String complement;
    @NotNull
    @Pattern(regexp = BRAZIL_STATE)
    private String state;

    public AddressDTO(Address address) {
        this.address = address.getAddress();
        this.area = address.getArea();
        this.number = address.getNumber();
        this.zipCode = address.getZipCode();
        this.city = address.getCity();
        this.complement = address.getComplement();
        this.state = address.getState();
    }
}
