package fr.sacquet.association.web.services;

import fr.sacquet.association.web.bean.WaldecAssociation;
import fr.sacquet.association.web.dto.WaldecAssociationRepository;
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
class WaldecAssociationServiceTest {

    @Autowired
    private WaldecAssociationService service;

    @Autowired
    private WaldecAssociationRepository repo;

    @Test
    void getWaldecAssociation() {
        // Given
        WaldecAssociation association = new WaldecAssociation();
        association = repo.save(association);

        // When
        WaldecAssociation waldecAssociation = service.getWaldecAssociation(association.getId());

        // Then
        assertNotNull(waldecAssociation);
        assertEquals(association.getId(), waldecAssociation.getId());
    }
}
