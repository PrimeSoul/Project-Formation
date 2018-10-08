package com.ats.ProjectAtS.services;

import java.util.List;

import com.ats.ProjectAtS.models.Result;

public interface ResultService {
	
	Result create (Result result);
	
	List<Result> getResultsByMaster(Integer idTrial, Integer master);
	
}
