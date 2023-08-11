package com.agencyesther.Agency.Esther.controllers;

import com.agencyesther.Agency.Esther.domain.entities.User;
import com.agencyesther.Agency.Esther.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @Transactional
    @PostMapping
    public ResponseEntity<User> insertUser(@RequestBody @Validated User user, UriComponentsBuilder uriComponentsBuilder) {
        user = userService.insert(user);
        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }
}
