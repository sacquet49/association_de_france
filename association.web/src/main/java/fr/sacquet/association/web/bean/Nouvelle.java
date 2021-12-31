package fr.sacquet.association.web.bean;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "nouvelle")
@Data
public class Nouvelle {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    
    @Column(name = "date_creation")
    private Date dateCreation;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "titre")
    private String titre;
}
