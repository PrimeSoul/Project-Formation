package com.ats.ProjectAtS.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ats.ProjectAtS.DTOs.ClubDTO;
import com.ats.ProjectAtS.models.Club;

@Component
public class ClubMapperImpl implements ClubMapper{

	@Override
	public Club mapToModel(ClubDTO dto) {
		Club club = new Club();
		club.setIdClub(dto.getId());
		club.setName(dto.getName());
		return club;
	}

	@Override
	public ClubDTO mapToDTO(Club model) {
		ClubDTO dto = new ClubDTO();
		dto.setId(model.getIdClub());
		dto.setName(model.getName());
		return dto;
	}

	@Override
	public List<ClubDTO> mapToDTO(List<Club> models) {
		return models.parallelStream().map(this::mapToDTO).collect(Collectors.toList());
	}

}
