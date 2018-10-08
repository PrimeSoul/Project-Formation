package com.ats.ProjectAtS.DAOs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ats.ProjectAtS.models.Result;
import com.ats.ProjectAtS.models.Trial;

@Repository
public interface ResultDAO extends JpaRepository<Result, Integer> {

	/**
	 * Consulta compleja para obtener los corredores que cumplan cierta franja de edad de una determinada prueba.
	 * @param idTrial : Identificador de la prueba.
	 * @param min : Edad mínima para la categoría elegida.
	 * @param max : Edad máxima para la categoría elegida.
	 * @param yearNow : Año en el que se organizó la prueba.
	 * @return Devuelve únicamente los resultados de corredores que cumplan la categoría.
	 */
	@Query("SELECT r FROM Result r JOIN r.runner a JOIN r.trial t WHERE t.id = :idTrial AND ABS(a.year - :yearNow) BETWEEN :min AND :max ORDER BY r.seconds")
	List<Result> getResultsByMaster(
			@Param("idTrial") Integer idTrial,
			@Param("min") Integer min,
			@Param("max") Integer max,
			@Param("yearNow") Integer yearNow);
	
	List<Result> findByTrial(Trial trial);
	
}
