package com.ats.ProjectAtS.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ats.ProjectAtS.DTOs.RunnerDTO;
import com.ats.ProjectAtS.models.Runner;

@Component
public class RunnerMapperImpl implements RunnerMapper {

	@Override
	public Runner mapToModel(RunnerDTO dto) {
		final Runner runner = new Runner();
		runner.setIdRunner(dto.getId());
		runner.setName(dto.getName());
		runner.setYear(dto.getYear());
		return runner;
	}

	@Override
	public RunnerDTO mapToDTO(Runner model) {
		final RunnerDTO dto = new RunnerDTO();
		dto.setId(model.getIdRunner());
		dto.setName(model.getName());
		dto.setYear(model.getYear());
		return dto;
	}

	@Override
	public List<RunnerDTO> mapToDTO(List<Runner> models) {
		return models.parallelStream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
}
