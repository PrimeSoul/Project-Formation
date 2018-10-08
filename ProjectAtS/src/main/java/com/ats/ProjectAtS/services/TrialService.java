package com.ats.ProjectAtS.services;

import java.util.Optional;

import com.ats.ProjectAtS.models.Trial;

public interface TrialService {

	Trial create (Trial trial);
	
	Optional<Trial> findById(Integer id);
	
}
