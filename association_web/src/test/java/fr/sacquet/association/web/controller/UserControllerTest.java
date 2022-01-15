package fr.sacquet.association.web.controller;

import fr.sacquet.association.web.model.UserRequest;
import fr.sacquet.association.web.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import java.io.FileInputStream;

import static fr.sacquet.association.web.conf.Constante.PUBLIC_API;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserService userDetailsService;

    private static final String BASE_PATH = "src/test/resources/controller/user/";

    @Test
    void createAuthenticationToken_405Error() throws Exception {
        // Setup
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
        FileInputStream fis = new FileInputStream(BASE_PATH + "userUseCase.json");
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
    void saveUser_useCase() {
    }

    @Test
    void getAllUsers_useCase() {
    }

    @Test
    void deleteUser_useCase() {
    }
}
