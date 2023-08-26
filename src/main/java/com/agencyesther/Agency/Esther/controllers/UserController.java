package com.agencyesther.Agency.Esther.controllers;

import com.agencyesther.Agency.Esther.domain.entities.User;
import com.agencyesther.Agency.Esther.dto.UserRegistrationDTO;
import com.agencyesther.Agency.Esther.dto.UsernameDTO;
import com.agencyesther.Agency.Esther.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ericklis
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserRegistrationDTO>> findAllUsers() {
        List<User> users = userService.findAllUsers();
        List<UserRegistrationDTO> usersDTO = users.stream().map(UserRegistrationDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(usersDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/username")
    public ResponseEntity<UsernameDTO> getCurrentUsername() {
        String username = userService.getCurrentUserName();
        return ResponseEntity.ok().body(new UsernameDTO(username));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<User> insertUser(@RequestBody @Validated UserRegistrationDTO userRegistrationDTO, UriComponentsBuilder uriComponentsBuilder) {
        User user = userService.fromDto(userRegistrationDTO);
        user = userService.insert(user);
        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.removeUser(id);
        return ResponseEntity.noContent().build();
    }
}
