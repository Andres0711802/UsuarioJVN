package com.andres.prueba.prueba.model.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.andres.prueba.prueba.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{

	@Query(value = "SELECT * FROM usuarios p where estado = 1",
			countQuery = ("SELECT * FROM usuarios p where estado = 1"),
			nativeQuery = true)
	Page<UsuarioEntity> consultarUsuarios(Pageable pageable);
	
}
