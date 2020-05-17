<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Association
 *
 * @ORM\Table(name="association", indexes={@ORM\Index(name="idx_cp", columns={"adrs_codepostal"})})
 * @ORM\Entity
 */
class Association
{
    /**
     * @var string
     *
     * @ORM\Column(name="id", type="string", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="SEQUENCE")
     * @ORM\SequenceGenerator(sequenceName="association_id_seq", allocationSize=1, initialValue=1)
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
     * @ORM\Column(name="date_publi", type="date", nullable=true)
     */
    private $datePubli;

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
     * @ORM\Column(name="adr1", type="string", nullable=true)
     */
    private $adr1;

    /**
     * @var string|null
     *
     * @ORM\Column(name="adr2", type="string", nullable=true)
     */
    private $adr2;

    /**
     * @var string|null
     *
     * @ORM\Column(name="adr3", type="string", nullable=true)
     */
    private $adr3;

    /**
     * @var string|null
     *
     * @ORM\Column(name="adrs_codepostal", type="string", nullable=true)
     */
    private $adrsCodepostal;

    /**
     * @var string|null
     *
     * @ORM\Column(name="libcom", type="string", nullable=true)
     */
    private $libcom;

    /**
     * @var string|null
     *
     * @ORM\Column(name="adrs_codeinsee", type="string", nullable=true)
     */
    private $adrsCodeinsee;

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
     * @var string|null
     *
     * @ORM\Column(name="rup_mi", type="string", nullable=true)
     */
    private $rupMi;

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="maj_time", type="date", nullable=true)
     */
    private $majTime;

    public function __construct($id, $titre, $objet, $adr1, $adrsCodepostal, $libcom)
    {
        $this->id = $id;
        $this->titre = $titre;
        $this->objet = $objet;
        $this->adr1 = $adr1;
        $this->adrsCodepostal = $adrsCodepostal;
        $this->libcom = $libcom;
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

    public function getGestion() {
        return $this->gestion;
    }

    public function getDateCreat(): \DateTime {
        return $this->dateCreat;
    }

    public function getDatePubli(): \DateTime {
        return $this->datePubli;
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

    public function getObjet() {
        return $this->objet;
    }

    public function getObjetSocial1() {
        return $this->objetSocial1;
    }

    public function getObjetSocial2() {
        return $this->objetSocial2;
    }

    public function getAdr1() {
        return $this->adr1;
    }

    public function getAdr2() {
        return $this->adr2;
    }

    public function getAdr3() {
        return $this->adr3;
    }

    public function getAdrsCodepostal() {
        return $this->adrsCodepostal;
    }

    public function getLibcom() {
        return $this->libcom;
    }

    public function getAdrsCodeinsee() {
        return $this->adrsCodeinsee;
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

    public function getObservation() {
        return $this->observation;
    }

    public function getPosition() {
        return $this->position;
    }

    public function getRupMi() {
        return $this->rupMi;
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

    public function setGestion($gestion) {
        $this->gestion = $gestion;
    }

    public function setDateCreat(\DateTime $dateCreat) {
        $this->dateCreat = $dateCreat;
    }

    public function setDatePubli(\DateTime $datePubli) {
        $this->datePubli = $datePubli;
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

    public function setObjet($objet) {
        $this->objet = $objet;
    }

    public function setObjetSocial1($objetSocial1) {
        $this->objetSocial1 = $objetSocial1;
    }

    public function setObjetSocial2($objetSocial2) {
        $this->objetSocial2 = $objetSocial2;
    }

    public function setAdr1($adr1) {
        $this->adr1 = $adr1;
    }

    public function setAdr2($adr2) {
        $this->adr2 = $adr2;
    }

    public function setAdr3($adr3) {
        $this->adr3 = $adr3;
    }

    public function setAdrsCodepostal($adrsCodepostal) {
        $this->adrsCodepostal = $adrsCodepostal;
    }

    public function setLibcom($libcom) {
        $this->libcom = $libcom;
    }

    public function setAdrsCodeinsee($adrsCodeinsee) {
        $this->adrsCodeinsee = $adrsCodeinsee;
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

    public function setObservation($observation) {
        $this->observation = $observation;
    }

    public function setPosition($position) {
        $this->position = $position;
    }

    public function setRupMi($rupMi) {
        $this->rupMi = $rupMi;
    }

    public function setMajTime(\DateTime $majTime) {
        $this->majTime = $majTime;
    }
}
