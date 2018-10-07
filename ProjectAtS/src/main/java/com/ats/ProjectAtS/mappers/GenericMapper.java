package com.ats.ProjectAtS.mappers;

import java.util.List;

public interface GenericMapper <Model, DTO>{
	
	Model mapToModel(DTO dto);

	DTO mapToDTO(Model model);

	List<DTO> mapToDTO(List<Model> models);

}
