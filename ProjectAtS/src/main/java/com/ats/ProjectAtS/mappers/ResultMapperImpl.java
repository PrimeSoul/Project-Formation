package com.ats.ProjectAtS.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ats.ProjectAtS.DTOs.ResultDTO;
import com.ats.ProjectAtS.models.Result;
import com.ats.ProjectAtS.services.RunnerService;

@Component
public class ResultMapperImpl implements ResultMapper {
	
	@Autowired
	RunnerService runnerService;

	@Override
	public Result mapToModel(ResultDTO dto) {
		Result result = new Result();
		result.setIdResult(dto.getId());
		result.setRunner(runnerService.findById(dto.getIdRunner()).get());
		result.setSeconds(dto.getSeconds());
		return result;
	}

	@Override
	public ResultDTO mapToDTO(Result model) {
		ResultDTO dto = new ResultDTO();
		dto.setId(model.getIdResult());
		dto.setIdRunner(model.getRunner().getIdRunner());
		dto.setSeconds(model.getSeconds());
		return dto;
	}

	@Override
	public List<ResultDTO> mapToDTO(List<Result> models) {
		return models.parallelStream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
}
