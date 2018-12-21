package cn.uenit.quizz.service.impl;

import cn.uenit.quizz.base.service.impl.BaseServiceImpl;
import cn.uenit.quizz.entity.QuestionEntity;
import cn.uenit.quizz.repository.QuestionRepository;
import cn.uenit.quizz.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl extends BaseServiceImpl<QuestionEntity,Long> implements IQuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<QuestionEntity> getQuestionsByTestId(Long id) {
        List<QuestionEntity> questionEntities = questionRepository.getQuestionsByTestId(id);
        return questionEntities;
    }


    @Override
    public int deleteEntityById(Long id) {
        return questionRepository.deleteEntityById(id);
    }
}
