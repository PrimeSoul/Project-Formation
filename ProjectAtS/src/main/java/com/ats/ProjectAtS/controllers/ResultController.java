package com.ats.ProjectAtS.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ProjectAtS.DTOs.ResultDTO;
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
	
	/*@GetMapping
	public List<ResultDTO> findAll() {

	}*/
	
	@PostMapping
	public ResultDTO create(@RequestBody ResultDTO ResultCreated) {
		final Result result = resultMapper.mapToModel(ResultCreated);
		final Result resultCreated = resultService.create(result);
		return resultMapper.mapToDTO(resultCreated);
	}
	
}
