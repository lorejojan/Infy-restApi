package com.infy.infyinterns.repository;

import com.infy.infyinterns.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Project findProjectByProjectNameEquals(String projectName);
}
