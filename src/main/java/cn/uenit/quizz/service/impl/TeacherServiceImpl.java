package cn.uenit.quizz.service.impl;


import cn.uenit.quizz.base.service.impl.BaseServiceImpl;
import cn.uenit.quizz.entity.TeacherEntity;
import cn.uenit.quizz.repository.TeacherRepository;
import cn.uenit.quizz.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl extends BaseServiceImpl<TeacherEntity,Long> implements ITeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<TeacherEntity> getAllByUsernameAndPassword(String username, String password) {
        return teacherRepository.getAllByUsernameAndPassword(username, password);
    }

    @Override
    public void updatePassword(String userId, String password) {
        long id = Long.parseLong(userId);
        teacherRepository.updatePassword(id,password);
    }


    @Override
    public boolean checkUsernameUnique(String username) {
       TeacherEntity entity =   teacherRepository.getByUsername(username);
       if(entity!=null) {
           return false;
       }else {
           return  true;
       }
    }
}
