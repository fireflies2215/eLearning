package com.eLearning.dto.user;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ClassTimeTableRequestDto {
    private LocalDate date;

    private int classId;
}
