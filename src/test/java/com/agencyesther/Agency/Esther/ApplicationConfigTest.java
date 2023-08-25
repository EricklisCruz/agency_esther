package com.agencyesther.Agency.Esther;

import com.agencyesther.Agency.Esther.domain.entities.User;
import com.agencyesther.Agency.Esther.domain.enums.UserRole;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationConfigTest {

    public User commonUser() {
        User user = new User("Carol", "Castro",
                "carolcastro@gmail.com", "1234567",
                "87999776654", 25, UserRole.USER_ROLE);
        return user;
    }

    public User adminUser() {
        User user = new User("Carla", "Castanho",
                "carlacastanho@gmail.com", "1234567",
                "87999753345", 25, UserRole.ADMIN_ROLE);
        return user;
    }

    public List<User> users() {
        List<User> users = new ArrayList<>();
        users.add(commonUser());
        users.add(adminUser());
        return users;
    }

}
