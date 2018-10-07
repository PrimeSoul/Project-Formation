package com.ats.ProjectAtS.services;

import java.util.List;
import java.util.Optional;

import com.ats.ProjectAtS.models.Club;

public interface ClubService {
	
	Club create (Club club);
	
	List<Club> findAll();
	
	Optional<Club> findById(Integer id);

}
