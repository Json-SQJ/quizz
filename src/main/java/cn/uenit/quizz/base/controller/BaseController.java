package cn.uenit.quizz.base.controller;


import cn.uenit.quizz.base.utils.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: SQJ
 * @data: 2018/6/29 10:20
 * @version:
 */
public class BaseController {

    @Autowired
    private ResponseCode responseCode;

    public Map<String, Object> getResult(int code, Object o) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("data", o);
        result.put("success", code == 200 ? true : false);
        result.put("msg", code == 200 ? "成功" : responseCode.getResponseMsg(code));
        return result;
    }
}
