package fr.sacquet.association.web.controller;

import fr.sacquet.association.web.bean.Nouvelle;
import fr.sacquet.association.web.services.NouvelleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class NouvelleController {

    private NouvelleService service;

    @GetMapping(value = "/nouvelles")
    public Iterable<Nouvelle> getNouvelles() {
        return service.getNouvelles();
    }
}
