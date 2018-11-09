package com.ats.ProjectAtS.services;

import java.util.List;
import java.util.Optional;

import com.ats.ProjectAtS.models.Club;

public interface ClubService {
	
	/**
	 * Función que nos permite crear un nuevo club.
	 * @param club : Objeto club para ser añadido a la BD
	 * @return Devuelve el club que acaba de ser introducido.
	 */
	Club create (Club club);
	
	/**
	 * Función para devolver todos los clubs.
	 * @return
	 */
	List<Club> findAll();
	
	/**
	 * Función para encontrar un club dado su identificador.
	 * @param id : Identificador del club
	 * @return Devuelve un objeto Optional para los casos donde el club no exista aún.
	 */
	Optional<Club> findById(Integer id);

}
