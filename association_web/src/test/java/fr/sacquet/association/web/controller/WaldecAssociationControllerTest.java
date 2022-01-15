package fr.sacquet.association.web.controller;

import fr.sacquet.association.web.bean.WaldecAssociation;
import fr.sacquet.association.web.services.WaldecAssociationService;
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
class WaldecAssociationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WaldecAssociationService service;

    private static final String BASE_PATH = "src/test/resources/controller/waldecAssociation/";

    @Test
    void getWaldecAssociation_useCase() throws Exception {
        // Setup
        FileInputStream fis = new FileInputStream(BASE_PATH + "wassoReturn.json");
        String data = IOUtils.toString(fis, "UTF-8");
        WaldecAssociation association = new WaldecAssociation();
        association.setId("1");

        // Given
        when(service.getWaldecAssociation("1")).thenReturn(association);

        // When and Then
        this.mvc.perform(get(PUBLIC_API + "/association_waldec/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(data.trim()));
    }

    @Test
    void getWaldecAssociation_wrongId() throws Exception {
        // Setup
        FileInputStream fis = new FileInputStream(BASE_PATH + "wassoReturnWrongId.json");
        String data = IOUtils.toString(fis, "UTF-8");

        // Given
        when(service.getWaldecAssociation("1")).thenReturn(new WaldecAssociation());

        // When and Then
        this.mvc.perform(get(PUBLIC_API + "/association_waldec/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(data.trim()));
    }
}
