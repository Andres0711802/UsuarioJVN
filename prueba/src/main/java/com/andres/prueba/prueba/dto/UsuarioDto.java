package com.andres.prueba.prueba.dto;

public class UsuarioDto {
	
	private String primerNombre;
	private String primerApellido;
	private String otrosNombres;
	private String paisEmpleo;
	private String correo;
	private long id;
	
	
	
	public UsuarioDto(long id, String primerNombre, String primerApellido, String otrosNombres, String paisEmpleo, String correo) {
		super();
		this.id = id;
		this.primerNombre = primerNombre;
		this.primerApellido = primerApellido;
		this.otrosNombres = otrosNombres;
		this.paisEmpleo = paisEmpleo;
		this.correo = correo;
	}
	
	
	public long getId() {
		return id;
	}


	public String getPrimerNombre() {
		return primerNombre;
	}


	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}


	public String getPrimerApellido() {
		return primerApellido;
	}


	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}


	public String getOtrosNombres() {
		return otrosNombres;
	}


	public void setOtrosNombres(String otrosNombres) {
		this.otrosNombres = otrosNombres;
	}


	public String getPaisEmpleo() {
		return paisEmpleo;
	}


	public void setPaisEmpleo(String paisEmpleo) {
		this.paisEmpleo = paisEmpleo;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
	
	
}
