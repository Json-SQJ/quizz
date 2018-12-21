package cn.uenit.quizz.entity;

import cn.uenit.quizz.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "tb_quizz_question")
public class QuestionEntity extends BaseEntity<Long> {

    private String questionName;
    private String analysis;
    private String testId;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @OrderBy("selecion ASC")
    private Set<AnswerEntity> answerSet;

}
