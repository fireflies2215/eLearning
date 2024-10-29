package com.eLearning.service.impl;

import com.eLearning.entity.Result;
import com.eLearning.repository.ResultRepository;
import com.eLearning.service.ResultService;
import com.eLearning.util.ResourceAlreadyExitsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Override
    public Result addResult(Result result) {
        System.out.println(result);

            var checkResult=resultRepository.findByUserIdAndSubjectId(result.getUser().getId(),result.getSubject().getId());
            if(checkResult.isPresent()){
                throw new ResourceAlreadyExitsException("result already added");
            }
            var studentResult=resultRepository.save(result);
            return studentResult;

    }

    @Override
    public List<Result> getResultByStudent(int studentId) {
        try{
            var studentResult=resultRepository.findByUserId(studentId);
            return studentResult;
        }

        catch (Exception e){

            throw new RuntimeException("Error While fetching result : "+e.getMessage());
        }
    }
}
