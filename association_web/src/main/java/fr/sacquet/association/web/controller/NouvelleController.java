package fr.sacquet.association.web.controller;

import fr.sacquet.association.web.bean.Nouvelle;
import fr.sacquet.association.web.model.NouvelleRequest;
import fr.sacquet.association.web.services.NouvelleService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static fr.sacquet.association.web.conf.Constante.PRIVATE_API;
import static fr.sacquet.association.web.conf.Constante.PUBLIC_API;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class NouvelleController {

    private NouvelleService service;

    @GetMapping(value = PUBLIC_API + "/nouvelles")
    public Iterable<Nouvelle> getNouvelles() {
        return service.getNouvelles();
    }

    @PostMapping(value = PRIVATE_API + "/nouvelle")
    public Nouvelle createNouvelle(@Validated @RequestBody NouvelleRequest nouvelle) {
        return service.createNouvelle(nouvelle);
    }

    @DeleteMapping(value = PRIVATE_API + "/nouvelle/{id}")
    public void deleteNouvelle(@PathVariable Long id) {
        service.delete(id);
    }
}
