package fr.sacquet.association.web.services;

import fr.sacquet.association.web.bean.Association;
import fr.sacquet.association.web.dto.AssociationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AssociationServiceTest {

    @Autowired
    private AssociationService service;

    @Autowired
    private AssociationRepository repo;

    @Test
    void getAssociation() {
        // Given
        Association association = new Association();
        association = repo.save(association);

        // When
        Association asso = service.getAssociation(association.getId());

        // Then
        assertNotNull(asso);
        assertEquals(association.getId(), asso.getId());
    }
}
