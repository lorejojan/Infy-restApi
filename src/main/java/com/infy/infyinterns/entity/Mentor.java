package com.infy.infyinterns.entity;

import com.infy.infyinterns.dto.MentorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mentor")
public class Mentor {
    @Id
    @NotNull(message = "${mentor.mentorid.absent}")
    private int mentorId;
    @NotNull(message = "${mentor.name.absent}")
    private String mentorName;

    private int numberOfProjectsMentored;

    public Mentor(MentorDTO mentorDTO) {
        this.mentorId = mentorDTO.getMentorId();
        this.mentorName = mentorDTO.getMentorName();
        this.numberOfProjectsMentored = mentorDTO.getNumberOfProjectsMentored();
    }


}
