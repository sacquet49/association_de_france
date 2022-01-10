package fr.sacquet.association.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.sacquet.association.web.conf.JwtTokenUtil;
import fr.sacquet.association.web.model.UserRequest;
import fr.sacquet.association.web.services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AbstractControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public String authToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiUm9sZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfVVNFUl9DTEFTU0lDIn1dLCJleHAiOjE2NDE4NjIzNzgsImlhdCI6MTY0MTg0NDM3OH0.tFNtb4FQNo8GvVs5Ik2b6Gd1XpAtoU_CrlDYavys8O-Fi_HrOaFC80J-hCbtYRnU6f4Q4VoE4Bk_9UxzxK9VtQ";

    @BeforeAll
    void before() {
        UserRequest ur = new UserRequest();
        ur.setUsername("test");
        ur.setPassword("test");
        userService.save(ur);
        // jwtTokenUtil.getIssuedAtDateFromToken();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
