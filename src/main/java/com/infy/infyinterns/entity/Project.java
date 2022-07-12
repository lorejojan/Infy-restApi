package com.infy.infyinterns.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.infy.infyinterns.dto.MentorDTO;
import com.infy.infyinterns.dto.ProjectDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int projectId;
    @NotNull(message = "${project.projectname.absent}")
    private String projectName;
    @NotNull(message = "${project.ideaowner.absent}")
    private int ideaOwner;

    @NotNull(message = "${project.releasedate.absent}")
    private LocalDate releaseDate;
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    @NotNull(message = "${project.mentor.absent }")
    Mentor mentor;

    public Project(ProjectDTO projectDTO) {
        this.projectId = projectDTO.getProjectId();
        this.projectName = projectDTO.getProjectName();
        this.ideaOwner = projectDTO.getIdeaOwner();
        this.releaseDate = projectDTO.getReleaseDate();
        MentorDTO mentorDTO = projectDTO.getMentorDTO();
        this.mentor = new com.infy.infyinterns.entity.Mentor(mentorDTO);
    }

}
