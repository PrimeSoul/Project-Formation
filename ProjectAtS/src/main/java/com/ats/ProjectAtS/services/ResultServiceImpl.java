package com.ats.ProjectAtS.services;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ats.ProjectAtS.DAOs.ResultDAO;
import com.ats.ProjectAtS.DAOs.ScoreDAO;
import com.ats.ProjectAtS.exceptions.NotFound;
import com.ats.ProjectAtS.models.Result;
import com.ats.ProjectAtS.models.Score;
import com.ats.ProjectAtS.models.Trial;



@Service
public class ResultServiceImpl implements ResultService {

	@Autowired
	ResultDAO dao;
	@Autowired
	ScoreDAO scoreDao;
	@Autowired
	TrialService trialService;
	
	@Override
	public Result create(Result result) {
		return dao.save(result);
	}
	
	@Override
	public void getResultsIntoTxt (Integer idTrial) throws NotFound {
		Trial trialChosen = trialService.findById(idTrial).orElseThrow(() -> new NotFound("Error: Trial does not exist."));
		List<Result> resultsOfTrial = dao.findByTrial(trialChosen);
		Collections.sort(resultsOfTrial, new Comparator<Result>() {
		    @Override
		    public int compare(Result a1, Result a2) {
		        return a1.getSeconds().compareTo(a2.getSeconds());
		    }
		});
		
		List<Score> scoresOfTrial = scoreDao.findByTrial(trialChosen);
		
		Map <String, Integer> scoreboard = new HashMap<>();
		
		for(Integer i = 0; i < scoresOfTrial.size(); i++) {		
			if(resultsOfTrial.size() > i) {
				String clubName = resultsOfTrial.get(i).getRunner().getClub().getName();
				Integer clubPoints = scoresOfTrial.get(i).getValue();
				
				if(scoreboard.containsKey(clubName))
					scoreboard.put(clubName, scoreboard.get(clubName)+clubPoints);
				else
					scoreboard.put(clubName, clubPoints);
			}	
		}
		
		Map<String, Integer> sortedScoreboard = scoreboard.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
	    		.collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

		try {
			FileWriter fileWriter = new FileWriter("ClubScores.txt");
		    PrintWriter printWriter = new PrintWriter(fileWriter);
		    
		    printWriter.print("CLUB\t\tSCORE\r\n");
		    sortedScoreboard.forEach((club, score) -> printWriter.print(club+"\t\t"+score+"\r\n"));
		    
		    printWriter.close();
		} catch (IOException e) {}
		
	}

	@Override
	public List<Result> getResultsByMaster(Integer idTrial, Integer master) {
		Date date = trialService.findById(idTrial).get().getDate();
		String[] dateApart = date.toString().split("-");
		Integer yearTrial = Integer.parseInt(dateApart[0]);
		Integer difference = 10;
		if(master >= 40) difference = 100;
		List<Result> results = dao.getResultsByMaster(idTrial, master, master+difference, yearTrial);
		return results;
	}

}
