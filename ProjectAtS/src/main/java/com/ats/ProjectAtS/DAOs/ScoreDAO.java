package com.ats.ProjectAtS.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.ProjectAtS.models.Score;

@Repository
public interface ScoreDAO extends JpaRepository<Score, Integer> {

}
