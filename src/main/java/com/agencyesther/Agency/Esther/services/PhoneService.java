package com.agencyesther.Agency.Esther.services;

import com.agencyesther.Agency.Esther.domain.entities.Phone;
import com.agencyesther.Agency.Esther.repositories.PhoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneService {

    private final PhoneRepository phoneRepository;

    public Phone insert(Phone phone) {
        return phoneRepository.save(phone);
    }
}
