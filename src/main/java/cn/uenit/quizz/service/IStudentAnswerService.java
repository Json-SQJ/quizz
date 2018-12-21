package cn.uenit.quizz.service;

import cn.uenit.quizz.base.service.IBaseService;
import cn.uenit.quizz.entity.StudentAnswerEntity;
import cn.uenit.quizz.entity.TestEntity;

import java.util.List;

public interface IStudentAnswerService extends IBaseService<StudentAnswerEntity,Long> {

    List<TestEntity> getTestsByOpenId(String openid);


    List<StudentAnswerEntity> getListByTestIdAndOpenid(String id,String openid);
}
