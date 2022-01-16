package fr.sacquet.association.web.services;

import fr.sacquet.association.web.bean.Categorie;
import fr.sacquet.association.web.dto.CategorieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ReferentielServiceTest {

    @Autowired
    private ReferentielService service;

    @Autowired
    private CategorieRepository repo;

    @Test
    void getAllCategorie() {
        // Given
        Categorie categorie = new Categorie();
        repo.save(categorie);

        // When
        Iterable<Categorie> categories = service.getAllCategorie();

        // Then
        assertNotNull(categories);
        assertEquals(1, ((Collection<?>) categories).size());
    }
}
