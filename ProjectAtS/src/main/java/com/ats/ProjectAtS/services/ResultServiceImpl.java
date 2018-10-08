package com.ats.ProjectAtS.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.ProjectAtS.DAOs.ResultDAO;
import com.ats.ProjectAtS.models.Result;

@Service
public class ResultServiceImpl implements ResultService {

	@Autowired
	ResultDAO dao;
	@Autowired
	TrialService trialService;
	
	@Override
	public Result create(Result result) {
		return dao.save(result);
	}

	@Override
	public List<Result> getResultsByMaster(Integer idTrial, Integer master) {
		Date date = trialService.findById(idTrial).get().getDate();
		String[] dateApart = date.toString().split("-");
		Integer yearTrial = Integer.parseInt(dateApart[0]);
		Integer difference = 10;
		if(master >= 40) difference = 100;
		List<Result> results = dao.getResultsByMaster(idTrial, master, master+difference, yearTrial);
		return results;
	}

}
