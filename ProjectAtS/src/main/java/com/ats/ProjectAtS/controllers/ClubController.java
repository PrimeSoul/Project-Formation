package com.ats.ProjectAtS.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.ProjectAtS.DTOs.ClubDTO;
import com.ats.ProjectAtS.mappers.ClubMapper;
import com.ats.ProjectAtS.models.Club;
import com.ats.ProjectAtS.services.ClubService;

@RestController
@RequestMapping("/club")
public class ClubController {
	
	@Autowired
	ClubService clubService;
	@Autowired
	ClubMapper clubMapper;
	
	@GetMapping
	public List<ClubDTO> findAll() {
		final List<Club> clubs = clubService.findAll();
		return clubMapper.mapToDTO(clubs);
	}
	
	@GetMapping("/{idClub}")
	public ClubDTO findById(@PathVariable Integer idClub) {
		final Optional<Club> club = clubService.findById(idClub);
		return clubMapper.mapToDTO(club.get());
	}
	
	@PostMapping
	public ClubDTO create(@RequestBody ClubDTO ClubCreated) {
		final Club club = clubMapper.mapToModel(ClubCreated);
		final Club clubCreated = clubService.create(club);
		return clubMapper.mapToDTO(clubCreated);
	}

}
