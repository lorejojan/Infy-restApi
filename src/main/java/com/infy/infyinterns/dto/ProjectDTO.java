package com.infy.infyinterns.dto;

import com.infy.infyinterns.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private int projectId;
    @NotNull(message = "${project.projectname.absent}")
    private String projectName;
    @NotNull(message = "${project.ideaowner.absent}")
    private int ideaOwner;

    @NotNull(message = "${project.releasedate.absent}")
    private LocalDate releaseDate;
    @NotNull(message = "${project.mentor.absent}")
    private MentorDTO mentorDTO;

    @PrePersist
    public void setReleaseDate() {
        this.releaseDate = LocalDate.now();
    }

    public ProjectDTO(Project project) {
        this.projectId = project.getProjectId();
        this.projectName = project.getProjectName();
        this.releaseDate = project.getReleaseDate();
        com.infy.infyinterns.entity.Mentor mentor = project.getMentor();
        this.mentorDTO = new MentorDTO(mentor);
    }
}
