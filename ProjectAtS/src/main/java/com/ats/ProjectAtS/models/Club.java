package com.ats.ProjectAtS.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Club {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idClub;
	
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "club")
	private List<Runner> members = new ArrayList<>();
	
}
