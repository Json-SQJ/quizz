package cn.uenit.quizz.repository;

import cn.uenit.quizz.base.repository.IBaseRepository;
import cn.uenit.quizz.entity.StudentAnswerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentAnswerRepository extends IBaseRepository<StudentAnswerEntity,Long> {

    @Query(nativeQuery = true,value = "select distinct test_id from tb_quizz_student_answer where open_id =?1")
    List<Integer> getTestIdsByOpenid(String openid);

    @Query(nativeQuery = true,value = "select * from tb_quizz_student_answer where test_id=?1 and open_id=?2 order by create_time ASC")
    List<StudentAnswerEntity> getByTestIdAndOpenId(String id,String openid);

}
