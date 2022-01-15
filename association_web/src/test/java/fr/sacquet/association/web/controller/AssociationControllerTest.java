package fr.sacquet.association.web.controller;

import fr.sacquet.association.web.bean.Association;
import fr.sacquet.association.web.services.AssociationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import java.io.FileInputStream;

import static fr.sacquet.association.web.conf.Constante.PUBLIC_API;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AssociationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AssociationService service;

    private static final String BASE_PATH = "src/test/resources/controller/association/";

    @Test
    void getWaldecAssociation_useCase() throws Exception {
        // Setup
        FileInputStream fis = new FileInputStream(BASE_PATH + "assoReturn.json");
        String data = IOUtils.toString(fis, "UTF-8");
        Association association = new Association();
        association.setId("1");

        // Given
        when(service.getAssociation("1")).thenReturn(association);

        // When and Then
        this.mvc.perform(get(PUBLIC_API + "/association/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(data.trim()));
    }

    @Test
    void getWaldecAssociation_wrongId() throws Exception {
        // Setup
        FileInputStream fis = new FileInputStream(BASE_PATH + "assoReturnWrongId.json");
        String data = IOUtils.toString(fis, "UTF-8");

        // Given
        when(service.getAssociation("1")).thenReturn(new Association());

        // When and Then
        this.mvc.perform(get(PUBLIC_API + "/association/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(data.trim()));
    }
}
