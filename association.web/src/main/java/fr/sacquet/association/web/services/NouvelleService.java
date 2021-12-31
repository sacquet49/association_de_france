package fr.sacquet.association.web.services;

import fr.sacquet.association.web.bean.Nouvelle;
import fr.sacquet.association.web.dto.NouvelleRepository;
import fr.sacquet.association.web.model.NouvelleRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class NouvelleService {

    private NouvelleRepository repository;

    public Iterable<Nouvelle> getNouvelles() {
        return repository.findAll();
    }

    public Nouvelle createNouvelle(NouvelleRequest nouvelle) {
        Date date = new Date(System.currentTimeMillis());
        Nouvelle nouvelleDto = new Nouvelle();
        nouvelleDto.setTitre(nouvelle.getTitre());
        nouvelleDto.setDescription(nouvelle.getDescription());
        nouvelleDto.setDateCreation(date);
        return repository.save(nouvelleDto);
    }
}
