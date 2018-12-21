package cn.uenit.quizz.repository;

import cn.uenit.quizz.base.repository.IBaseRepository;
import cn.uenit.quizz.entity.AnswerEntity;

import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends IBaseRepository<AnswerEntity, Long> {

}
