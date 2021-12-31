package fr.sacquet.association.web.services;

import fr.sacquet.association.web.bean.Nouvelle;
import fr.sacquet.association.web.dto.NouvelleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NouvelleService {

    private NouvelleRepository repository;

    public Iterable<Nouvelle> getNouvelles() {
        return repository.findAll();
    }
}
