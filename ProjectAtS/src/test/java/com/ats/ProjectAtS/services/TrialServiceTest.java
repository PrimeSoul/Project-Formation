package com.ats.ProjectAtS.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ats.ProjectAtS.DAOs.TrialDAO;
import com.ats.ProjectAtS.models.Trial;

@RunWith(MockitoJUnitRunner.class)
public class TrialServiceTest {

	@Mock
	TrialDAO trialDAOMock;
	
	@InjectMocks
	TrialServiceImpl trialImpl;
	
	@Test
	public void givenTrialCreateTrial () {
		// Given
		Trial storedTrial= new Trial();
		storedTrial.setIdTrial(1);
		storedTrial.setName("TrialName");
		// When
		when(trialDAOMock.save(Mockito.any(Trial.class))).thenReturn(storedTrial);
		// Then
		Trial newTrial = new Trial();
		newTrial.setName("TrialName");
		final Trial returnedTrial = trialImpl.create(newTrial);
		
		assertNotNull(returnedTrial);
		assertEquals(returnedTrial.getIdTrial(), storedTrial.getIdTrial());
		assertEquals(returnedTrial.getName(), storedTrial.getName());
	}
	
	@Test
	public void findTrialById () {
		Trial storedTrial = new Trial();
		storedTrial.setIdTrial(1);
		when(trialDAOMock.findById(1)).thenReturn(Optional.ofNullable(storedTrial));
		
		final Trial returnedTrial = trialImpl.findById(1).get();
		assertEquals(returnedTrial.getIdTrial(), storedTrial.getIdTrial());
	}

}
