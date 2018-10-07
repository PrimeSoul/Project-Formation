package com.ats.ProjectAtS.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@IdClass(UniqueScore.class)
@Entity
public class Score {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	private Trial trial;
	
	@Id
	private Integer place;
	
	private Integer value;
	
}
