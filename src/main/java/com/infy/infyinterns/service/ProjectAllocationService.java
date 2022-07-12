package com.infy.infyinterns.service;

import com.infy.infyinterns.dto.MentorDTO;
import com.infy.infyinterns.dto.ProjectDTO;
import com.infy.infyinterns.exception.InfyInternException;

import java.util.List;

public interface ProjectAllocationService {
    public Integer allocateProject(ProjectDTO project) throws InfyInternException;

    public List<MentorDTO> getMentors(int numberOfProjectsMentored) throws InfyInternException;

    public void updateProjectMentor(int projectId, int mentorId) throws InfyInternException;

    public void deleteProject(int projectId) throws InfyInternException;
}
