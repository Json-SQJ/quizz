package cn.uenit.quizz.service;

import cn.uenit.quizz.base.service.IBaseService;
import cn.uenit.quizz.entity.QuestionEntity;

import java.util.List;

public interface IQuestionService  extends IBaseService<QuestionEntity,Long> {

    List<QuestionEntity> getQuestionsByTestId(Long id);

    int deleteEntityById(Long id);
}
