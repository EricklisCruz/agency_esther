package com.agencyesther.Agency.Esther.services;

import com.agencyesther.Agency.Esther.ApplicationConfigTest;
import com.agencyesther.Agency.Esther.domain.entities.User;
import com.agencyesther.Agency.Esther.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest extends ApplicationConfigTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void shouldRemoveUser() {

        Long userId = 3L;

        Boolean user = Long.parseLong("3") == userId;
        when(userRepository.existsById(ArgumentMatchers.eq(userId))).thenReturn(user);
        userService.removeUser(userId);
        verify(userRepository, times(1)).deleteById(ArgumentMatchers.eq(userId));
    }

    @Test
    public void shouldThrowAnExceptionWhenUserNotFound() {
        assertThrows(RuntimeException.class, () -> this.userService.removeUser(7L));
    }

    @Test
    public void shouldInsertUser() {
        when(userRepository.save(any(User.class))).thenReturn(commonUser());
        userService.insert(commonUser());
        when(userRepository.findAll()).thenReturn(List.of(commonUser()));
        int size = userService.findAllUsers().size();
        verify(userRepository, times(1)).save(ArgumentMatchers.eq(commonUser()));
        assertEquals(1, size);
    }

    @Test
    public void shouldReturnAllUsers() {
        when(userRepository.findAll()).thenReturn(users());
        List users = userService.findAllUsers();
        int size = users.size();
        assertEquals(2, size);
    }
}
