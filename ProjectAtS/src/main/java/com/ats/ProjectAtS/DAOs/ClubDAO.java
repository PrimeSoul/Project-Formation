package com.ats.ProjectAtS.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.ProjectAtS.models.Club;

@Repository
public interface ClubDAO extends JpaRepository<Club, Integer> {

}
