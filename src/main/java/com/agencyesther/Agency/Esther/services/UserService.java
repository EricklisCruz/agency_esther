package com.agencyesther.Agency.Esther.services;

import com.agencyesther.Agency.Esther.domain.entities.User;
import com.agencyesther.Agency.Esther.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User insert(User user) {
        String bCrypPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(bCrypPassword);
        return userRepository.save(user);
    }
}
