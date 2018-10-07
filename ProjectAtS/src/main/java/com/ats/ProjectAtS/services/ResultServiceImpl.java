package com.ats.ProjectAtS.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.ProjectAtS.DAOs.ResultDAO;
import com.ats.ProjectAtS.models.Result;

@Service
public class ResultServiceImpl implements ResultService {

	@Autowired
	ResultDAO dao;
	
	@Override
	public Result create(Result result) {
		return dao.save(result);
	}

}
