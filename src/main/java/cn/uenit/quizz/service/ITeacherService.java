package cn.uenit.quizz.service;

import cn.uenit.quizz.base.service.IBaseService;
import cn.uenit.quizz.entity.TeacherEntity;

import java.util.List;

public interface ITeacherService extends IBaseService<TeacherEntity,Long> {

    List<TeacherEntity> getAllByUsernameAndPassword(String username, String password);

    void updatePassword(String userId,String password);

    boolean checkUsernameUnique(String username);
}
