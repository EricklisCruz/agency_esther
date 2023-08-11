package com.agencyesther.Agency.Esther.repositories;

import com.agencyesther.Agency.Esther.domain.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
