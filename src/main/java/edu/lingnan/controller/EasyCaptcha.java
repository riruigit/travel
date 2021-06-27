package edu.lingnan.controller;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 18364
 */
@Controller
@RequestMapping("/easy")
public class EasyCaptcha {
    @RequestMapping(value = "/cap", produces = "application/json;charset=utf-8")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 48);
        captcha.setLen(3);
        captcha.getArithmeticString();
        request.getSession().setAttribute("captcha", captcha.text());
        String sessionCode = (String) request.getSession().getAttribute("captcha");
        CaptchaUtil.out(captcha, request, response);
    }
}