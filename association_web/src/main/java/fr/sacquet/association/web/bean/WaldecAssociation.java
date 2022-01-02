package fr.sacquet.association.web.bean;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "waldec_association")
@Data
public class WaldecAssociation {

    @SequenceGenerator(name = "waldec_association_id_seq",
            sequenceName = "waldec_association_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="waldec_association_id_seq")
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "id_ex")
    private String idEx;
    
    @Column(name = "siret")
    private String siret;
    
    @Column(name = "rup_mi")
    private String rupMi;
    
    @Column(name = "gestion")
    private String gestion;
    
    @Column(name = "date_creat")
    private Date dateCreat;
    
    @Column(name = "date_decla")
    private Date dateDecla;
    
    @Column(name = "date_publi")
    private Date datePubli;
    
    @Column(name = "date_disso")
    private Date dateDisso;
    
    @Column(name = "nature")
    private String nature;
    
    @Column(name = "groupement")
    private String groupement;
    
    @Column(name = "titre")
    private String titre;
    
    @Column(name = "titre_court")
    private String titreCourt;
    
    @Column(name = "objet")
    private String objet;
    
    @Column(name = "objet_social1")
    private String objetSocial1;
    
    @Column(name = "objet_social2")
    private String objetSocial2;
    
    @Column(name = "adrs_complement")
    private String adrsComplement;
    
    @Column(name = "adrs_numvoie")
    private String adrsNumvoie;
    
    @Column(name = "adrs_repetition")
    private String adrsRepetition;
    
    @Column(name = "adrs_typevoie")
    private String adrsTypevoie;
    
    @Column(name = "adrs_libvoie")
    private String adrsLibvoie;
    
    @Column(name = "adrs_distrib")
    private String adrsDistrib;
    
    @Column(name = "adrs_codeinsee")
    private String adrsCodeinsee;
    
    @Column(name = "adrs_codepostal")
    private String adrsCodepostal;
    
    @Column(name = "adrs_libcommune")
    private String adrsLibcommune;
    
    @Column(name = "adrg_declarant")
    private String adrgDeclarant;
    
    @Column(name = "adrg_complemid")
    private String adrgComplemid;
    
    @Column(name = "adrg_complemgeo")
    private String adrgComplemgeo;
    
    @Column(name = "adrg_libvoie")
    private String adrgLibvoie;
    
    @Column(name = "adrg_distrib")
    private String adrgDistrib;
    
    @Column(name = "adrg_codepostal")
    private String adrgCodepostal;
    
    @Column(name = "adrg_achemine")
    private String adrgAchemine;
    
    @Column(name = "adrg_pays")
    private String adrgPays;
    
    @Column(name = "dir_civilite")
    private String dirCivilite;
    
    @Column(name = "telephone")
    private String telephone;
    
    @Column(name = "siteweb")
    private String siteweb;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "publiweb")
    private String publiweb;
    
    @Column(name = "observation")
    private String observation;
    
    @Column(name = "position")
    private String position;
    
    @Column(name = "maj_time")
    private Timestamp majTime;
}
