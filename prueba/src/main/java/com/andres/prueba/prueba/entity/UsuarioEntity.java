package com.andres.prueba.prueba.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class UsuarioEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="primer_nombre")
	private String primerNombre;
	@Column(name="primer_apellido")
	private String primerApellido;
	@Column(name="otros_nombres")
	private String otrosNombres;
	@Column(name="pais_del_empleo")
	private String paisEmpleo;
	@Column(name="correo")
	private String correo;
	@Column(name="estado")
	private Integer estado;
	
	public UsuarioEntity() {
		super();
	}
	
	public UsuarioEntity(String primerNombre, String primerApellido, String otrosNombres, String paisEmpleo) {
		super();
		this.primerNombre = primerNombre;
		this.primerApellido = primerApellido;
		this.otrosNombres = otrosNombres;
		this.paisEmpleo = paisEmpleo;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
}
