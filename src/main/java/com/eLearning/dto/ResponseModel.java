package com.eLearning.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel {
    private String message;
    private String status;
    private Object data;
    private List<String> errorMessages;
}
