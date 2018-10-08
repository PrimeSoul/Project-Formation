package com.ats.ProjectAtS.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ProjectAtS.DTOs.ResultDTO;
import com.ats.ProjectAtS.exceptions.NotFound;
import com.ats.ProjectAtS.mappers.ResultMapper;
import com.ats.ProjectAtS.models.Result;
import com.ats.ProjectAtS.services.ResultService;

@RestController
@RequestMapping("/result")
public class ResultController {

	@Autowired
	ResultService resultService;
	@Autowired
	ResultMapper resultMapper;
	
	@GetMapping("/trial/{idTrial}")
	public void getResultsIntoTxt (@PathVariable Integer idTrial) throws NotFound {
		resultService.getResultsIntoTxt(idTrial);
	}
	
	@GetMapping("/trial/{idTrial}/master/{master}")
	public List<ResultDTO> getResultsByMaster(@PathVariable Integer idTrial, @PathVariable Integer master) {
		final List<Result> results = resultService.getResultsByMaster(idTrial, master);
		return resultMapper.mapToDTO(results);
	}
	
	@PostMapping
	public ResultDTO create(@RequestBody ResultDTO ResultCreated) {
		final Result result = resultMapper.mapToModel(ResultCreated);
		final Result resultCreated = resultService.create(result);
		return resultMapper.mapToDTO(resultCreated);
	}
	
}