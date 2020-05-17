<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * WaldecAssociation
 *
 * @ORM\Table(name="waldec_association", indexes={@ORM\Index(name="idx_waldec_asso", columns={"adrs_codepostal"}), @ORM\Index(name="idx2_waldec_asso", columns={"objet_social1", "objet_social2"})})
 * @ORM\Entity
 */
class WaldecAssociation
{
    /**
     * @var string
     *
     * @ORM\Column(name="id", type="string", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="SEQUENCE")
     * @ORM\SequenceGenerator(sequenceName="waldec_association_id_seq", allocationSize=1, initialValue=1)
     */
    private $id;

    /**
     * @var string|null
     *
     * @ORM\Column(name="id_ex", type="string", nullable=true)
     */
    private $idEx;

    /**
     * @var string|null
     *
     * @ORM\Column(name="siret", type="string", nullable=true)
     */
    private $siret;

    /**
     * @var string|null
     *
     * @ORM\Column(name="rup_mi", type="string", nullable=true)
     */
    private $rupMi;

    /**
     * @var string|null
     *
     * @ORM\Column(name="gestion", type="string", nullable=true)
     */
    private $gestion;

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="date_creat", type="date", nullable=true)
     */
    private $dateCreat;

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="date_decla", type="date", nullable=true)
     */
    private $dateDecla;

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="date_publi", type="date", nullable=true)
     */
    private $datePubli;

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="date_disso", type="date", nullable=true)
     */
    private $dateDisso;

    /**
     * @var string|null
     *
     * @ORM\Column(name="nature", type="string", nullable=true)
     */
    private $nature;

    /**
     * @var string|null
     *
     * @ORM\Column(name="groupement", type="string", nullable=true)
     */
    private $groupement;

    /**
     * @var string|null
     *
     * @ORM\Column(name="titre", type="string", nullable=true)
     */
    private $titre;

    /**
     * @var string|null
     *
     * @ORM\Column(name="titre_court", type="string", nullable=true)
     */
    private $titreCourt;

    /**
     * @var string|null
     *
     * @ORM\Column(name="objet", type="string", nullable=true)
     */
    private $objet;

    /**
     * @var string|null
     *
     * @ORM\Column(name="objet_social1", type="string", nullable=true)
     */
    private $objetSocial1;

    /**
     * @var string|null
     *
     * @ORM\Column(name="objet_social2", type="string", nullable=true)
     */
    private $objetSocial2;

    /**
     * @var string|null
     *
     * @ORM\Column(name="adrs_complement", type="string", nullable=true)
     */
    private $adrsComplement;

    /**
     * @var string|null
     *
     * @ORM\Column(name="adrs_numvoie", type="string", nullable=true)
     */
    private $adrsNumvoie;

    /**
     * @var string|null
     *
     * @ORM\Column(name="adrs_repetition", type="string", nullable=true)
     */
    private $adrsRepetition;

    /**
     * @var string|null
     *
     * @ORM\Column(name="adrs_typevoie", type="string", nullable=true)
     */
    private $adrsTypevoie;

    /**
     * @var string|null
     *
     * @ORM\Column(name="adrs_libvoie", type="string", nullable=true)
     */
    private $adrsLibvoie;

    /**
     * @var string|null
     *
     * @ORM\Column(name="adrs_distrib", type="string", nullable=true)
     */
    private $adrsDistrib;

    /**
     * @var string|null
     *
     * @ORM\Column(name="adrs_codeinsee", type="string", nullable=true)
     */
    private $adrsCodeinsee;

    /**
     * @var string|null
     *
     * @ORM\Column(name="adrs_codepostal", type="string", nullable=true)
     */
    private $adrsCodepostal;

    /**
     * @var string|null
     *
     * @ORM\Column(name="adrs_libcommune", type="string", nullable=true)
     */
    private $adrsLibcommune;

    /**
     * @var string|null
     *
     * @ORM\Column(name="adrg_declarant", type="string", nullable=true)
     */
    private $adrgDeclarant;

    /**
     * @var string|null
     *
     * @ORM\Column(name="adrg_complemid", type="string", nullable=true)
     */
    private $adrgComplemid;

    /**
     * @var string|null
     *
     * @ORM\Column(name="adrg_complemgeo", type="string", nullable=true)
     */
    private $adrgComplemgeo;

    /**
     * @var string|null
     *
     * @ORM\Column(name="adrg_libvoie", type="string", nullable=true)
     */
    private $adrgLibvoie;

    /**
     * @var string|null
     *
     * @ORM\Column(name="adrg_distrib", type="string", nullable=true)
     */
    private $adrgDistrib;

    /**
     * @var string|null
     *
     * @ORM\Column(name="adrg_codepostal", type="string", nullable=true)
     */
    private $adrgCodepostal;

    /**
     * @var string|null
     *
     * @ORM\Column(name="adrg_achemine", type="string", nullable=true)
     */
    private $adrgAchemine;

    /**
     * @var string|null
     *
     * @ORM\Column(name="adrg_pays", type="string", nullable=true)
     */
    private $adrgPays;

    /**
     * @var string|null
     *
     * @ORM\Column(name="dir_civilite", type="string", nullable=true)
     */
    private $dirCivilite;

    /**
     * @var string|null
     *
     * @ORM\Column(name="telephone", type="string", nullable=true)
     */
    private $telephone;

    /**
     * @var string|null
     *
     * @ORM\Column(name="siteweb", type="string", nullable=true)
     */
    private $siteweb;

    /**
     * @var string|null
     *
     * @ORM\Column(name="email", type="string", nullable=true)
     */
    private $email;

    /**
     * @var string|null
     *
     * @ORM\Column(name="publiweb", type="string", nullable=true)
     */
    private $publiweb;

    /**
     * @var string|null
     *
     * @ORM\Column(name="observation", type="string", nullable=true)
     */
    private $observation;

    /**
     * @var string|null
     *
     * @ORM\Column(name="position", type="string", nullable=true)
     */
    private $position;

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="maj_time", type="datetime", nullable=true)
     */
    private $majTime;

    /**
     * WaldecAssociation constructor.
     * @param string $id
     * @param string|null $titre
     * @param string|null $objet
     * @param string|null $adrsLibvoie
     * @param string|null $adrsCodepostal
     * @param string|null $adrsLibcommune
     * @param string|null $adrgLibvoie
     * @param string|null $adrgCodepostal
     */
    public function __construct(string $id, ?string $titre, ?string $objet,
                                ?string $adrsCodepostal, ?string $adrsLibvoie,
                                ?string $adrsLibcommune, ?string $adrgCodepostal,
                                ?string $adrgLibvoie, ?string $adrsNumvoie, ?string $adrsTypevoie)
    {
        $this->id = $id;
        $this->titre = $titre;
        $this->objet = $objet;
        $this->adrsLibvoie = $adrsLibvoie;
        $this->adrsCodepostal = $adrsCodepostal;
        $this->adrsLibcommune = $adrsLibcommune;
        $this->adrgLibvoie = $adrgLibvoie;
        $this->adrgCodepostal = $adrgCodepostal;
        $this->adrsNumvoie = $adrsNumvoie;
        $this->adrsTypevoie = $adrsTypevoie;
    }


    public function getId() {
        return $this->id;
    }

    public function getIdEx() {
        return $this->idEx;
    }

    public function getSiret() {
        return $this->siret;
    }

    public function getRupMi() {
        return $this->rupMi;
    }

    public function getGestion() {
        return $this->gestion;
    }

    public function getDateCreat(): \DateTime {
        return $this->dateCreat;
    }

    public function getDateDecla(): \DateTime {
        return $this->dateDecla;
    }

    public function getDatePubli(): \DateTime {
        return $this->datePubli;
    }

    public function getDateDisso(): \DateTime {
        return $this->dateDisso;
    }

    public function getNature() {
        return $this->nature;
    }

    public function getGroupement() {
        return $this->groupement;
    }

    public function getTitre() {
        return $this->titre;
    }

    public function getTitreCourt() {
        return $this->titreCourt;
    }

    public function getObjet() {
        return $this->objet;
    }

    public function getObjetSocial1() {
        return $this->objetSocial1;
    }

    public function getObjetSocial2() {
        return $this->objetSocial2;
    }

    public function getAdrsComplement() {
        return $this->adrsComplement;
    }

    public function getAdrsNumvoie() {
        return $this->adrsNumvoie;
    }

    public function getAdrsRepetition() {
        return $this->adrsRepetition;
    }

    public function getAdrsTypevoie() {
        return $this->adrsTypevoie;
    }

    public function getAdrsLibvoie() {
        return $this->adrsLibvoie;
    }

    public function getAdrsDistrib() {
        return $this->adrsDistrib;
    }

    public function getAdrsCodeinsee() {
        return $this->adrsCodeinsee;
    }

    public function getAdrsCodepostal() {
        return $this->adrsCodepostal;
    }

    public function getAdrsLibcommune() {
        return $this->adrsLibcommune;
    }

    public function getAdrgDeclarant() {
        return $this->adrgDeclarant;
    }

    public function getAdrgComplemid() {
        return $this->adrgComplemid;
    }

    public function getAdrgComplemgeo() {
        return $this->adrgComplemgeo;
    }

    public function getAdrgLibvoie() {
        return $this->adrgLibvoie;
    }

    public function getAdrgDistrib() {
        return $this->adrgDistrib;
    }

    public function getAdrgCodepostal() {
        return $this->adrgCodepostal;
    }

    public function getAdrgAchemine() {
        return $this->adrgAchemine;
    }

    public function getAdrgPays() {
        return $this->adrgPays;
    }

    public function getDirCivilite() {
        return $this->dirCivilite;
    }

    public function getTelephone() {
        return $this->telephone;
    }

    public function getSiteweb() {
        return $this->siteweb;
    }

    public function getEmail() {
        return $this->email;
    }

    public function getPubliweb() {
        return $this->publiweb;
    }

    public function getObservation() {
        return $this->observation;
    }

    public function getPosition() {
        return $this->position;
    }

    public function getMajTime(): \DateTime {
        return $this->majTime;
    }

    public function setId($id) {
        $this->id = $id;
    }

    public function setIdEx($idEx) {
        $this->idEx = $idEx;
    }

    public function setSiret($siret) {
        $this->siret = $siret;
    }

    public function setRupMi($rupMi) {
        $this->rupMi = $rupMi;
    }

    public function setGestion($gestion) {
        $this->gestion = $gestion;
    }

    public function setDateCreat(\DateTime $dateCreat) {
        $this->dateCreat = $dateCreat;
    }

    public function setDateDecla(\DateTime $dateDecla) {
        $this->dateDecla = $dateDecla;
    }

    public function setDatePubli(\DateTime $datePubli) {
        $this->datePubli = $datePubli;
    }

    public function setDateDisso(\DateTime $dateDisso) {
        $this->dateDisso = $dateDisso;
    }

    public function setNature($nature) {
        $this->nature = $nature;
    }

    public function setGroupement($groupement) {
        $this->groupement = $groupement;
    }

    public function setTitre($titre) {
        $this->titre = $titre;
    }

    public function setTitreCourt($titreCourt) {
        $this->titreCourt = $titreCourt;
    }

    public function setObjet($objet) {
        $this->objet = $objet;
    }

    public function setObjetSocial1($objetSocial1) {
        $this->objetSocial1 = $objetSocial1;
    }

    public function setObjetSocial2($objetSocial2) {
        $this->objetSocial2 = $objetSocial2;
    }

    public function setAdrsComplement($adrsComplement) {
        $this->adrsComplement = $adrsComplement;
    }

    public function setAdrsNumvoie($adrsNumvoie) {
        $this->adrsNumvoie = $adrsNumvoie;
    }

    public function setAdrsRepetition($adrsRepetition) {
        $this->adrsRepetition = $adrsRepetition;
    }

    public function setAdrsTypevoie($adrsTypevoie) {
        $this->adrsTypevoie = $adrsTypevoie;
    }

    public function setAdrsLibvoie($adrsLibvoie) {
        $this->adrsLibvoie = $adrsLibvoie;
    }

    public function setAdrsDistrib($adrsDistrib) {
        $this->adrsDistrib = $adrsDistrib;
    }

    public function setAdrsCodeinsee($adrsCodeinsee) {
        $this->adrsCodeinsee = $adrsCodeinsee;
    }

    public function setAdrsCodepostal($adrsCodepostal) {
        $this->adrsCodepostal = $adrsCodepostal;
    }

    public function setAdrsLibcommune($adrsLibcommune) {
        $this->adrsLibcommune = $adrsLibcommune;
    }

    public function setAdrgDeclarant($adrgDeclarant) {
        $this->adrgDeclarant = $adrgDeclarant;
    }

    public function setAdrgComplemid($adrgComplemid) {
        $this->adrgComplemid = $adrgComplemid;
    }

    public function setAdrgComplemgeo($adrgComplemgeo) {
        $this->adrgComplemgeo = $adrgComplemgeo;
    }

    public function setAdrgLibvoie($adrgLibvoie) {
        $this->adrgLibvoie = $adrgLibvoie;
    }

    public function setAdrgDistrib($adrgDistrib) {
        $this->adrgDistrib = $adrgDistrib;
    }

    public function setAdrgCodepostal($adrgCodepostal) {
        $this->adrgCodepostal = $adrgCodepostal;
    }

    public function setAdrgAchemine($adrgAchemine) {
        $this->adrgAchemine = $adrgAchemine;
    }

    public function setAdrgPays($adrgPays) {
        $this->adrgPays = $adrgPays;
    }

    public function setDirCivilite($dirCivilite) {
        $this->dirCivilite = $dirCivilite;
    }

    public function setTelephone($telephone) {
        $this->telephone = $telephone;
    }

    public function setSiteweb($siteweb) {
        $this->siteweb = $siteweb;
    }

    public function setEmail($email) {
        $this->email = $email;
    }

    public function setPubliweb($publiweb) {
        $this->publiweb = $publiweb;
    }

    public function setObservation($observation) {
        $this->observation = $observation;
    }

    public function setPosition($position) {
        $this->position = $position;
    }

    public function setMajTime(\DateTime $majTime) {
        $this->majTime = $majTime;
    }
}
