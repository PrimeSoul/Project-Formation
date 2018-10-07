package com.ats.ProjectAtS.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ProjectAtS.DTOs.RunnerDTO;
import com.ats.ProjectAtS.exceptions.NotFound;
import com.ats.ProjectAtS.mappers.RunnerMapper;
import com.ats.ProjectAtS.models.Runner;
import com.ats.ProjectAtS.services.RunnerService;

@RestController
@RequestMapping("/runner")
public class RunnerController {

	@Autowired
	RunnerService runnerService;
	@Autowired
	RunnerMapper runnerMapper;
	
	@GetMapping
	public List<RunnerDTO> findAll(
			@RequestParam(defaultValue = "0", value = "page", required = false) Integer page,
			@RequestParam(defaultValue = "5", value = "size", required = false) Integer size,
			@RequestParam(value = "name", required = false) String name) {
		final List<Runner> runners = runnerService.findAll(PageRequest.of(page, size), name);
		return runnerMapper.mapToDTO(runners);
	}
	
	@GetMapping("/{idRunner}")
	public RunnerDTO findById(@PathVariable Integer idRunner) {
		final Optional<Runner> runner = runnerService.findById(idRunner);
		return runnerMapper.mapToDTO(runner.get());
	}

	@PostMapping
	public RunnerDTO create(@RequestBody RunnerDTO RunnerCreated) {
		final Runner runner = runnerMapper.mapToModel(RunnerCreated);
		final Runner runnerCreated = runnerService.create(runner);
		return runnerMapper.mapToDTO(runnerCreated);
	}

	@PutMapping("/{idRunner}")
	public void update(@RequestBody RunnerDTO runnerUpdated, @PathVariable Integer idRunner) throws NotFound {
		final Runner runner = runnerMapper.mapToModel(runnerUpdated);
		runnerService.update(idRunner, runner);
	}
	
	@PutMapping("/{idRunner}/{idClub}")
	public void assignClub(@PathVariable Integer idRunner, @PathVariable Integer idClub) throws NotFound {
		runnerService.assignClub(idRunner, idClub);
	}

	@DeleteMapping("/{idRunner}")
	public void delete(@PathVariable Integer idRunner) {
		runnerService.delete(idRunner);
	}
	
}
