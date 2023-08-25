package com.agencyesther.Agency.Esther.services;

import com.agencyesther.Agency.Esther.domain.entities.MyUserPrincipal;
import com.agencyesther.Agency.Esther.domain.entities.User;
import com.agencyesther.Agency.Esther.dto.UserRegistrationDTO;
import com.agencyesther.Agency.Esther.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author Ericklis
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAllUsers(){
        List<User> users = userRepository.findAll();
        return users;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found!!!"));
    }

    @Transactional
    public User insert(User user) {
        String bCrypPassword = new BCryptPasswordEncoder().encode(user.getPasswordd());
        user.setPasswordd(bCrypPassword);
        return userRepository.save(user);
    }

    public void removeUser(Long id) {
        boolean existUser = userRepository.existsById(id);
        if (!existUser) {
            throw new RuntimeException("User not found!!");
        } else {
            userRepository.deleteById(id);
        }

    }

    public String getCurrentUser() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username;
            return username = ((MyUserPrincipal) principal).user().getName();

        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public User fromDto(UserRegistrationDTO userRegistrationDTO) {
        return new User(userRegistrationDTO.getName(),
                userRegistrationDTO.getSurname(),
                userRegistrationDTO.getLogin(),
                userRegistrationDTO.getPassword(),
                userRegistrationDTO.getLastPhone(),
                userRegistrationDTO.getAge(),
                userRegistrationDTO.getUserRole()
        );
    }
}
