package fr.sacquet.association.web.services;

import fr.sacquet.association.web.bean.User;
import fr.sacquet.association.web.model.UserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    void loadUserByUsername() {
        // Given
        UserRequest ur = new UserRequest();
        ur.setUsername("login");
        ur.setPassword("pass");
        service.save(ur);

        // When
        UserDetails userR = service.loadUserByUsername("login");

        // Then
        assertNotNull(userR);
    }

    @Test
    void save() {
        // Given
        UserRequest ur = new UserRequest();
        ur.setUsername("login");
        ur.setPassword("pass");

        // When
        User user = service.save(ur);

        // Then
        assertNotNull(user);
    }

    @Test
    void getAllUser() {
        // Given
        UserRequest ur = new UserRequest();
        ur.setUsername("login");
        ur.setPassword("pass");
        service.save(ur);

        // When
        Iterable<User> users = service.getAllUser();

        // Then
        assertNotNull(users);
        assertEquals(1, ((Collection<?>) users).size());
    }

    @Test
    void deleteUser() {
        // Given
        UserRequest ur = new UserRequest();
        ur.setUsername("login");
        ur.setPassword("pass");
        User user = service.save(ur);

        // When
        service.deleteUser(user.getId());
        Iterable<User> users = service.getAllUser();

        // Then
        assertNotNull(users);
        assertEquals(0, ((Collection<?>) users).size());
    }

    @BeforeEach
    void before() {
        Iterable<User> users = service.getAllUser();
        if (users != null) {
            users.forEach(user -> service.deleteUser(user.getId()));
        }
    }
}
