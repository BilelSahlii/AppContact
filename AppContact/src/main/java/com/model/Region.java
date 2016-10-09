package com.model;

// Generated 9 oct. 2016 13:55:49 by Hibernate Tools 3.4.0.CR1

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
 * Region generated by hbm2java
 */
@Entity
@Table(name = "region", catalog = "appcontact")
public class Region implements java.io.Serializable {

	private Integer idRegion;
	private String libelleRegion;
	private Set<Physique> physiques = new HashSet<Physique>(0);
	private Set<Morale> morales = new HashSet<Morale>(0);

	public Region() {
	}

	public Region(String libelleRegion, Set<Physique> physiques,
			Set<Morale> morales) {
		this.libelleRegion = libelleRegion;
		this.physiques = physiques;
		this.morales = morales;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idRegion", unique = true, nullable = false)
	public Integer getIdRegion() {
		return this.idRegion;
	}

	public void setIdRegion(Integer idRegion) {
		this.idRegion = idRegion;
	}

	@Column(name = "libelleRegion", length = 254)
	public String getLibelleRegion() {
		return this.libelleRegion;
	}

	public void setLibelleRegion(String libelleRegion) {
		this.libelleRegion = libelleRegion;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "region")
	public Set<Physique> getPhysiques() {
		return this.physiques;
	}

	public void setPhysiques(Set<Physique> physiques) {
		this.physiques = physiques;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "region")
	public Set<Morale> getMorales() {
		return this.morales;
	}

	public void setMorales(Set<Morale> morales) {
		this.morales = morales;
	}

	
	  @Override
	    public boolean equals(Object other) {
	        return (other instanceof Region) && (idRegion != null)
	            ? idRegion.equals(((Region) other).idRegion)
	            : (other == this);
	    }

	    @Override
	    public int hashCode() {
	        return (idRegion != null)
	            ? (this.getClass().hashCode() + idRegion.hashCode())
	            : super.hashCode();
	    }

	    @Override
	    public String toString() {
	        return String.format("Region[%d, %s]", idRegion,libelleRegion);
	    }
	
}
