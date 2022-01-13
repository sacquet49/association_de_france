package fr.sacquet.association.web.model;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class NouvelleRequest {

    @NotBlank(message = "Titre manquant")
    private String titre;

    @NotBlank(message = "Description manquante")
    private String description;
}
