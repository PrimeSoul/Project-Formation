package com.ats.ProjectAtS.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class UniqueScore implements Serializable {

	private static final long serialVersionUID = -6783149490762593702L;
	
	private Trial trial;
	
	private Integer place;
}
