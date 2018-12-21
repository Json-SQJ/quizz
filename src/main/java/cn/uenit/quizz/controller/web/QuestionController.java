package cn.uenit.quizz.controller.web;

import cn.uenit.quizz.base.config.RestJsonResponse;
import cn.uenit.quizz.base.entity.ResponseResultEntity;
import cn.uenit.quizz.base.utils.Action;
import cn.uenit.quizz.entity.AnswerEntity;
import cn.uenit.quizz.entity.QuestionEntity;
import cn.uenit.quizz.service.IAnswerService;
import cn.uenit.quizz.service.IQuestionService;
import cn.uenit.quizz.service.IStudentAnswerService;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/web/question")
public class QuestionController {

    private static final Logger logger = Logger.getLogger(QuestionController.class);

    @Autowired
    private IQuestionService questionService;
    @Autowired
    private IAnswerService answerService;


    @RequestMapping("/addQuestion")
    @RestJsonResponse(auth = Action.CHECKTOKEN)
    public Object addQuestion(@RequestBody Map<String,Object> maps){
        System.err.println(JSON.toJSON(maps).toString());
        Set<AnswerEntity> answerEntities = new HashSet<>();
        List<Map<String,String>> questionAnswer =(List<Map<String, String>>) maps.get("questionAnswer");
        Map<String,String> questionCorrect = (Map<String, String>) maps.get("questionCorrect");
        for(Map map:questionAnswer){
            AnswerEntity answerEntity = new AnswerEntity();
            answerEntity.setSelecion(map.get("select").toString());
            answerEntity.setSelectionContent(map.get("content").toString());
            if(questionCorrect.get("select").equalsIgnoreCase(map.get("select").toString())){
                answerEntity.setIsCorrect(1);
            }
            answerEntities.add(answerEntity);
        }
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setAnalysis(maps.get("analysis").toString());
        questionEntity.setQuestionName(maps.get("questionName").toString());
        questionEntity.setAnswerSet(answerEntities);
        questionEntity.setTestId(maps.get("testId").toString());
        questionService.saveEntity(questionEntity);
        return new ResponseResultEntity(200,"成功",null,true);
    }

    @RequestMapping("/deleteQuestion")
    @RestJsonResponse(auth = Action.CHECKTOKEN)
    public Object deleteQuestion(String id){
        QuestionEntity questionEntity = questionService.getById(Long.parseLong(id));
        Set<AnswerEntity> answerEntities = questionEntity.getAnswerSet();
        for(AnswerEntity answerEntity:answerEntities){
            answerService.deleteById(answerEntity.getId());
        }
        questionService.deleteEntityById(Long.parseLong(id));
        return new ResponseResultEntity(200,"成功",null,true);
    }


    @RequestMapping("/getQuestionsByTestId")
    @RestJsonResponse(auth = Action.CHECKTOKEN)
    public Object getQuestionsByTestId(String testId){
        List<QuestionEntity> questionEntities = questionService.getQuestionsByTestId(Long.parseLong(testId));
        return new ResponseResultEntity(200,"成功",questionEntities,true);
    }

    @RequestMapping("/getQuestionById")
    @RestJsonResponse(auth = Action.CHECKTOKEN)
    public Object getQuestionById(String id){
        QuestionEntity questionEntity = questionService.getById(Long.parseLong(id));
        return questionEntity;
    }

}
