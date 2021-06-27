package edu.lingnan.controller;

import com.alibaba.fastjson.JSONObject;
import edu.lingnan.pojo.User;
import edu.lingnan.service.RegistUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 18364
 */
@Controller
public class Login {
    @Autowired
    RegistUserService registUserService;

    @Autowired
    User user;

    @RequestMapping(value = "login", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login(String username, String password, String captcha, HttpServletRequest request) {
        System.out.println("controller层获取到的参数id" + username + "密码" + password + "验证码" + captcha);
        String sessionCode = (String) request.getSession().getAttribute("captcha");
        user = registUserService.selectByUserName(username);
        if (registUserService.ifExist(username) == 0) {
            return "账号不存在";
        } else {
            if (!user.getPassword().equals(password)) {
                return "密码错误";
            } else {
                if (!sessionCode.equals(captcha)) {
                    return "验证码错误";
                } else {
                    if (!"Y".equals(user.getStatus())) {
                        return "账号还未激活";
                    } else {
                        request.getSession().setAttribute("userinfo", registUserService.selectByUserName(username));
                        return "登陆成功";
                    }
                }
            }
        }
    }

    @RequestMapping(value = "indexName", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String indexName(HttpServletRequest request ,HttpServletResponse response) throws IOException {
        User userinfo = (User) request.getSession().getAttribute("userinfo");
        return JSONObject.toJSONString(userinfo);
    }

    @RequestMapping(value = "exit", produces = "application/json;charset=utf-8")
    public String exit(HttpServletRequest request) {
        User userinfo = (User) request.getSession().getAttribute("userinfo");
        request.getSession().invalidate();
        return "redirect:/userlogin.html";
    }

    @RequestMapping(value = "userOne", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String userONe(HttpServletRequest request) {
        System.out.println("是否进去");
        User userinfo = (User) request.getSession().getAttribute("userinfo");
        if (!"".equals(userinfo) && userinfo != null) {
            return "true";
        } else {
            return "false";
        }
    }


}
