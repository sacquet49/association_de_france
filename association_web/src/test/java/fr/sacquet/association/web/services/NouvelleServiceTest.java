package fr.sacquet.association.web.services;

import fr.sacquet.association.web.bean.Nouvelle;
import fr.sacquet.association.web.model.NouvelleRequest;
import org.junit.jupiter.api.BeforeEach;
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
class NouvelleServiceTest {

    @Autowired
    private NouvelleService nouvelleService;

    @Test
    void getNouvelles() {
        NouvelleRequest nr = new NouvelleRequest("title", "desc");
        nouvelleService.createNouvelle(nr);
        Iterable<Nouvelle> news = nouvelleService.getNouvelles();
        assertNotNull(news);
        assertEquals(1, ((Collection<?>) news).size());
    }

    @Test
    void createNouvelle() {
        NouvelleRequest nr = new NouvelleRequest("title", "desc");
        nouvelleService.createNouvelle(nr);
        Iterable<Nouvelle> news = nouvelleService.getNouvelles();
        assertNotNull(news);
        assertEquals(1, ((Collection<?>) news).size());
    }

    @Test
    void deleteNouvelle() {
        NouvelleRequest nr = new NouvelleRequest("title", "desc");
        nouvelleService.createNouvelle(nr);
        Iterable<Nouvelle> news = nouvelleService.getNouvelles();

        assertNotNull(news);
        assertEquals(1, ((Collection<?>) news).size());

        news.forEach(nouvelle -> nouvelleService.delete(nouvelle.getId()));

        news = nouvelleService.getNouvelles();
        assertNotNull(news);
        assertEquals(0, ((Collection<?>) news).size());
    }

    @BeforeEach
    void before() {
        Iterable<Nouvelle> news = nouvelleService.getNouvelles();
        if (news != null) {
            news.forEach(nouvelle -> nouvelleService.delete(nouvelle.getId()));
        }
    }
}
