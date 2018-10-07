package com.ats.ProjectAtS.mappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ats.ProjectAtS.DTOs.TrialDTO;
import com.ats.ProjectAtS.models.Trial;

@Component
public class TrialMapperImpl implements TrialMapper {

	@Override
	public Trial mapToModel(TrialDTO dto) {
		Trial trial = new Trial();
		trial.setIdTrial(dto.getId());
		trial.setName(dto.getName());
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		try {
			trial.setDate(format.parse(dto.getDate()));
		} catch (ParseException e) {}
		return trial;
	}

	@Override
	public TrialDTO mapToDTO(Trial model) {
		TrialDTO dto = new TrialDTO();
		dto.setId(model.getIdTrial());
		dto.setName(model.getName());
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		dto.setDate(format.format(model.getDate()));
		return dto;
	}

	@Override
	public List<TrialDTO> mapToDTO(List<Trial> models) {
		return models.parallelStream().map(this::mapToDTO).collect(Collectors.toList());
	}

}
