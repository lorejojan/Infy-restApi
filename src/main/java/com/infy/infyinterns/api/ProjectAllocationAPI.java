package com.infy.infyinterns.api;

import com.infy.infyinterns.dto.MentorDTO;
import com.infy.infyinterns.dto.ProjectDTO;
import com.infy.infyinterns.exception.InfyInternException;
import com.infy.infyinterns.service.ProjectAllocationService;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
@RequestMapping("infyinterns")

public class ProjectAllocationAPI {
    @Autowired
    private ProjectAllocationService projectService;
    @Autowired
    private Environment environment;


    @PostMapping("project")
    public ResponseEntity<String> allocateProject(@Valid @RequestBody ProjectDTO project) throws InfyInternException {
        return new ResponseEntity<>(environment.getProperty("API.ALLOCATION_SUCCESS")
                + ":" + projectService.allocateProject(project), HttpStatus.CREATED);
    }

    @GetMapping("mentor/{numberOfProjectsMentored}")
    public ResponseEntity<List<MentorDTO>> getMentors(@PathVariable Integer numberOfProjectsMentored) throws InfyInternException {
        return new ResponseEntity<List<MentorDTO>>(projectService.getMentors(numberOfProjectsMentored), HttpStatus.OK);
    }

    @PutMapping("project/{projectId}/{mentorId}")
    public ResponseEntity<?> updateProjectMentor(@PathVariable Integer projectId, @PathVariable Integer mentorId) throws InfyInternException {
        projectService.updateProjectMentor(projectId, mentorId);
        return new ResponseEntity<>(environment.getProperty("API.PROJECT_UPDATE_SUCCESS"), HttpStatus.OK);
    }

    @DeleteMapping("project/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable Integer projectId) throws InfyInternException {
        projectService.deleteProject(projectId);
        return new ResponseEntity<>(environment.getProperty("API.PROJECT_DELETE_SUCCESS"), HttpStatus.OK);
    }

}
