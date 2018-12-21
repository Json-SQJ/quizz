package cn.uenit.quizz.controller.web;

import cn.uenit.quizz.base.config.RestJsonResponse;
import cn.uenit.quizz.base.entity.ResponseResultEntity;
import cn.uenit.quizz.base.utils.Action;
import cn.uenit.quizz.base.utils.Md5Utils;
import cn.uenit.quizz.base.utils.RedisUtil;
import cn.uenit.quizz.base.utils.StringUtils;
import cn.uenit.quizz.entity.TeacherEntity;
import cn.uenit.quizz.service.ITeacherService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/web/teacher")
public class TeacherController {

    private static final Logger logger = Logger.getLogger(TeacherController.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ITeacherService teacherService;

    @RequestMapping(value = "/login")
    @RestJsonResponse(auth = Action.CHECKTOKENSKIP)
    public Object login(String username, String password, String secret) throws Exception {
        String usernameMd5 = Md5Utils.getMD5(username + "quizz");
        if (StringUtils.isNullOrEmpty(secret) || (!usernameMd5.equals(secret))) {
            return new ResponseResultEntity(10000, "非法访问", null, false);
        }
        List<TeacherEntity> teacherEntities = teacherService.getAllByUsernameAndPassword(username, password);
        if (teacherEntities.size() != 1) {
            return new ResponseResultEntity(10001, "用户名或密码错误，请重新输入", null, false);
        }
        String token = StringUtils.getToken();
        redisUtil.set(teacherEntities.get(0).getId() + "-token", token, 3600 * 60L);
        TeacherEntity teacherEntity = teacherEntities.get(0);
        teacherEntity.setToken(token);
        return new ResponseResultEntity(200, "登陆成功", teacherEntity, true);
    }

    @RequestMapping(value = "/register")
    @RestJsonResponse(auth = Action.CHECKTOKENSKIP)
    public Object register( String username,  String password,  String secret) throws Exception{
        String usernameMd5 = Md5Utils.getMD5(username + "quizz");
        if (StringUtils.isNullOrEmpty(secret) || (!usernameMd5.equals(secret))) {
            return new ResponseResultEntity(10000, "非法访问", null, false);
        }
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setUsername(username);
        teacherEntity.setPassword(password);
        teacherService.saveEntity(teacherEntity);
        return new ResponseResultEntity();
    }

    @RequestMapping(value = "/changePwd")
    @RestJsonResponse(auth = Action.CHECKTOKEN)
    public Object changePwd(HttpServletRequest request, String passwordBefore,String password,String username) throws Exception{
        List<TeacherEntity> teacherEntities = teacherService.getAllByUsernameAndPassword(username, passwordBefore);
        if (teacherEntities.size() != 1) {
            return new ResponseResultEntity(10001, "原密码错误", null, false);
        }
        String userId = request.getHeader("userId");
        teacherService.updatePassword(userId,password);
        return new ResponseResultEntity();
    }


    @RequestMapping(value = "/logout")
    @RestJsonResponse(auth = Action.CHECKTOKEN)
    public Object logout(HttpServletRequest request){
        String userId = request.getHeader("userId");
        redisUtil.remove(userId+"-token");
        return new ResponseResultEntity();
    }

    @RequestMapping(value = "/checkUsername")
    @RestJsonResponse
    public Object checkUsername(String username){
       boolean  result =  teacherService.checkUsernameUnique(username);
       if(result){
           return new ResponseResultEntity(200,"成功",null,true);
       }else{
           return new ResponseResultEntity(10000,"用户名已被使用",null,false);
       }
    }

}
