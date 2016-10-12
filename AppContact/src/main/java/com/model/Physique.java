package com.model;

// Generated 9 oct. 2016 13:55:49 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Physique generated by hbm2java
 */
@Entity
@Table(name = "physique", catalog = "appcontact")
public class Physique implements java.io.Serializable {

	private Integer idPhysique;
	private Region region;
	private Groupecontact groupecontact;
	private Fonction fonction;
	private Secteur secteur;
	private String prenom;
	private String age;
	private String civilite;
	private String nom;
	private String ville;
	private String adresse;
	private String email;
	private String codePostale;
	private String telephone;
	private String mobile;
	private Date date;
	private Date dateTime;

	public Physique() {
	}

	public Physique(Region region, Groupecontact groupecontact,
			Fonction fonction, Secteur secteur, String prenom, String age,
			String civilite, String nom, String ville, String adresse,
			String email, String codePostale, String telephone,
			String mobile, Date date, Date dateTime) {
		this.region = region;
		this.groupecontact = groupecontact;
		this.fonction = fonction;
		this.secteur = secteur;
		this.prenom = prenom;
		this.age = age;
		this.civilite = civilite;
		this.nom = nom;
		this.ville = ville;
		this.adresse = adresse;
		this.email = email;
		this.codePostale = codePostale;
		this.telephone = telephone;
		this.mobile = mobile;
		this.date = date;
		this.dateTime = dateTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idPhysique", unique = true, nullable = false)
	public Integer getIdPhysique() {
		return this.idPhysique;
	}

	public void setIdPhysique(Integer idPhysique) {
		this.idPhysique = idPhysique;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idRegion")
	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idGroupe")
	public Groupecontact getGroupecontact() {
		return this.groupecontact;
	}

	public void setGroupecontact(Groupecontact groupecontact) {
		this.groupecontact = groupecontact;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idFonction")
	public Fonction getFonction() {
		return this.fonction;
	}

	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idSecteur")
	public Secteur getSecteur() {
		return this.secteur;
	}

	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}

	@Column(name = "prenom", length = 254)
	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Column(name = "age", length = 254)
	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Column(name = "civilite", length = 254)
	public String getCivilite() {
		return this.civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	@Column(name = "nom", length = 254)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "ville", length = 254)
	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Column(name = "adresse", length = 254)
	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Column(name = "email", length = 254)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "codePostale")
	public String getCodePostale() {
		return this.codePostale;
	}

	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
	}

	@Column(name = "telephone")
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "mobile")
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", length = 19)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateTime", length = 19)
	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

}
