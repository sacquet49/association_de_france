package fr.sacquet.association.web.controller;

import fr.sacquet.association.web.bean.Categorie;
import fr.sacquet.association.web.bean.SousCategorie;
import fr.sacquet.association.web.services.ReferentielService;
import org.jetbrains.annotations.NotNull;
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

import static fr.sacquet.association.web.conf.Constante.PUBLIC_API;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ReferentielControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReferentielService service;

    private static final String BASE_PATH = "src/test/resources/controller/referentiel/";

    @Test
    void getCategories_useCase() throws Exception {
        // Setup
        FileInputStream fis = new FileInputStream(BASE_PATH + "categoriesReturn.json");
        String data = IOUtils.toString(fis, "UTF-8");
        List<Categorie> categorieList = getCategories();

        // Given
        when(service.getAllCategorie()).thenReturn(categorieList);

        // When and Then
        this.mvc.perform(get(PUBLIC_API + "/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(data.trim()));
    }

    @NotNull
    private List<Categorie> getCategories() {
        List<Categorie> categorieList = new ArrayList<>();
        Categorie categorie = new Categorie();
        categorie.setId(1L);
        categorie.setTitre("cat");
        categorieList.add(categorie);
        List<SousCategorie> sousCateList = new ArrayList<>();
        SousCategorie sc = new SousCategorie();
        sc.setCategorie(categorie);
        sc.setTitre("sc");
        sousCateList.add(sc);
        categorie.setSousCategories(sousCateList);
        return categorieList;
    }
}
