package fr.sacquet.association.web.services;

import fr.sacquet.association.web.bean.Association;
import fr.sacquet.association.web.bean.Statistique;
import fr.sacquet.association.web.dto.AssociationRepository;
import fr.sacquet.association.web.dto.StatistiqueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AssociationService {

    private AssociationRepository repository;
    private StatistiqueRepository repositoryStat;

    public Association getAssociation(String id) {
        Optional<Association> opAsso = repository.findById(id);
        if (opAsso.isPresent()) {
            return opAsso.get();
        } else {
            return new Association();
        }
    }

    public Iterable<Statistique> getAllStatistique() {
        return repositoryStat.findAll();
    }
}
