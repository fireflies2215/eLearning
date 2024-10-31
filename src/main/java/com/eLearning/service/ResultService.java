package com.eLearning.service;

import com.eLearning.entity.Result;

import java.util.List;

public interface ResultService {

    public Result addResult(Result result);
    public List<Result> getResultByStudent(int studentId);

    public List<Result> getResultBySubject(int subjectId);
}
