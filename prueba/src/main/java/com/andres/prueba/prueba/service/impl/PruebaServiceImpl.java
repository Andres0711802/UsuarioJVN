package com.andres.prueba.prueba.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.andres.prueba.prueba.dto.UsuarioDto;
import com.andres.prueba.prueba.entity.UsuarioEntity;
import com.andres.prueba.prueba.exeption.ExeptionPrueba;
import com.andres.prueba.prueba.model.repository.UsuarioRepository;
import com.andres.prueba.prueba.service.PruebaService;
import com.andres.prueba.prueba.util.TrnasformProductoEntityToDto;

import net.bytebuddy.matcher.StringMatcher;

@Service
public class PruebaServiceImpl implements PruebaService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	private final TrnasformProductoEntityToDto trnasform;

	public PruebaServiceImpl(TrnasformProductoEntityToDto trnasform) {
		this.trnasform = trnasform;
	}

	@Override
	public Page<UsuarioEntity> consultarUsuarios(Pageable pageable) throws ExeptionPrueba {
		return usuarioRepository.consultarUsuarios(pageable);
	}

	@Override
	public UsuarioDto buscarId(Long id) throws ExeptionPrueba {
		UsuarioEntity user = usuarioRepository.findById(id).orElse(null);
		if (user == null) {
			throw new ExeptionPrueba("No se encontro el usuario.");
		}
		return trnasform.transformEntityTodtoProduct(user);

	}

	@Override
	public Long crearUsuarios(UsuarioDto usuario) throws ExeptionPrueba {
		UsuarioEntity userEntity = trnasform.transformUserDtoToUserEntity(usuario);
		userEntity.setCorreo(usuario.getPrimerNombre().replace(" ", "").concat("."+usuario.getPrimerApellido().replace(" ", "")));
		userEntity.setEstado(1);
		if(validarCampos(usuario.getPrimerApellido()) ||
				validarCampos(usuario.getPrimerNombre()) ||
				validarCampos(usuario.getOtrosNombres())) {
			throw new ExeptionPrueba("No se permiten acentos ni caracteres especiales.");
		}
		userEntity = usuarioRepository.save(userEntity);

		return userEntity.getId();
	}

	@Override
	public Boolean editarUsuarios(Long id, UsuarioDto usuario) throws ExeptionPrueba {
		UsuarioEntity user = usuarioRepository.findById(id).orElse(null);
		if (user == null) {
			throw new ExeptionPrueba("No se encontro el usuario.");
		}

		if (usuario.getOtrosNombres() != null) {
			user.setOtrosNombres(usuario.getOtrosNombres().toUpperCase());
		}
		if (usuario.getPrimerApellido() != null) {
			user.setPrimerApellido(usuario.getPrimerApellido().toUpperCase());
		}
		if (usuario.getPrimerNombre() != null) {
			user.setPrimerNombre(usuario.getPrimerNombre().toUpperCase());
		}
		if (usuario.getPaisEmpleo() != null) {
			user.setPaisEmpleo(usuario.getPaisEmpleo().toUpperCase());
		}
		
		if(validarCampos(usuario.getPrimerApellido()) ||
				validarCampos(usuario.getPrimerNombre()) ||
				validarCampos(usuario.getOtrosNombres())) {
			throw new ExeptionPrueba("No se permiten acentos ni caracteres especiales.");
		}
		
		usuarioRepository.save(user);

		return true;
	}

	@Override
	public Boolean eliminarUsuarios(Long id) throws ExeptionPrueba {
		UsuarioEntity user = usuarioRepository.findById(id).orElse(null);
		if (user == null) {
			throw new ExeptionPrueba("No se encontro el usuario.");
		}

		user.setEstado(0);
		usuarioRepository.save(user);
		return true;
	}
	
	@Override
	public void actualizarCorreo(Long id) {
		UsuarioEntity user = usuarioRepository.findById(id).orElse(null);
		
		user.setCorreo(user.getCorreo().concat("."+id.toString()+ "@jvntecnologias.com "));
		usuarioRepository.save(user);
	}
	
	private Boolean validarCampos(String valor) {
		Pattern pat = Pattern.compile("[A-Z ]{2,50}");
	    Matcher mat = pat.matcher(valor.replace(" ", "").toUpperCase());                                                                           
	     System.out.println(!mat.matches());
		return !mat.matches();
	}

}
