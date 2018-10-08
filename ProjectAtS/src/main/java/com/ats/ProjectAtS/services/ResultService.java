package com.ats.ProjectAtS.services;

import java.util.List;

import com.ats.ProjectAtS.exceptions.NotFound;
import com.ats.ProjectAtS.models.Result;

public interface ResultService {
	
	Result create (Result result);
	
	void getResultsIntoTxt (Integer idTrial) throws NotFound;
	
	List<Result> getResultsByMaster(Integer idTrial, Integer master);
	
}
