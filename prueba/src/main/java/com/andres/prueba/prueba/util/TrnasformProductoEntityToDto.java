package com.andres.prueba.prueba.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


import com.andres.prueba.prueba.dto.UsuarioDto;

import com.andres.prueba.prueba.entity.UsuarioEntity;

@Service
public class TrnasformProductoEntityToDto {

	public List<UsuarioDto> transformEntityTodtoProductList(List<UsuarioEntity> usuarios){
		List<UsuarioDto> response = new ArrayList<UsuarioDto>();
		for(UsuarioEntity usuario: usuarios) {
			response.add(transformEntityTodtoProduct(usuario));
		}
		return response;
	}
	
	public UsuarioDto transformEntityTodtoProduct(UsuarioEntity usuario){
		UsuarioDto response = new UsuarioDto(usuario.getId(), usuario.getPrimerNombre(), usuario.getPrimerApellido(), usuario.getOtrosNombres(), usuario.getPaisEmpleo(), usuario.getCorreo());
		
		return response;
	}
	
	public UsuarioEntity transformUserDtoToUserEntity(UsuarioDto usuario) {
		return new UsuarioEntity(usuario.getPrimerNombre().toUpperCase(), usuario.getPrimerApellido().toUpperCase(), usuario.getOtrosNombres().toUpperCase(), usuario.getPaisEmpleo()) ;
		
	}
	
}
