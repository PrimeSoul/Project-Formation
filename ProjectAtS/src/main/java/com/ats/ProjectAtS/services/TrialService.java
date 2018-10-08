package com.ats.ProjectAtS.services;

import java.util.Optional;

import com.ats.ProjectAtS.models.Trial;

public interface TrialService {

	/**
	 * Función para crear una nueva prueba.
	 * @param trial : Objeto prueba que será añadido
	 * @return Devuelve el objeto prueba recién creado.
	 */
	Trial create (Trial trial);
	
	/**
	 * Función para encontrar una prueba por su identificador.
	 * @param id : Identificador de la prueba
	 * @return : Devuelve una prueba encapsulada en un Optional por si no existese.
	 */
	Optional<Trial> findById(Integer id);
	
}
