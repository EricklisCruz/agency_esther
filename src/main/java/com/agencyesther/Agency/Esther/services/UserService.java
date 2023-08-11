package com.agencyesther.Agency.Esther.services;

import com.agencyesther.Agency.Esther.domain.entities.User;
import com.agencyesther.Agency.Esther.dto.UserRegistrationDTO;
import com.agencyesther.Agency.Esther.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAllUsers(){
        List<User> users = userRepository.findAll();
        return users;
    }

    @Transactional
    public User insert(User user) {
        String bCrypPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(bCrypPassword);
        return userRepository.save(user);
    }

    public User fromDto(UserRegistrationDTO userRegistrationDTO) {
        return new User(userRegistrationDTO.getName(),
                userRegistrationDTO.getSurname(),
                userRegistrationDTO.getEmail(),
                userRegistrationDTO.getPassword(),
                userRegistrationDTO.getLastPhone(),
                userRegistrationDTO.getAge(),
                userRegistrationDTO.getUserRole()
        );
    }
}
