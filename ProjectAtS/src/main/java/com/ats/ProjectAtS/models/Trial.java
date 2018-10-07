package com.ats.ProjectAtS.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Trial {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idTrial;
	
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "trial")
	List<Result> results = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "trial")
	List<Score> scores = new ArrayList<>();

}
