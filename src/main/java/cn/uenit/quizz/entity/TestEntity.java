package cn.uenit.quizz.entity;

import cn.uenit.quizz.base.entity.BaseEntity;
import cn.uenit.quizz.base.specification.Operator;
import cn.uenit.quizz.base.specification.QueryPath;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Data
@Table(name = "tb_quizz_info")
public class TestEntity extends BaseEntity<Long> {

    private String quizzName;
    private String quizzComment;
    private Boolean setTime;
    private Integer quizzTime;
    @Transient
    private String time;

    @QueryPath(operator = Operator.LIKE)
    public String getQuizzName(){
        return quizzName;
    }

}
