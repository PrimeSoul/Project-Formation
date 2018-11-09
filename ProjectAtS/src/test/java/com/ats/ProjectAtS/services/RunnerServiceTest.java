package com.ats.ProjectAtS.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.ats.ProjectAtS.DAOs.RunnerDAO;
import com.ats.ProjectAtS.models.Club;
import com.ats.ProjectAtS.models.Runner;

public class RunnerServiceTest {
	
	@Mock
	RunnerDAO runnerDAOMock;
	
	@Mock
	ClubService clubService;
	
	@InjectMocks
	RunnerServiceImpl runnerImpl;
	
	@Test
	public void givenRunnerCreateRunner () {
		// Given
		Club club = new Club();
		club.setIdClub(1);
		
		Runner storedRunner= new Runner();
		storedRunner.setIdRunner(1);
		storedRunner.setName("Pepe");
		storedRunner.setClub(club);
		// When
		when(runnerDAOMock.save(Mockito.any(Runner.class))).thenReturn(storedRunner);
		// Then
		Runner newRunner = new Runner();
		newRunner.setName("Pepe");
		final Runner returnedRunner = runnerImpl.create(newRunner);
		
		assertNotNull(returnedRunner);
		assertEquals(returnedRunner.getIdRunner(), storedRunner.getIdRunner());
		assertEquals(returnedRunner.getName(), storedRunner.getName());
	}
}
