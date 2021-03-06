package com.model;

// Generated 9 oct. 2016 13:55:49 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Morale generated by hbm2java
 */
@Entity
@Table(name = "morale", catalog = "appcontact")
public class Morale implements java.io.Serializable {

	private Integer idMorale;
	private Typemoral typemoral;
	private Region region;
	private Activite activite;
	private Groupecontact groupecontact;
	private Secteur secteur;
	private Chefresponsable chefresponsable;
	private String description;
	private String fax;
	private String ville;
	private String codePostale;
	private String telephone;
	private String mobile;
	private String email;
	private String nom;
	private String adresse;
	private Date date;
	private Date datetime;
	private Set<Membre> membres = new HashSet<Membre>(0);

	public Morale() {
	}

	public Morale(Typemoral typemoral, Region region, Activite activite,
			Groupecontact groupecontact, Secteur secteur,
			Chefresponsable chefresponsable, String description, String fax,
			String ville, String codePostale, String telephone,
			String mobile, String email, String nom, String adresse,
			Date date, Date datetime, Set<Membre> membres) {
		this.typemoral = typemoral;
		this.region = region;
		this.activite = activite;
		this.groupecontact = groupecontact;
		this.secteur = secteur;
		this.chefresponsable = chefresponsable;
		this.description = description;
		this.fax = fax;
		this.ville = ville;
		this.codePostale = codePostale;
		this.telephone = telephone;
		this.mobile = mobile;
		this.email = email;
		this.nom = nom;
		this.adresse = adresse;
		this.date = date;
		this.datetime = datetime;
		this.membres = membres;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idMorale", unique = true, nullable = false)
	public Integer getIdMorale() {
		return this.idMorale;
	}

	public void setIdMorale(Integer idMorale) {
		this.idMorale = idMorale;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idTypeMoral")
	public Typemoral getTypemoral() {
		return this.typemoral;
	}

	public void setTypemoral(Typemoral typemoral) {
		this.typemoral = typemoral;
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
	@JoinColumn(name = "idActivite")
	public Activite getActivite() {
		return this.activite;
	}

	public void setActivite(Activite activite) {
		this.activite = activite;
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
	@JoinColumn(name = "idSecteur")
	public Secteur getSecteur() {
		return this.secteur;
	}

	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idChefResponsable")
	public Chefresponsable getChefresponsable() {
		return this.chefresponsable;
	}

	public void setChefresponsable(Chefresponsable chefresponsable) {
		this.chefresponsable = chefresponsable;
	}

	@Column(name = "description", length = 254)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "fax")
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "ville", length = 254)
	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
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

	@Column(name = "email", length = 254)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "nom", length = 254)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "adresse", length = 254)
	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
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
	@Column(name = "datetime", length = 19)
	public Date getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "morale")
	public Set<Membre> getMembres() {
		return this.membres;
	}

	public void setMembres(Set<Membre> membres) {
		this.membres = membres;
	}
	
    @Override
    public boolean equals(Object other) {
        return (other instanceof Morale) && (idMorale != null)
            ? idMorale.equals(((Morale) other).idMorale)
            : (other == this);
    }

    @Override
    public int hashCode() {
        return (idMorale != null)
            ? (this.getClass().hashCode() + idMorale.hashCode())
            : super.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Morale[%d, %s]", idMorale,email);
    }

}
