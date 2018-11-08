package com.ats.ProjectAtS.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ats.ProjectAtS.DAOs.ResultDAO;
import com.ats.ProjectAtS.models.Result;

@RunWith(MockitoJUnitRunner.class)
public class ResultServiceTest {
	
	@Mock
	ResultDAO resultDAOMock;
	
	@InjectMocks
	ResultServiceImpl resultImpl;
	
	@Test
	public void givenResultCreateResult () {
		// Given
		Result storedResult = new Result();
		storedResult.setIdResult(1);
		// When
		when(resultDAOMock.save(Mockito.any(Result.class))).thenReturn(storedResult);
		// Then
		Result newClub = new Result();
		final Result returnedClub = resultImpl.create(newClub);
		
		assertNotNull(returnedClub);
		assertEquals(returnedClub.getIdResult(), storedResult.getIdResult());
	}
	
	@Test
	public void findAllResults () {
		List<Result> listResults = new ArrayList<>();
		Result result1 = new Result();
		Result result2 = new Result();
		listResults.add(result1);
		listResults.add(result2);
		
		when(resultDAOMock.findAll()).thenReturn(listResults);
		
		List<Result> returnedResults = resultImpl.findAll();
		assertNotEquals(returnedResults.size(), 0);
		assertEquals(returnedResults.size(), listResults.size());
	}
	
	@Test
	public void givenIdReturnsResultsInTxt() {
		
	}
}
