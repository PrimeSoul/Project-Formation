package com.ats.ProjectAtS.DAOs;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.ProjectAtS.models.Runner;

@Repository
public interface RunnerDAO extends JpaRepository<Runner, Integer> {
	
	Page<Runner> findByNameContaining(String name, Pageable pageable);

}
