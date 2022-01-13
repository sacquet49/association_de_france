package fr.sacquet.association.web.controller;

import fr.sacquet.association.web.bean.Nouvelle;
import fr.sacquet.association.web.model.NouvelleRequest;
import fr.sacquet.association.web.services.NouvelleService;
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
import java.util.ArrayList;
import java.util.List;

import static fr.sacquet.association.web.conf.Constante.PRIVATE_API;
import static fr.sacquet.association.web.conf.Constante.PUBLIC_API;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class NouvelleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private NouvelleService service;

    private static final String BASE_PATH = "src/test/resources/controller/nouvelle/";

    @Test
    void getNouvelles() throws Exception {
        // Setup
        FileInputStream fis = new FileInputStream(BASE_PATH + "nouvelleReturn.json");
        String data = IOUtils.toString(fis, "UTF-8");
        List<Nouvelle> nouvelleList = new ArrayList<>();
        Nouvelle news = new Nouvelle();
        news.setDescription("description");
        news.setTitre("title");
        news.setId(1);
        nouvelleList.add(news);

        // Given
        when(service.getNouvelles()).thenReturn(nouvelleList);

        // When and Then
        this.mvc.perform(get(PUBLIC_API + "/nouvelles")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(data.trim()));
    }

    @Test
    void createNouvelle_noAuth() throws Exception {
        // Setup
        FileInputStream fis = new FileInputStream(BASE_PATH + "nouvelleReturn.json");
        String data = IOUtils.toString(fis, "UTF-8");
        Nouvelle news = new Nouvelle();
        news.setDescription("description");
        news.setTitre("title");
        news.setId(1);

        // Given
        when(service.createNouvelle(new NouvelleRequest("", ""))).thenReturn(news);

        // When and Then
        this.mvc.perform(post(PRIVATE_API + "/nouvelle")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void createNouvelle_useCase() throws Exception {
        // Setup
        FileInputStream fis = new FileInputStream(BASE_PATH + "nouvelleCreateReturn.json");
        String data = IOUtils.toString(fis, "UTF-8");
        FileInputStream fisC = new FileInputStream(BASE_PATH + "nouvelleCreate.json");
        String dataC = IOUtils.toString(fisC, "UTF-8");
        Nouvelle news = new Nouvelle();
        news.setDescription("description1");
        news.setTitre("title1");
        news.setId(1);

        // Given
        when(service.createNouvelle(new NouvelleRequest("title1", "description1"))).thenReturn(news);

        // When
        String dataResponse = this.mvc.perform(post(PRIVATE_API + "/nouvelle")
                        .with(jwt())
                        .content(dataC)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // Then
        assertEquals(data.trim(), dataResponse.trim());
    }

    @Test
    void createNouvelle_noTitle() throws Exception {
        // Setup
        FileInputStream fisC = new FileInputStream(BASE_PATH + "nouvelleCreateNoTitle.json");
        String dataC = IOUtils.toString(fisC, "UTF-8");
        Nouvelle news = new Nouvelle();
        news.setDescription("");
        news.setTitre("title1");
        news.setId(1);

        // Given
        when(service.createNouvelle(new NouvelleRequest("title1", "description1"))).thenReturn(news);

        // When and Then
        this.mvc.perform(post(PRIVATE_API + "/nouvelle")
                        .with(jwt())
                        .content(dataC)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }

    @Test
    void createNouvelle_noDescription() throws Exception {
        // Setup
        FileInputStream fisC = new FileInputStream(BASE_PATH + "nouvelleCreateNoDescription.json");
        String dataC = IOUtils.toString(fisC, "UTF-8");
        Nouvelle news = new Nouvelle();
        news.setDescription("description1");
        news.setTitre("");
        news.setId(1);

        // Given
        when(service.createNouvelle(new NouvelleRequest("title1", "description1"))).thenReturn(news);

        // When and Then
        this.mvc.perform(post(PRIVATE_API + "/nouvelle")
                        .with(jwt())
                        .content(dataC)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }

    @Test
    void deleteNouvelle_notAuth() throws Exception {
        // When and Then
        this.mvc.perform(delete(PRIVATE_API + "/nouvelle/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void deleteNouvelle_useCase() throws Exception {
        // When and Then
        this.mvc.perform(delete(PRIVATE_API + "/nouvelle/1")
                        .with(jwt()))
                .andExpect(status().isOk());
    }
}
