package cn.uenit.quizz.service.impl;

import cn.uenit.quizz.base.service.impl.BaseServiceImpl;
import cn.uenit.quizz.entity.StudentAnswerEntity;
import cn.uenit.quizz.entity.TestEntity;
import cn.uenit.quizz.repository.StudentAnswerRepository;
import cn.uenit.quizz.service.IStudentAnswerService;
import cn.uenit.quizz.service.ITestService;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class StudentAnswerServiceImpl extends BaseServiceImpl<StudentAnswerEntity,Long> implements IStudentAnswerService {

    @Autowired
    private StudentAnswerRepository studentAnswerRepository;

    @Autowired
    private ITestService testService;

    @Override
    public List<TestEntity> getTestsByOpenId(String openid) {
        List<Integer> testIds = studentAnswerRepository.getTestIdsByOpenid(openid);
        List<TestEntity> testEntities = new ArrayList<>();
        for(Integer id:testIds){
            TestEntity testEntity = testService.getById(id.longValue());
            testEntities.add(testEntity);
        }
        return testEntities;
    }

    @Override
    public List<StudentAnswerEntity> getListByTestIdAndOpenid(String id, String openid) {
        return studentAnswerRepository.getByTestIdAndOpenId(id,openid);
    }
}
