package com.ats.ProjectAtS.services;

import java.util.List;

import com.ats.ProjectAtS.exceptions.NotFound;
import com.ats.ProjectAtS.models.Result;

public interface ResultService {
	
	/**
	 * Función para crear un resultado.
	 * @param result : Resultado a ser creado
	 * @return Devuelve el resultado recién creado.
	 */
	Result create (Result result);
	
	/**
	 * Función que devuelve todos los resultados obtenidos.
	 * @return
	 */
	List<Result> findAll();
	
	/**
	 * Dado un Id de una prueba, nos genera un fichero .TXT en la carpeta raíz de nuestro proyecto.
	 * En él vendrán los clubes que han participado en la prueba y sus puntos obtenidos en la misma. 
	 * @param idTrial : Identificador de la prueba
	 * @throws NotFound : Excepción que se lanza cuando se le pasa una prueba que no existe.
	 */
	void getResultsIntoTxt (Integer idTrial) throws NotFound;
	
	/**
	 * Función que nos devuelve los resultados obtenidos por los corredores que cumplen con la categoría.
	 * La función tomará cualquier entero y lo tratará como una categoría, por si en el futuro se decide
	 * añadir nuevas.
	 * @param idTrial : Identificación de la prueba.
	 * @param master : número que nos indica la categoría del master.
	 * @return Devuelve los resultados de una categoría.
	 */
	List<Result> getResultsByMaster(Integer idTrial, Integer master);
	
}
