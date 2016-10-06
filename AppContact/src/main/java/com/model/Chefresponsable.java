package com.model;

// Generated 31 ao�t 2016 17:16:44 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Chefresponsable generated by hbm2java
 */
@Entity
@Table(name = "chefresponsable", catalog = "appcontactmybuild")
public class Chefresponsable implements java.io.Serializable {

	private Integer idChefResponsable;
	private String nomChefResponsable;
	private String prenomChefResponsable;
	private String adresse;
	private String email;
	private Integer telephone;
	private Set<Morale> morales = new HashSet<Morale>(0);

	public Chefresponsable() {
	}

	public Chefresponsable(String nomChefResponsable,
			String prenomChefResponsable, String adresse, String email,
			Integer telephone, Set<Morale> morales) {
		this.nomChefResponsable = nomChefResponsable;
		this.prenomChefResponsable = prenomChefResponsable;
		this.adresse = adresse;
		this.email = email;
		this.telephone = telephone;
		this.morales = morales;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idChefResponsable", unique = true, nullable = false)
	public Integer getIdChefResponsable() {
		return this.idChefResponsable;
	}

	public void setIdChefResponsable(Integer idChefResponsable) {
		this.idChefResponsable = idChefResponsable;
	}

	@Column(name = "nomChefResponsable", length = 254)
	public String getNomChefResponsable() {
		return this.nomChefResponsable;
	}

	public void setNomChefResponsable(String nomChefResponsable) {
		this.nomChefResponsable = nomChefResponsable;
	}

	@Column(name = "prenomChefResponsable", length = 254)
	public String getPrenomChefResponsable() {
		return this.prenomChefResponsable;
	}

	public void setPrenomChefResponsable(String prenomChefResponsable) {
		this.prenomChefResponsable = prenomChefResponsable;
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

	@Column(name = "telephone")
	public Integer getTelephone() {
		return this.telephone;
	}

	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "chefresponsable")
	public Set<Morale> getMorales() {
		return this.morales;
	}

	public void setMorales(Set<Morale> morales) {
		this.morales = morales;
	}

	
	   @Override
	    public boolean equals(Object other) {
	        return (other instanceof Chefresponsable) && (idChefResponsable != null)
	            ? idChefResponsable.equals(((Chefresponsable) other).idChefResponsable)
	            : (other == this);
	    }

	    @Override
	    public int hashCode() {
	        return (idChefResponsable != null)
	            ? (this.getClass().hashCode() + idChefResponsable.hashCode())
	            : super.hashCode();
	    }

	    @Override
	    public String toString() {
	        return String.format("Chefresponsable[%d, %s]", idChefResponsable,nomChefResponsable);
	    }
}
