package cn.uenit.quizz.repository;

import cn.uenit.quizz.base.repository.IBaseRepository;
import cn.uenit.quizz.base.specification.QueryPath;
import cn.uenit.quizz.entity.QuestionEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface QuestionRepository extends IBaseRepository<QuestionEntity,Long> {

    @Query(nativeQuery = true,value = "select * from tb_quizz_question where test_id = ?1")
    List<QuestionEntity> getQuestionsByTestId(Long id);


    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "delete from tb_quizz_question where id=?1")
    int deleteEntityById(Long id);

}
