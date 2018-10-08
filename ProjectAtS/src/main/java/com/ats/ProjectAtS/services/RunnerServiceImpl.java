package com.ats.ProjectAtS.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ats.ProjectAtS.DAOs.RunnerDAO;
import com.ats.ProjectAtS.exceptions.NotFound;
import com.ats.ProjectAtS.models.Club;
import com.ats.ProjectAtS.models.Runner;

@Service
public class RunnerServiceImpl implements RunnerService {

	@Autowired
	RunnerDAO dao;
	@Autowired
	ClubService clubService;
	
	@Override
	public Runner create(Runner runner) {
		return dao.save(runner);
	}

	@Override
	public List<Runner> findAll(Pageable page, String name) {
		return dao.findByNameContaining(name, page).getContent();
	}

	@Override
	public Optional<Runner> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public void update(Integer id, Runner runnerUpdated) throws NotFound {
		final Runner runner = dao.findById(id).orElseThrow(() -> new NotFound("Error: Runner does not exist."));
		runnerUpdated.setIdRunner(id);
		runnerUpdated.setClub(runner.getClub());
		dao.save(runnerUpdated);
	}
	
	@Override
	public void assignClub(Integer idRunner, Integer idClub) throws NotFound {
		final Runner runner = dao.findById(idRunner).orElseThrow(() -> new NotFound("Error: Runner does not exist."));
		final Club club = clubService.findById(idClub).orElseThrow(() -> new NotFound("Error: Runner does not exist."));
		runner.setClub(club);
		dao.save(runner);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

}
