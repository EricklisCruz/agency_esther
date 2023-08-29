package com.agencyesther.Agency.Esther.services;

import com.agencyesther.Agency.Esther.domain.entities.Address;
import com.agencyesther.Agency.Esther.domain.entities.MyUserPrincipal;
import com.agencyesther.Agency.Esther.domain.entities.Phone;
import com.agencyesther.Agency.Esther.domain.entities.User;
import com.agencyesther.Agency.Esther.dto.AddressDTO;
import com.agencyesther.Agency.Esther.dto.UserRegistrationDTO;
import com.agencyesther.Agency.Esther.repositories.PhoneRepository;
import com.agencyesther.Agency.Esther.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final PhoneRepository phoneRepository;

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
        user = userRepository.save(user);
        phoneRepository.save(insertPhone(user));
        return user;
    }

    private Phone insertPhone(User user) {
        return new Phone(user.getLastPhone(), user);
    }

    public void removeUser(Long id) {
        boolean existUser = userRepository.existsById(id);
        if (!existUser) {
            throw new RuntimeException("User not found!!");
        } else {
            userRepository.deleteById(id);
        }

    }

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ((MyUserPrincipal) principal).user();
        return user;
    }

    public String getCurrentUserName() {
        try {
            String username;
            return username = getCurrentUser().getName();

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
                userRegistrationDTO.getUserRole(),
                new Address(userRegistrationDTO.getAddress().getAddress(), userRegistrationDTO.getAddress().getArea(),
                        userRegistrationDTO.getAddress().getNumber(), userRegistrationDTO.getAddress().getZipCode(),
                        userRegistrationDTO.getAddress().getCity(), userRegistrationDTO.getAddress().getComplement(),
                        userRegistrationDTO.getAddress().getState())
        );
    }

    public Address addressDTO(AddressDTO addressDTO) {
        return new Address(
                addressDTO.getAddress(),
                addressDTO.getArea(),
                addressDTO.getNumber(),
                addressDTO.getZipCode(),
                addressDTO.getCity(),
                addressDTO.getComplement(),
                addressDTO.getState()
        );
    }
}
