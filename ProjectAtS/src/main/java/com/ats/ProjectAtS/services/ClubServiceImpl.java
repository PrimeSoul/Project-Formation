package com.ats.ProjectAtS.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.ProjectAtS.DAOs.ClubDAO;
import com.ats.ProjectAtS.exceptions.NotFound;
import com.ats.ProjectAtS.models.Club;

@Service
public class ClubServiceImpl implements ClubService {

	@Autowired
	ClubDAO dao;
	
	@Override
	public Club create(Club club) {
		return dao.save(club);
	}

	@Override
	public List<Club> findAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Club> findById(Integer id) {
		return dao.findById(id);
	}

}
