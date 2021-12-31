package fr.sacquet.association.web.controller;

import fr.sacquet.association.web.bean.Nouvelle;
import fr.sacquet.association.web.model.NouvelleRequest;
import fr.sacquet.association.web.services.NouvelleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class NouvelleController {

    private NouvelleService service;

    @GetMapping(value = "/open/api/nouvelles")
    public Iterable<Nouvelle> getNouvelles() {
        return service.getNouvelles();
    }

    @PostMapping(value = "/private/api/nouvelle")
    public Nouvelle createNouvelle(@RequestBody NouvelleRequest nouvelle) {
        return service.createNouvelle(nouvelle);
    }
}
