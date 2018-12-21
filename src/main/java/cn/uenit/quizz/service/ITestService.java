package cn.uenit.quizz.service;

import cn.uenit.quizz.base.service.IBaseService;
import cn.uenit.quizz.entity.TestEntity;

import java.util.List;

public interface ITestService extends IBaseService<TestEntity,Long> {

    List<TestEntity> findAll(TestEntity testEntity);

    TestEntity getByTestId(long id);
}
