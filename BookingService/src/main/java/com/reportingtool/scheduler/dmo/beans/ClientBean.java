package com.reportingtool.scheduler.dmo.beans;

public class ClientBean {
	
	//public static final String PERSONAFISICA = "PF";
	//public static final String PERSONAJURIDICA = "PJ";
	
	public static final String PERSONAFISICA = "F";
	public static final String PERSONAJURIDICA = "J";
	
	
	private String name = null;
	private String surname1 = null;
	private String surname2 = null;
	private String typeIdentificator = null;
	private String identificator = null;
	private String country = null;
	private String relacion = null;
	private String telefono = null;
	private String municipio = null;
	// Persona Fisica F, Persona Juridica J
	private String tipoLegal = null;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname1() {
		return surname1;
	}
	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}
	public String getSurname2() {
		return surname2;
	}
	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}
	public String getTypeIdentificator() {
		return typeIdentificator;
	}
	public void setTypeIdentificator(String typeIdentificator) {
		this.typeIdentificator = typeIdentificator;
	}
	public String getIdentificator() {
		return identificator;
	}
	public void setIdentificator(String identificator) {
		this.identificator = identificator;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRelacion() {
		return relacion;
	}
	public void setRelacion(String relacion) {
		this.relacion = relacion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getTipoLegal() {
		return tipoLegal;
	}
	public void setTipoLegal(String tipoLegal) {
		this.tipoLegal = tipoLegal;
	}
	
	

}
