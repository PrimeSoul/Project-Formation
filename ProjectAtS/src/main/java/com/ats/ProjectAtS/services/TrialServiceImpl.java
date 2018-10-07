package com.ats.ProjectAtS.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.ProjectAtS.DAOs.TrialDAO;
import com.ats.ProjectAtS.models.Trial;

@Service
public class TrialServiceImpl implements TrialService {
	
	@Autowired
	TrialDAO dao;

	@Override
	public Trial create(Trial trial) {
		return dao.save(trial);
	}

}
