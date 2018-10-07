package com.ats.ProjectAtS.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ats.ProjectAtS.DAOs.RunnerDAO;
import com.ats.ProjectAtS.exceptions.NotFound;
import com.ats.ProjectAtS.models.Runner;

@Service
public class RunnerServiceImpl implements RunnerService {

	@Autowired
	RunnerDAO dao;
	
	@Override
	public Runner create(Runner runner) {
		return dao.save(runner);
	}

	@Override
	public List<Runner> findAll(Pageable page, String name) {
		return dao.findByNameContaining(page, name).getContent();
	}

	@Override
	public Optional<Runner> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public void update(Integer id, Runner runnerUpdated) throws NotFound {
		Runner runner = dao.findById(id).orElseThrow(NotFound::new);
		dao.save(runnerUpdated);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

}
