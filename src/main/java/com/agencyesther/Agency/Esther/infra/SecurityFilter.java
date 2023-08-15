package com.agencyesther.Agency.Esther.infra;

import com.agencyesther.Agency.Esther.domain.entities.MyUserPrincipal;
import com.agencyesther.Agency.Esther.domain.entities.User;
import com.agencyesther.Agency.Esther.infra.security.TokenService;
import com.agencyesther.Agency.Esther.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recoveryToken(request);

        if (token != null) {
            var login = tokenService.validateToken(token);
            User user = userRepository.findByLogin(login);
            MyUserPrincipal userPrincipal = new MyUserPrincipal(user);

            var authentication = new UsernamePasswordAuthenticationToken(userPrincipal,
                    null, userPrincipal.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }
}
