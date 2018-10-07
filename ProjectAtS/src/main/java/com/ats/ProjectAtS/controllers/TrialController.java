package com.ats.ProjectAtS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ProjectAtS.DTOs.TrialDTO;
import com.ats.ProjectAtS.mappers.TrialMapper;
import com.ats.ProjectAtS.models.Trial;
import com.ats.ProjectAtS.services.TrialService;

@RestController
@RequestMapping("/trial")
public class TrialController {
	
	@Autowired
	TrialService trialService;
	@Autowired
	TrialMapper trialMapper;
	
	@PostMapping
	public TrialDTO create(@RequestBody TrialDTO TrialCreated) {
		final Trial trial = trialMapper.mapToModel(TrialCreated);
		final Trial trialCreated = trialService.create(trial);
		return trialMapper.mapToDTO(trialCreated);
	}

}