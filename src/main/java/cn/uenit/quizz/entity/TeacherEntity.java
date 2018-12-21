package cn.uenit.quizz.entity;

import cn.uenit.quizz.base.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity
@Data
@Table(name = "tb_quizz_teacher_user")
public class TeacherEntity extends BaseEntity<Long>  implements Serializable {

    private String username;
    private String password;

    @Transient
    private String token;

}
