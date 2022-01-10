package fr.sacquet.association.web.model;

import com.sun.istack.NotNull;
import lombok.Value;

@Value
public class NouvelleRequest {
    @NotNull
    private String titre;
    @NotNull
    private String description;
}
