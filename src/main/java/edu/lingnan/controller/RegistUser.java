package edu.lingnan.controller;

import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import edu.lingnan.pojo.User;
import edu.lingnan.service.RegistUserService;
import edu.lingnan.util.Mail;
import edu.lingnan.util.Phone;
import edu.lingnan.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * @author 18364
 */
@Controller


public class RegistUser {

    @Autowired
    RegistUserService registUserService;

    @Autowired
    Mail mail;

    @RequestMapping(value = "RegistUser", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String registUser(User user, String rpcode,HttpServletRequest request) throws MessagingException {
        //打印获取到的参数
        System.out.println(user.toString());
        System.out.println("验证码" + rpcode);
        //通过名字参数去查询数据库有没有这条记录

        String stringBufferResult = (String) request.getSession().getAttribute("registCode");
        System.out.println("sessiomcode"+stringBufferResult);

        if (rpcode.equals(stringBufferResult)){
            if (registUserService.ifExist(user.getUsername()) == 0) {
                //返回值为0，说明没有这个记录，则可以进行插入操作
                user.setCode(UuidUtil.getUuid());
                user.setStatus("N");
                System.out.println("插入了" + registUserService.insertUser(user) + "条数据");
                //发送激活邮件
                mail.mail(user.getEmail(), "<a href='http://101.34.6.69:8080/activeUser?code=" + user.getCode() + "'>点击激活【黑马旅游网】</a>");
                return "用户注册成功";
            } else {
                System.out.println("这条数据已经存在数据库中了");
            }
            return "用户已经存在";
        }
        else {
            return "验证码错误";
        }

    }

    @RequestMapping(value = "activeUser", produces = "application/json;charset=utf-8")
    public String activeUser(String code) {
        System.out.println("获取到的唯一的code是" + code);
        /**邮件激活功能，先注册一个账号，但是状态是N，需要激活
         * 注册好了跳转ok界面，然后收到邮件的提示，点击后调用这个方法，然后修改状态。
         * 一种场景：注册好了没有验证邮件，
         * 一种场景，注册好了，然后直接打开邮件。
         * 无论如何，两者都需要有一个邮箱，然后在激活的时候需要一个用户名
         */
        int y = registUserService.updateStatus(code, "Y");
        return "redirect:/index.html";
    }


    @RequestMapping(value = "registCode", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String registCode(String  telephone, HttpServletRequest request) throws Exception {
        com.aliyun.dysmsapi20170525.Client client = Phone.createClient("xxxx", "xxxxx");//阿里云用户里面获取
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            stringBuffer.append(random.nextInt(10));
        }
        System.out.println("suji"+stringBuffer);
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(telephone)
                .setSignName("美柚明治")
                .setTemplateCode("SMS_218xxx6")
                .setTemplateParam("{\"code\":" + stringBuffer + "}");
        // 复制代码运行请自行打印 API 的返回值
        client.sendSms(sendSmsRequest);

        request.getSession().setAttribute("registCode",stringBuffer.toString());
        String stringBufferresult = (String) request.getSession().getAttribute("registCode");
        System.out.println("sessiomcode"+stringBufferresult);
        return stringBuffer.toString();


    }


}
