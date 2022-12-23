package com.andres.prueba.prueba.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.andres.prueba.prueba.dto.UsuarioDto;
import com.andres.prueba.prueba.entity.UsuarioEntity;
import com.andres.prueba.prueba.exeption.ExeptionPrueba;

public interface PruebaService {

	public Page<UsuarioEntity> consultarUsuarios(Pageable pageable) throws ExeptionPrueba;

	public UsuarioDto buscarId(Long id) throws ExeptionPrueba;
	
	public Long crearUsuarios(UsuarioDto usuario) throws ExeptionPrueba;

	public Boolean editarUsuarios(Long id, UsuarioDto usuario) throws ExeptionPrueba;

	public Boolean eliminarUsuarios(Long id) throws ExeptionPrueba;
	
	public void actualizarCorreo(Long id) throws ExeptionPrueba;

}
