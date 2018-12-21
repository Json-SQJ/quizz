package cn.uenit.quizz.repository;

import cn.uenit.quizz.base.repository.IBaseRepository;
import cn.uenit.quizz.entity.TestEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends IBaseRepository<TestEntity,Long> {
}
