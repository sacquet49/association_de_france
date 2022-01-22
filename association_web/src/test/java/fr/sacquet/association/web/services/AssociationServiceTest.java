package fr.sacquet.association.web.services;

import fr.sacquet.association.web.bean.Association;
import fr.sacquet.association.web.dto.AssociationRepository;
import fr.sacquet.association.web.model.AssociationStat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

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

    @Test
    void getStatAssociation() {
        // Given
        Association association = new Association();
        association.setAdrsCodepostal("00");
        repo.save(association);

        association = new Association();
        association.setAdrsCodepostal("01");
        repo.save(association);

        association = new Association();
        association.setAdrsCodepostal("1");


        association = new Association();
        association.setAdrsCodepostal("13");
        repo.save(association);

        association = new Association();
        association.setAdrsCodepostal("49");
        repo.save(association);

        association = new Association();
        association.setAdrsCodepostal("3");
        repo.save(association);

        association = new Association();
        association.setAdrsCodepostal("In");
        repo.save(association);

        // When
        List<AssociationStat> associationStatList = service.statAssociation();

        // Then
        assertNotNull(associationStatList);
        assertEquals(3, associationStatList.size());
    }
}
