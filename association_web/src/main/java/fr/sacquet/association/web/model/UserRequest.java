package fr.sacquet.association.web.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRequest {

    @NotBlank(message = "Username manquant")
    private String username;

    @NotBlank(message = "Password manquant")
    private String password;
}
