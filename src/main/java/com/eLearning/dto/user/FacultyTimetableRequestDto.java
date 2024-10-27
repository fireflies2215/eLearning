package com.eLearning.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FacultyTimetableRequestDto {


    private LocalDate date;

    private int facultyId;

}
