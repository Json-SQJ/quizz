package cn.uenit.quizz.controller.web;

import cn.uenit.quizz.base.config.RestJsonResponse;
import cn.uenit.quizz.base.entity.ResponseResultEntity;
import cn.uenit.quizz.base.utils.Action;
import cn.uenit.quizz.base.utils.RedisUtil;
import cn.uenit.quizz.base.utils.StringUtils;
import cn.uenit.quizz.entity.TeacherEntity;
import cn.uenit.quizz.entity.TestEntity;
import cn.uenit.quizz.service.ITestService;
import cn.uenit.quizz.utils.QRCodeUtil;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/web/test")
public class TestController {

    private static Logger logger = Logger.getLogger(TeacherEntity.class);

    @Autowired
    private ITestService testService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/addTest",method = RequestMethod.POST)
    @RestJsonResponse(auth = Action.CHECKTOKEN)
    public Object addTest(HttpServletRequest request, @RequestBody TestEntity testEntity){
        String userId = request.getHeader("userId");
        testEntity.setCreateUser(userId);
        TestEntity entity = testService.saveOrUpdate(testEntity);
        return new ResponseResultEntity(200,"成功",entity,true);
    }

    @RequestMapping("/listTest")
    @RestJsonResponse(auth = Action.CHECKTOKEN)
    public Object listTest(HttpServletRequest request,String search){
        String userId = request.getHeader("userId");
        TestEntity testEntity = new TestEntity();
        testEntity.setQuizzName(search);
        testEntity.setCreateUser(userId);
        List<TestEntity> testEntities = testService.findAll(testEntity);
        List<TestEntity> results= new ArrayList<>();
        for(TestEntity entity:testEntities){
            if(entity.getCreateUser().equals(userId)){
                String time  = entity.getCreateStr().split(" ")[0];
                time = time.replace("-","/");
                entity.setTime(time);
                results.add(entity);
            }
        }
        return new ResponseResultEntity(200,"成功",results,true);
    }

    @RequestMapping("getTestById")
    @RestJsonResponse(auth = Action.CHECKTOKEN)
    public Object getTestById(String testId){
        TestEntity testEntity = testService.getById(Long.parseLong(testId));
        return new ResponseResultEntity(200,"成功",testEntity,true);
    }


    @RequestMapping("deleteTestById")
    @RestJsonResponse(auth = Action.CHECKTOKEN)
    public Object deleteTestById(String testId){
        testService.deleteById(Long.parseLong(testId));
        return new ResponseResultEntity(200,"成功",null,true);
    }

    @RequestMapping("startTest")
    @RestJsonResponse(auth = Action.CHECKTOKEN)
    public Object startTest(HttpServletRequest request,String testId){
        logger.info("开始考试……");
        //生成二维码
        String code = StringUtils.getVcode();
        redisUtil.set(code+"-test",testId);
        String path = request.getSession().getServletContext().getRealPath("/");
        path+="QuizzQRCode/"+code+".jpg";
        try {
            QRCodeUtil.createQrCode(path,code,600,"JPEG");
        }catch (Exception e){
            e.printStackTrace();
        }
        Map map = new HashMap();
        map.put("qrcode","/QuizzQRCode/"+code+".jpg");
        map.put("pin",code);
        return new ResponseResultEntity(200,"成功", JSON.toJSON(map),true);
    }
}
