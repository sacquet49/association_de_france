package fr.sacquet.association.web.model;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
