package cn.uenit.quizz.controller.wx;

import cn.uenit.quizz.base.config.RestJsonResponse;
import cn.uenit.quizz.base.entity.ResponseResultEntity;
import cn.uenit.quizz.base.utils.HttpClientUtils;
import cn.uenit.quizz.base.utils.RedisUtil;
import cn.uenit.quizz.entity.QuestionEntity;
import cn.uenit.quizz.entity.StudentAnswerEntity;
import cn.uenit.quizz.entity.TestEntity;
import cn.uenit.quizz.service.IQuestionService;
import cn.uenit.quizz.service.IStudentAnswerService;
import cn.uenit.quizz.service.ITestService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Source;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/wx/common")
@RestController
public class CommonController {

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ITestService testService;

    @Autowired
    private IStudentAnswerService studentAnswerService;

    @RequestMapping("/getwxopenid")
    @RestJsonResponse
    public Object getWxOpenId(@RequestParam("code") String code) {
        Map<String, String> request = new HashMap<>();
        request.put("appid", "wx077462963f8b1d43");
        request.put("secret", "b2e9cafffe684d14f4f037006b91d030");
        request.put("js_code", code);
        request.put("grant_type", "authorization_code");
        String response = HttpClientUtils.doGet("https://api.weixin.qq.com/sns/jscode2session", request);
        return new ResponseResultEntity(200, "成功", response, true);
    }


    @RequestMapping("/getQuestionsByTestId")
    @RestJsonResponse
    public Object getQuestionsByTestId(String pin) {
        String testId = redisUtil.get(pin + "-test").toString();
        List<QuestionEntity> questionEntities = questionService.getQuestionsByTestId(Long.parseLong(testId));
        return new ResponseResultEntity(200, "成功", questionEntities, true);
    }

    @RequestMapping("/checkUnique")
    @RestJsonResponse
    public Object checkUnique(String openid,String testid){
        List<StudentAnswerEntity> studentAnswerEntities = studentAnswerService.getListByTestIdAndOpenid(testid,openid);
        if(studentAnswerEntities.size()!=0){
            return new ResponseResultEntity(1000,"失败",null,false);
        }
        return new ResponseResultEntity(null);

    }


    @RequestMapping("/getTestByPin")
    @RestJsonResponse
    public Object getTestByPin(String pin) {
        String testId = redisUtil.get(pin + "-test").toString();
        TestEntity testEntity = testService.getByTestId(Long.parseLong(testId));
        return new ResponseResultEntity(200, "成功", testEntity, true);
    }

    @RequestMapping(value = "/saveScore", method = RequestMethod.POST)
    @RestJsonResponse
    public Object saveScore(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        try {
            BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);
             jsonObject = JSON.parseObject(responseStrBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Integer> answerArr = (List<Integer>) jsonObject.get("answerArr");
        String openId = jsonObject.get("openid").toString();
        String testId = jsonObject.get("testId").toString();
        List<QuestionEntity> questionEntities = questionService.getQuestionsByTestId(Long.parseLong(testId));
        for (int i =0;i<questionEntities.size();i++){
            StudentAnswerEntity answerEntity = new StudentAnswerEntity();
            answerEntity.setOpenId(openId);
            answerEntity.setTestId(Long.parseLong(testId));
            answerEntity.setIsCorrect(answerArr.get(i));
            answerEntity.setQuestionId(questionEntities.get(i).getId());
            studentAnswerService.saveEntity(answerEntity);
        }
        return new ResponseResultEntity(200, "成功", null, true);
    }


    @RequestMapping(value = "/getStudentAnswer")
    @RestJsonResponse
    public Object getStudentAnswer(String id,String openid){
      List<StudentAnswerEntity> studentAnswerEntities =   studentAnswerService.getListByTestIdAndOpenid(id,openid);
      TestEntity testEntity = testService.getById(Long.parseLong(id));
      List<QuestionEntity> questionEntities = questionService.getQuestionsByTestId(Long.parseLong(id));
      Map<String,Object> result = new HashMap<>();
      result.put("answer",studentAnswerEntities);
      result.put("questions",questionEntities);
      result.put("test",testEntity);
      return new ResponseResultEntity(result);
    }

    @RequestMapping(value = "/getTests")
    @RestJsonResponse
    public Object getTests(String openid){
        List<TestEntity> testEntities = studentAnswerService.getTestsByOpenId(openid);
        return new ResponseResultEntity(testEntities);
    }
}
