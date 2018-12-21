package cn.uenit.quizz.base.utils;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: SQJ
 * @data: 2018/6/29 10:16
 * @version:
 */
@Component
public class ResponseCode {

    private static final Map<Integer, String> responseCode = new HashMap<>();


    public ResponseCode() {
    }

    @PostConstruct
    public void init() {
        responseCode.put(200, "成功");
        responseCode.put(201, "未知原因错误");
        responseCode.put(1000, "未登录或登录失效，请重新登录");
        responseCode.put(1001, "token验证失败，请重新登录");
        responseCode.put(1002, "手机号已经注册，请登录");
        responseCode.put(1003, "获取验证码超过一小时限制，5次");
        responseCode.put(1004, "获取验证码超过一天限制，15次");
        responseCode.put(1005, "用户名或密码错误，请重新输入");
        responseCode.put(1006, "微信跳转登录，用户没有注册，请前往注册页面注册");
        responseCode.put(1007, "验证码输入错误，请检查验证码");
        responseCode.put(1008, "需要通过获取验证码登录");
        responseCode.put(1009, "验证码过期，请重新获取");
        responseCode.put(1010, "文件上传为空，请检查重新上传");
        responseCode.put(1011, "参数description不在可用范围内，请确认");
    }

    public String getResponseMsg(int code) {
        return responseCode.get(code);
    }
}
