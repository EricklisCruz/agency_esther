package com.agencyesther.Agency.Esther.repositories;

import com.agencyesther.Agency.Esther.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
