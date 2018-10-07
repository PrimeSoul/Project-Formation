package com.ats.ProjectAtS.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idResult;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Runner runner;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Trial trial;
	
	@Column(precision = 9, scale = 3)
	private BigDecimal seconds;
	
}
