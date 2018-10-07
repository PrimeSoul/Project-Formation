package com.ats.ProjectAtS.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.ats.ProjectAtS.exceptions.NotFound;
import com.ats.ProjectAtS.models.Runner;

public interface RunnerService {
	
	Runner create (Runner runner);
	
	List<Runner> findAll(Pageable page, String name);
	
	Optional<Runner> findById(Integer id);

	void update (Integer id, Runner runner) throws NotFound;

	void assignClub(Integer idRunner, Integer idClub) throws NotFound;
	
	void delete (Integer id);
	
}
