package com.ats.ProjectAtS.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Score implements Serializable {
	
	private static final long serialVersionUID = -6783149490762593702L;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	private Trial trial;
	
	@Id
	private Integer place;
	
	private Integer value;
	
}
