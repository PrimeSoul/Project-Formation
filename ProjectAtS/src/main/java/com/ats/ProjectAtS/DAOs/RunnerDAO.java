package com.ats.ProjectAtS.DAOs;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.ProjectAtS.models.Runner;

@Repository
public interface RunnerDAO extends JpaRepository<Runner, Integer> {
	
	/**
	 * Consulta que nos permite buscar un nombre concreto de un corredor, e incluirlo en una paginación.
	 * @param name : Nombre del corredor
	 * @param pageable : Paginación
	 * @return Devuelve una página con corredores cuyo nombre coincida con "name".
	 */
	Page<Runner> findByNameContaining(String name, Pageable pageable);

}
