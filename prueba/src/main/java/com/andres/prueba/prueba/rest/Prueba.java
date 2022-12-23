package com.andres.prueba.prueba.rest;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andres.prueba.prueba.dto.UsuarioDto;
import com.andres.prueba.prueba.exeption.ExeptionPrueba;
import com.andres.prueba.prueba.service.PruebaService;

@CrossOrigin("*")
@RestController
@RequestMapping("${api.context}/usuarios")
public class Prueba {
	
	@Autowired
	private PruebaService service;
	
	public Prueba() {
	}

	@GetMapping("/")
	public ResponseEntity<?> consulta(Pageable pageble){
		try {
			
			return new ResponseEntity<>(service.consultarUsuarios(pageble), HttpStatus.OK);
		} catch (ExeptionPrueba e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("Ha ocurrido un error, porfavor intentar mas tarde", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> consultaId(@PathVariable(name = "id") Long id){
		try {
			
			return new ResponseEntity<>(service.buscarId(id), HttpStatus.OK);
		} catch (ExeptionPrueba e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("Ha ocurrido un error, porfavor intentar mas tarde", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<?> registrarUsuario(@RequestBody UsuarioDto usuario){
		try {
			Long response = service.crearUsuarios(usuario);
			service.actualizarCorreo(response);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (ExeptionPrueba e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("Ha ocurrido un error, porfavor intentar mas tarde", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> actualizarUsuario(@PathVariable(name = "id") Long id, @RequestBody UsuarioDto usuario){
		try {
			
			return new ResponseEntity<>(service.editarUsuarios(id, usuario), HttpStatus.OK);
		} catch (ExeptionPrueba e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("Ha ocurrido un error, porfavor intentar mas tarde", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> eliminarUsuario(@PathVariable(name = "id") Long id){
		try {
			
			return new ResponseEntity<>(service.eliminarUsuarios(id), HttpStatus.OK);
		} catch (ExeptionPrueba e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("Ha ocurrido un error, porfavor intentar mas tarde", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
