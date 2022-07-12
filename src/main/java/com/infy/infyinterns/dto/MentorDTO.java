package com.infy.infyinterns.dto;

import com.infy.infyinterns.entity.Mentor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MentorDTO {
    @Min(value = 1000, message = "{mentor.mentorid.invalid}")
    @Max(value = 9999, message = "{mentor.mentorid.invalid}")
    @NotNull(message = "${mentor.mentorid.absent}")
    private int mentorId;
    @NotNull(message = "${mentor.name.absent}")
    private String mentorName;
    private int numberOfProjectsMentored;

    public MentorDTO(Mentor mentor) {
        this.mentorId = mentor.getMentorId();
        this.mentorName = mentor.getMentorName();
        this.numberOfProjectsMentored = mentor.getNumberOfProjectsMentored();
    }

}
