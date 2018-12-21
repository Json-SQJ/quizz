package cn.uenit.quizz.repository;

import cn.uenit.quizz.base.repository.IBaseRepository;
import cn.uenit.quizz.base.utils.PassWordUtils;
import cn.uenit.quizz.entity.TeacherEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TeacherRepository  extends IBaseRepository<TeacherEntity,Long> {

    List<TeacherEntity> getAllByUsernameAndPassword(String password, String username);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "update tb_quizz_teacher_user set password = ?2 where id=?1 ")
    void updatePassword(long id,String password);

    TeacherEntity getByUsername(String username);

}
