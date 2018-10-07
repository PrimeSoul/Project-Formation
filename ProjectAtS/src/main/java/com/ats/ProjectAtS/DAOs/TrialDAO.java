package com.ats.ProjectAtS.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.ProjectAtS.models.Trial;

@Repository
public interface TrialDAO extends JpaRepository<Trial, Integer> {

}
