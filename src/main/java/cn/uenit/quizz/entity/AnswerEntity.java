package cn.uenit.quizz.entity;

import cn.uenit.quizz.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tb_quizz_question_selection" )
public class AnswerEntity extends BaseEntity<Long> {

    private String selecion;
    private Integer isCorrect;
    private String selectionContent;
}
