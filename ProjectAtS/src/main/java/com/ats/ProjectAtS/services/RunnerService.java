package com.ats.ProjectAtS.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.ats.ProjectAtS.exceptions.NotFound;
import com.ats.ProjectAtS.models.Runner;

public interface RunnerService {
	
	/**
	 * Función para crear un corredor.
	 * @param runner : Corredor a añadir
	 * @return Devuelve el corredor recién añadido.
	 */
	Runner create (Runner runner);
	
	/**
	 * Función que nos permite mostrar a todos o sólo algunos corredores.
	 * @param page : Página que contiene los corredores
	 * @param name : Nombre del corredor
	 * @return Devuelve una lista con los corredores.
	 */
	List<Runner> findAll(Pageable page, String name);
	
	/**
	 * Función para buscar un corredor por un Id.
	 * @param id : Identificador del corredor
	 * @return Devuelve un objeto Optional por si el corredor no existiese.
	 */
	Optional<Runner> findById(Integer id);

	/**
	 * Función que nos permite actualizar un corredor, modificando sus valores.
	 * @param id : Identificador del corredor a cambiar.
	 * @param runner : Objeto corredor con los nuevos datos.
	 * @throws NotFound : Excepción que se lanza cuando se intenta actualizar un corredor que no existe.
	 */
	void update (Integer id, Runner runner) throws NotFound;

	/**
	 * Función que asigna un club a un corredor.
	 * @param idRunner : Identificador del corredor
	 * @param idClub : Identificador del club que se va a asignar.
	 * @throws NotFound : Excepción que se lanza cuando se intenta actualizar un corredor que no existe.
	 */
	void assignClub(Integer idRunner, Integer idClub) throws NotFound;
	
	/**
	 * Función para eliminar un corredor de nuestra BD.
	 * @param id : Identificador del corredor.
	 */
	void delete (Integer id);
	
}
