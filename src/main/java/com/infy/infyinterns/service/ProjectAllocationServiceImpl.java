package com.infy.infyinterns.service;

import com.infy.infyinterns.dto.MentorDTO;
import com.infy.infyinterns.dto.ProjectDTO;
import com.infy.infyinterns.entity.Mentor;
import com.infy.infyinterns.entity.Project;
import com.infy.infyinterns.exception.InfyInternException;
import com.infy.infyinterns.repository.MentorRepository;
import com.infy.infyinterns.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "projectService")

@EnableAutoConfiguration
@Transactional
@Resource(name = "application")
public class ProjectAllocationServiceImpl implements ProjectAllocationService {
    @Autowired
    MentorRepository mentorRepository;
    @Autowired
    ProjectRepository projectRepository;

    @Override
    public Integer allocateProject(ProjectDTO project) throws InfyInternException {
        final int mId = project.getMentorDTO().getMentorId();
        if (mId < 999 || mId >= 9999) {
            throw new InfyInternException("Service.MENTOR_ID_INVALID");
        }
        Optional<Mentor> mentorFromRepo = mentorRepository.findById(project.getMentorDTO().getMentorId());
        Mentor mentor = mentorFromRepo.orElseThrow(() -> new InfyInternException("Service.MENTOR_NOT_FOUND"));
        if (mentorFromRepo.get().getNumberOfProjectsMentored() >= 3) {
            throw new InfyInternException("Service.CANNOT_ALLOCATE_PROJECT");
        }
            mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored() + 1);
            project.setMentorDTO(new MentorDTO(mentor));
            projectRepository.save(new Project(project));
            mentorRepository.save(mentor);
            Project toReturn = projectRepository.findProjectByProjectNameEquals(project.getProjectName());
            return toReturn.getProjectId();




    }

    @Override
    public List<MentorDTO> getMentors(int numberOfProjectsMentored) throws InfyInternException {
        List<MentorDTO> mentorDTODTOList = new ArrayList<MentorDTO>();
        List<Mentor> mentorList = mentorRepository.findAllByNumberOfProjectsMentored(numberOfProjectsMentored);

        if (mentorList.isEmpty()) {
            throw new InfyInternException("Service.MENTOR_NOT_FOUND");
        } else {
            mentorList.forEach(x -> mentorDTODTOList.add(new MentorDTO(x)));
            return mentorDTODTOList;
        }

    }

    @Override
    public void updateProjectMentor(int projectId, int mentorId) throws InfyInternException {

        Optional<Mentor> mentorOptional = mentorRepository.findById(mentorId);
        Mentor mentor = mentorOptional.orElseThrow(() -> new InfyInternException("Service.MENTOR_NOT_FOUND"));
        if (mentor.getNumberOfProjectsMentored() >= 3) {
            throw new InfyInternException("Service.CANNOT_ALLOCATE_PROJECT");
        }
        else
        {

        }
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        Project project = projectOptional.orElseThrow(() -> new InfyInternException("Service.PROJECT_NOT_FOUND"));
        project.setMentor(mentor);
        mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored() + 1);
        projectRepository.save(project);
        mentorRepository.save(mentor);
    }

    @Override
    public void deleteProject(int projectId) throws InfyInternException {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        Project project = projectOptional.orElseThrow(() -> new InfyInternException("Service.PROJECT_NOT_FOUND"));
        if (project.getMentor() == null) {
            projectRepository.deleteById(projectId);
        }

            project.getMentor().setNumberOfProjectsMentored(project.getMentor().getNumberOfProjectsMentored() - 1);
            mentorRepository.save(project.getMentor());
            project.setMentor(null);
            projectRepository.deleteById(projectId);

    }
}
