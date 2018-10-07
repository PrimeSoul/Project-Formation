package com.ats.ProjectAtS.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.ProjectAtS.models.Result;

@Repository
public interface ResultDAO extends JpaRepository<Result, Integer> {

}
