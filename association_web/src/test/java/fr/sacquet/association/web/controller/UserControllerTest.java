package fr.sacquet.association.web.controller;

import fr.sacquet.association.web.bean.User;
import fr.sacquet.association.web.model.UserRequest;
import fr.sacquet.association.web.services.UserService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import java.io.FileInputStream;

import static fr.sacquet.association.web.conf.Constante.PRIVATE_API;
import static fr.sacquet.association.web.conf.Constante.PUBLIC_API;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserService userDetailsService;

    private static final String BASE_PATH = "src/test/resources/controller/user/";

    @Test
    @Order(1)
    void saveUser_useCase() throws Exception {
        // Setup
        FileInputStream fis = new FileInputStream(BASE_PATH + "userUseCase.json");
        String data = IOUtils.toString(fis, "UTF-8");
        FileInputStream fisR = new FileInputStream(BASE_PATH + "userUseCaseResponse.json");
        String dataR = IOUtils.toString(fisR, "UTF-8");

        // When and Then
        String response = this.mvc.perform(post(PRIVATE_API + "/user")
                        .with(jwt())
                        .content(data)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // Then
        assertEquals(dataR.trim(), response);
    }

    @Test
    @Order(2)
    void getAllUsers_useCase() throws Exception {
        // Setup
        FileInputStream fisR = new FileInputStream(BASE_PATH + "usersUseCaseResponse.json");
        String dataR = IOUtils.toString(fisR, "UTF-8");

        // Then
        String response = this.mvc.perform(get(PRIVATE_API + "/users")
                        .with(jwt())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // Then
        assertEquals(dataR.trim(), response);
    }

    @Test
    @Order(3)
    void deleteUser_useCase() throws Exception {
        // Setup
        FileInputStream fis = new FileInputStream(BASE_PATH + "userUseCase.json");
        String data = IOUtils.toString(fis, "UTF-8");

        // When
        this.mvc.perform(post(PRIVATE_API + "/user")
                        .with(jwt())
                        .content(data)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Then
        this.mvc.perform(delete(PRIVATE_API + "/user/1")
                        .with(jwt())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createAuthenticationToken_405Error() throws Exception {
        // Setup
        deleteAllUser();
        FileInputStream fis = new FileInputStream(BASE_PATH + "userUseCase.json");
        String data = IOUtils.toString(fis, "UTF-8");
        UserRequest ur = new UserRequest();
        ur.setUsername("test");
        ur.setPassword("1");
        userDetailsService.save(ur);

        // When and Then
        this.mvc.perform(get(PUBLIC_API + "/authenticate")
                        .content(data)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void createAuthenticationToken_useCase() throws Exception {
        // Setup
        deleteAllUser();
        FileInputStream fis = new FileInputStream(BASE_PATH + "userUseCase.json");
        String data = IOUtils.toString(fis, "UTF-8");
        UserRequest ur = new UserRequest();
        ur.setUsername("test");
        ur.setPassword("1");
        userDetailsService.save(ur);

        // When
        String response = this.mvc.perform(post(PUBLIC_API + "/authenticate")
                        .content(data)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // Then
        assertNotNull(response);
    }

    @Test
    void createAuthenticationToken_wrongPass() throws Exception {
        // Setup
        deleteAllUser();
        FileInputStream fis = new FileInputStream(BASE_PATH + "userUseCase.json");
        String data = IOUtils.toString(fis, "UTF-8");
        UserRequest ur = new UserRequest();
        ur.setUsername("test");
        ur.setPassword("x");
        userDetailsService.save(ur);

        // When
        String response = this.mvc.perform(post(PUBLIC_API + "/authenticate")
                        .content(data)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn().getResponse().getContentAsString();

        // Then
        assertEquals("", response);
    }

    @Test
    void createAuthenticationToken_wrongName() throws Exception {
        // Setup
        deleteAllUser();
        FileInputStream fis = new FileInputStream(BASE_PATH + "userUseCaseWrongName.json");
        String data = IOUtils.toString(fis, "UTF-8");
        UserRequest ur = new UserRequest();
        ur.setUsername("testx");
        ur.setPassword("1");
        userDetailsService.save(ur);

        // When
        String response = this.mvc.perform(post(PUBLIC_API + "/authenticate")
                        .content(data)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn().getResponse().getContentAsString();

        // Then
        assertEquals("", response);
    }

    @Test
    void createAuthenticationToken_noName() throws Exception {
        // Setup
        deleteAllUser();
        FileInputStream fis = new FileInputStream(BASE_PATH + "userUseCase.json");
        String data = IOUtils.toString(fis, "UTF-8");
        UserRequest ur = new UserRequest();
        ur.setUsername("");
        ur.setPassword("1");
        userDetailsService.save(ur);

        // When
        String response = this.mvc.perform(post(PUBLIC_API + "/authenticate")
                        .content(data)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn().getResponse().getContentAsString();

        // Then
        assertEquals("", response);
    }

    @Test
    void createAuthenticationToken_noPass() throws Exception {
        // Setup
        deleteAllUser();
        FileInputStream fis = new FileInputStream(BASE_PATH + "userUseCase.json");
        String data = IOUtils.toString(fis, "UTF-8");
        UserRequest ur = new UserRequest();
        ur.setUsername("test");
        ur.setPassword("");
        userDetailsService.save(ur);

        // When
        String response = this.mvc.perform(post(PUBLIC_API + "/authenticate")
                        .content(data)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn().getResponse().getContentAsString();

        // Then
        assertEquals("", response);
    }

    @Test
    void createAuthenticationToken_noNameRequest() throws Exception {
        // Setup
        deleteAllUser();
        FileInputStream fis = new FileInputStream(BASE_PATH + "userUseNoName.json");
        String data = IOUtils.toString(fis, "UTF-8");
        UserRequest ur = new UserRequest();
        ur.setUsername("");
        ur.setPassword("1");
        userDetailsService.save(ur);

        // When
        String response = this.mvc.perform(post(PUBLIC_API + "/authenticate")
                        .content(data)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();

        // Then
        assertEquals("", response);
    }

    @Test
    void createAuthenticationToken_noPassRequest() throws Exception {
        // Setup
        deleteAllUser();
        FileInputStream fis = new FileInputStream(BASE_PATH + "userUseNoPass.json");
        String data = IOUtils.toString(fis, "UTF-8");
        UserRequest ur = new UserRequest();
        ur.setUsername("test");
        ur.setPassword("");
        userDetailsService.save(ur);

        // When
        String response = this.mvc.perform(post(PUBLIC_API + "/authenticate")
                        .content(data)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();

        // Then
        assertEquals("", response);
    }

    @Test
    void saveUser_useCase_NoAuth() throws Exception {
        // Setup
        deleteAllUser();
        FileInputStream fis = new FileInputStream(BASE_PATH + "userUseCase.json");
        String data = IOUtils.toString(fis, "UTF-8");
        FileInputStream fisR = new FileInputStream(BASE_PATH + "userUseCaseResponse.json");
        String dataR = IOUtils.toString(fisR, "UTF-8");

        // When and Then
        this.mvc.perform(post(PRIVATE_API + "/user")
                        .content(data)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void saveUser_userNoPass() throws Exception {
        // Setup
        deleteAllUser();
        FileInputStream fis = new FileInputStream(BASE_PATH + "userUseNoPass.json");
        String data = IOUtils.toString(fis, "UTF-8");

        // When
        String response = this.mvc.perform(post(PRIVATE_API + "/user")
                        .with(jwt())
                        .content(data)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();

        // Then
        assertEquals("", response);
    }

    @Test
    void saveUser_userNoName() throws Exception {
        // Setup
        deleteAllUser();
        FileInputStream fis = new FileInputStream(BASE_PATH + "userUseNoName.json");
        String data = IOUtils.toString(fis, "UTF-8");

        // When
        String response = this.mvc.perform(post(PRIVATE_API + "/user")
                        .with(jwt())
                        .content(data)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();

        // Then
        assertEquals("", response);
    }

    private void deleteAllUser() {
        Iterable<User> users = userDetailsService.getAllUser();
        if (users != null) {
            users.forEach(user -> userDetailsService.deleteUser(user.getId()));
        }
    }
}
