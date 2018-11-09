package com.ats.ProjectAtS.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ats.ProjectAtS.DAOs.ResultDAO;
import com.ats.ProjectAtS.DAOs.ScoreDAO;
import com.ats.ProjectAtS.exceptions.NotFound;
import com.ats.ProjectAtS.models.Club;
import com.ats.ProjectAtS.models.Result;
import com.ats.ProjectAtS.models.Runner;
import com.ats.ProjectAtS.models.Score;
import com.ats.ProjectAtS.models.Trial;

//import lombok.extern.slf4j.Slf4j;

//@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class ResultServiceTest {
	
	@Mock
	ResultDAO resultDAOMock;
	
	@Mock
	ScoreDAO scoreDAOMock;
	
	@Mock
	TrialService trialService;
	
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
	public void givenIdReturnsResultsInTxt() throws NotFound {
		// Prueba:
		Trial storedTrial = new Trial();
		storedTrial.setIdTrial(1);
		storedTrial.setName("TrialName");
		
		// Clubes:
		Club club1 = new Club();
		club1.setIdClub(1);
		club1.setName("Club 1");
		Club club2 = new Club();
		club2.setIdClub(2);
		club2.setName("Club 2");
		
		// Corredores:
		Runner runner1 = new Runner();
		runner1.setIdRunner(1);
		runner1.setClub(club1);
		Runner runner2 = new Runner();
		runner2.setIdRunner(2);
		runner2.setClub(club2);
		Runner runner3 = new Runner();
		runner3.setIdRunner(3);
		runner3.setClub(club2);
		
		// Lista de resultados:
		List<Result> listResults = new ArrayList<>();
		Result result1 = new Result();
		result1.setSeconds(new BigDecimal(354.52));
		result1.setTrial(storedTrial);
		result1.setRunner(runner1);
		Result result2 = new Result();
		result2.setSeconds(new BigDecimal(314.32));
		result2.setTrial(storedTrial);
		result2.setRunner(runner2);
		Result result3 = new Result();
		result3.setSeconds(new BigDecimal(312.32));
		result3.setTrial(storedTrial);
		result3.setRunner(runner3);
		listResults.add(result1);
		listResults.add(result2);
		listResults.add(result3);
		
		// Lista de puntuaciones:
		List<Score> listScores = new ArrayList<>();
		Score score1 = new Score();
		score1.setPlace(1);
		score1.setValue(15);
		Score score2 = new Score();
		score2.setPlace(2);
		score2.setValue(10);
		Score score3 = new Score();
		score3.setPlace(3);
		score3.setValue(5);
		listScores.add(score1);
		listScores.add(score2);
		listScores.add(score3);
		
		when(trialService.findById(1)).thenReturn(Optional.ofNullable(storedTrial));
		when(resultDAOMock.findByTrial(storedTrial)).thenReturn(listResults);
		when(scoreDAOMock.findByTrial(storedTrial)).thenReturn(listScores);
		
		resultImpl.getResultsIntoTxt(1);
		
		Mockito.verify(trialService).findById(1);
		Mockito.verify(resultDAOMock).findByTrial(storedTrial);
		Mockito.verify(scoreDAOMock).findByTrial(storedTrial);
	}
	
	@Test(expected = NotFound.class)
	public void givenNonExistentIdOfATrial() throws NotFound {

		when(trialService.findById(Mockito.any(Integer.class))).thenReturn(Optional.empty());
		
		resultImpl.getResultsIntoTxt(1);
	}
	
	@Test
	public void givenTrialResultsAreEmpty() throws NotFound {
		List<Result> listResults = new ArrayList<>();
		
		Trial storedTrial= new Trial();
		storedTrial.setIdTrial(1);
		storedTrial.setName("TrialName");
		
		when(trialService.findById(1)).thenReturn(Optional.ofNullable(storedTrial));
		when(resultDAOMock.findByTrial(storedTrial)).thenReturn(listResults);
		
		resultImpl.getResultsIntoTxt(1);	
	}
	
	@Test(expected = NotFound.class)
	public void givenIdTrialItDoesntExist() throws NotFound {
		
		when(trialService.findById(Mockito.any(Integer.class))).thenReturn(Optional.empty());
		
		resultImpl.getResultsByMaster(1, 20);
		/*} catch (NotFound e) {
			a = 0;
			log.warn(e.getMessage());
			log.error(e.getMessage());
		}*/
	}
	
	@Test
	public void givenIdTrialAndMasterReturnsResults() throws NotFound {
		Date date = Date.valueOf("2018-02-18");
		Trial storedTrial= new Trial();
		storedTrial.setIdTrial(1);
		storedTrial.setDate(date);
		storedTrial.setName("TrialName");
		
		List<Result> listResults = new ArrayList<>();
		Result result1 = new Result();
		result1.setSeconds(new BigDecimal(354.52));
		result1.setTrial(storedTrial);
		listResults.add(result1);
		
		when(trialService.findById(Mockito.any(Integer.class))).thenReturn(Optional.ofNullable(storedTrial));
		when(resultDAOMock.getResultsByMaster(Mockito.eq(1), Mockito.any(Integer.class), Mockito.any(Integer.class), Mockito.any(Integer.class))).thenReturn(listResults);
		
		List<Result> listResultsReturned = resultImpl.getResultsByMaster(1, 20);
		
		assertNotEquals(listResultsReturned.size(), 0);
		assertEquals(listResultsReturned.size(), listResults.size());

	}
	
	
}
