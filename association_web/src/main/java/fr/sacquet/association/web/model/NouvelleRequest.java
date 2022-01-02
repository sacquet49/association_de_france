package fr.sacquet.association.web.model;

import lombok.Value;

@Value
public class NouvelleRequest {
    private String titre;
    private String description;
}
