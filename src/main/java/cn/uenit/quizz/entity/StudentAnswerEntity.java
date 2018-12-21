package cn.uenit.quizz.entity;

import cn.uenit.quizz.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "tb_quizz_student_answer")
@Entity
@Data
public class StudentAnswerEntity extends BaseEntity<Long> {

    private String openId;
    private Long questionId;
    private Long testId;
    private  Integer isCorrect;

}
