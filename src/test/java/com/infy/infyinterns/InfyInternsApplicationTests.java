package com.infy.infyinterns;

import java.time.LocalDate;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import com.infy.infyinterns.dto.MentorDTO;
import com.infy.infyinterns.dto.ProjectDTO;
import com.infy.infyinterns.entity.Mentor;
import com.infy.infyinterns.entity.Project;
import com.infy.infyinterns.exception.InfyInternException;
import com.infy.infyinterns.repository.MentorRepository;
import com.infy.infyinterns.service.ProjectAllocationService;
import com.infy.infyinterns.service.ProjectAllocationServiceImpl;

import javax.persistence.PersistenceContext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class InfyInternsApplicationTests {

	@Mock
	private MentorRepository mentorRepository;

	@InjectMocks
	private ProjectAllocationService projectAllocationService = new ProjectAllocationServiceImpl();

	@Test
	public void allocateProjectMentorNotFoundTest() throws InfyInternException {
		ProjectDTO projectDTO=new ProjectDTO();

		projectDTO.setIdeaOwner(7);
		projectDTO.setProjectId(12);
		projectDTO.setProjectName("jjsszcxz");
		projectDTO.setReleaseDate(LocalDate.now());

		MentorDTO mentorDTO=new MentorDTO();
		mentorDTO.setMentorId(1019);
		mentorDTO.setMentorName("js");
		mentorDTO.setNumberOfProjectsMentored(3);
		projectDTO.setMentorDTO(mentorDTO);
		InfyInternException exception=Assertions.assertThrows(InfyInternException.class,() -> projectAllocationService.allocateProject(projectDTO));
		Assertions.assertEquals("Service.MENTOR_NOT_FOUND", exception.getMessage());

	}

	@Test
	public void allocateProjectMentorIdInvalidTest() throws Exception {
		ProjectDTO projectDTO=new ProjectDTO();

		projectDTO.setIdeaOwner(7);
		projectDTO.setProjectId(1);
		projectDTO.setProjectName("jj");
		projectDTO.setReleaseDate(LocalDate.now());

		MentorDTO mentorDTO=new MentorDTO();
		mentorDTO.setMentorId(1);
		mentorDTO.setMentorName("js");
		mentorDTO.setNumberOfProjectsMentored(3);

		Mentor mentor=new Mentor();
		mentor.setMentorId(mentorDTO.getMentorId());
		mentor.setMentorName(mentorDTO.getMentorName());
		mentor.setNumberOfProjectsMentored(mentorDTO.getNumberOfProjectsMentored());

		projectDTO.setMentorDTO(mentorDTO);

		mentorRepository.save(mentor);
		InfyInternException exception=Assertions.assertThrows(InfyInternException.class, () -> projectAllocationService.allocateProject(projectDTO));
		Assertions.assertEquals("Service.MENTOR_ID_INVALID", exception.getMessage());

	}
}