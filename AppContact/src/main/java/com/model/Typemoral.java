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
 * Typemoral generated by hbm2java
 */
@Entity
@Table(name = "typemoral", catalog = "appcontactmybuild")
public class Typemoral implements java.io.Serializable {

	private Integer idMoral;
	private String libelleMorale;
	private Set<Morale> morales = new HashSet<Morale>(0);

	public Typemoral() {
	}

	public Typemoral(String libelleMorale, Set<Morale> morales) {
		this.libelleMorale = libelleMorale;
		this.morales = morales;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idMoral", unique = true, nullable = false)
	public Integer getIdMoral() {
		return this.idMoral;
	}

	public void setIdMoral(Integer idMoral) {
		this.idMoral = idMoral;
	}

	@Column(name = "libelleMorale", length = 254)
	public String getLibelleMorale() {
		return this.libelleMorale;
	}

	public void setLibelleMorale(String libelleMorale) {
		this.libelleMorale = libelleMorale;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "typemoral")
	public Set<Morale> getMorales() {
		return this.morales;
	}

	public void setMorales(Set<Morale> morales) {
		this.morales = morales;
	}

}