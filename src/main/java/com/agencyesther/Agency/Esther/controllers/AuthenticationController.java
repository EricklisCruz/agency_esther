package com.agencyesther.Agency.Esther.controllers;

import com.agencyesther.Agency.Esther.domain.entities.MyUserPrincipal;
import com.agencyesther.Agency.Esther.dto.AuthenticationDTO;
import com.agencyesther.Agency.Esther.dto.LoginResponseDTO;
import com.agencyesther.Agency.Esther.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
        Authentication auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        var token = tokenService.generateToken((MyUserPrincipal) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
