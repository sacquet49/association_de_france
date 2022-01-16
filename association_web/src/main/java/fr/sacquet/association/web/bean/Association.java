package fr.sacquet.association.web.bean;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "association")
@Data
public class Association {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;
    
    @Column(name = "id_ex")
    private String idEx;
    
    @Column(name = "siret")
    private String siret;
    
    @Column(name = "gestion")
    private String gestion;
    
    @Column(name = "date_creat")
    private Date dateCreat;
    
    @Column(name = "date_publi")
    private Date datePubli;
    
    @Column(name = "nature")
    private String nature;
    
    @Column(name = "groupement")
    private String groupement;
    
    @Column(name = "titre")
    private String titre;
    
    @Column(name = "objet")
    private String objet;
    
    @Column(name = "objet_social1")
    private String objetSocial1;
    
    @Column(name = "objet_social2")
    private String objetSocial2;
    
    @Column(name = "adr1")
    private String adr1;
    
    @Column(name = "adr2")
    private String adr2;
    
    @Column(name = "adr3")
    private String adr3;
    
    @Column(name = "adrs_codepostal")
    private String adrsCodepostal;
    
    @Column(name = "libcom")
    private String libcom;
    
    @Column(name = "adrs_codeinsee")
    private String adrsCodeinsee;
    
    @Column(name = "dir_civilite")
    private String dirCivilite;
    
    @Column(name = "telephone")
    private String telephone;
    
    @Column(name = "siteweb")
    private String siteweb;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "observation")
    private String observation;
    
    @Column(name = "position")
    private String position;
    
    @Column(name = "rup_mi")
    private String rupMi;
    
    @Column(name = "maj_time")
    private Date majTime;
}
