package com.ats.ProjectAtS.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ats.ProjectAtS.DAOs.ClubDAO;
import com.ats.ProjectAtS.models.Club;

@RunWith(MockitoJUnitRunner.class)
public class ClubServiceTest {
	
	@Mock
	ClubDAO clubDAOMock;
	
	@InjectMocks
	ClubServiceImpl clubImpl;
	
	@Test
	public void givenClubCreateClub () {
		// Given
		Club storedClub = new Club();
		storedClub.setIdClub(1);
		storedClub.setName("ClubName");
		// When
		when(clubDAOMock.save(Mockito.any(Club.class))).thenReturn(storedClub);
		// Then
		Club newClub = new Club();
		newClub.setName("ClubName");
		final Club returnedClub = clubImpl.create(newClub);
		
		assertNotNull(returnedClub);
		assertEquals(returnedClub.getIdClub(), storedClub.getIdClub());
		assertEquals(returnedClub.getName(), storedClub.getName());
	}
	
	@Test
	public void findAllClubs () {
		List<Club> listClubs = new ArrayList<>();
		Club club1 = new Club();
		Club club2 = new Club();
		listClubs.add(club1);
		listClubs.add(club2);
		
		when(clubDAOMock.findAll()).thenReturn(listClubs);
		
		List<Club> returnedClubs = clubImpl.findAll();
		assertNotEquals(returnedClubs.size(), 0);
		assertEquals(returnedClubs.size(), listClubs.size());
	}
	
	@Test
	public void findClubById () {
		Club storedClub = new Club();
		storedClub.setIdClub(1);
		when(clubDAOMock.findById(1)).thenReturn(Optional.ofNullable(storedClub));
		
		final Club returnedClub = clubImpl.findById(1).get();
		assertEquals(returnedClub.getIdClub(), storedClub.getIdClub());
	}
	
	/*@Test(expected = NotFound.class)
	public void exceptionInFindClubById () {
		Club storedClub = new Club();
		storedClub.setIdClub(1);
		when(clubDAOMock.findById(1)).thenReturn(Optional.empty());
		
		final Club returnedClub = clubImpl.findById(1);
	}*/
}
