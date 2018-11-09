package com.ats.ProjectAtS.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.ats.ProjectAtS.DAOs.RunnerDAO;
import com.ats.ProjectAtS.exceptions.NotFound;
import com.ats.ProjectAtS.models.Club;
import com.ats.ProjectAtS.models.Runner;

@RunWith(MockitoJUnitRunner.class)
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
		Runner storedRunner= new Runner();
		storedRunner.setIdRunner(1);
		storedRunner.setName("Pepe");
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
	
	@Test
	public void givenPageAndNameFindAllRunners () {
		
		List<Runner> listRunners = new ArrayList<>();
		Runner runner1 = new Runner();
		Runner runner2 = new Runner();
		listRunners.add(runner1);
		listRunners.add(runner2);
		
		Page<Runner> runners = new PageImpl<Runner>(listRunners);
		
		when(runnerDAOMock.findByNameContaining(Mockito.anyString(), Mockito.any(Pageable.class))).thenReturn(runners);
	
		Pageable pageable = PageRequest.of(0, 5);
		List<Runner> returnedRunners = runnerImpl.findAll(pageable, "");
		assertNotEquals(returnedRunners.size(), 0);
		assertEquals(returnedRunners.size(), listRunners.size());
	}
	
	@Test
	public void givenIdRunnerReturnsRunner () {
		Runner storedRunner = new Runner();
		storedRunner.setIdRunner(1);
		
		when(runnerDAOMock.findById(1)).thenReturn(Optional.ofNullable(storedRunner));
		
		final Runner returnedRunner = runnerImpl.findById(1).orElse(new Runner());
		assertEquals(returnedRunner.getIdRunner(), storedRunner.getIdRunner());
	}
	
	@Test
	public void givenIdAndNewRunnerThenUpdate () throws NotFound {
		Runner storedRunner = new Runner();
		storedRunner.setIdRunner(2);
		
		Runner storedRunnerName = new Runner();
		storedRunnerName.setName("Bill");
		
		when(runnerDAOMock.findById(2)).thenReturn(Optional.ofNullable(storedRunner));
		when(runnerDAOMock.save(Mockito.any(Runner.class))).thenReturn(storedRunnerName);
		
		Runner updatedRunner = new Runner();
		updatedRunner.setName("Bill");
		runnerImpl.update(2, updatedRunner);
		
		Mockito.verify(runnerDAOMock).findById(Mockito.eq(2));
		Mockito.verify(runnerDAOMock).save(Mockito.any(Runner.class));
		assertEquals(updatedRunner.getName(), storedRunnerName.getName());
	}
	
	@Test(expected = NotFound.class)
	public void givenIdRunnerToUpdateDoesNotExist () throws NotFound {
		when(runnerDAOMock.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		
		Runner updatedRunner = new Runner();
		updatedRunner.setName("Bill");
		
		runnerImpl.update(2, updatedRunner);
	}
	
	@Test
	public void givenIdRunnerAndIdClubAssignClubToRunner () throws NotFound {
		Runner storedRunner = new Runner();
		storedRunner.setIdRunner(1);
		
		Club storedClub = new Club();
		storedClub.setIdClub(1);
		
		when(runnerDAOMock.findById(1)).thenReturn(Optional.ofNullable(storedRunner));
		when(clubService.findById(1)).thenReturn(Optional.ofNullable(storedClub));
		when(runnerDAOMock.findById(1)).thenReturn(Optional.ofNullable(storedRunner));
		
		runnerImpl.assignClub(1, 1);
		Mockito.verify(runnerDAOMock).findById(1);
		Mockito.verify(clubService).findById(1);
	}
	
	@Test(expected = NotFound.class)
	public void givenIdRunnerAndIdClubWhenRunnerDoesNotExist () throws NotFound {
		when(runnerDAOMock.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		
		runnerImpl.assignClub(1, 1);
	}
	
	@Test(expected = NotFound.class)
	public void givenIdRunnerAndIdClubWhenClubDoesNotExist () throws NotFound {
		when(runnerDAOMock.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(new Runner()));
		when(clubService.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		
		runnerImpl.assignClub(1, 1);
	}
	
	@Test
	public void givenIdDeleteRunner () {
		runnerImpl.delete(1);
		Mockito.verify(runnerDAOMock).deleteById(Mockito.anyInt());
	}
	
}
