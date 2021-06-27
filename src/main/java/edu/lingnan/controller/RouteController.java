package edu.lingnan.controller;

import com.alibaba.fastjson.JSONObject;
import edu.lingnan.pojo.PageBean;
import edu.lingnan.pojo.Route;
import edu.lingnan.pojo.User;
import edu.lingnan.service.FavoriteService;
import edu.lingnan.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


/**
 * @author 18364
 */

@Controller
public class RouteController {

    @Autowired
    RouteService routeService;

    @Autowired
    FavoriteService favoriteService;

    @RequestMapping(value = "pageQuery", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String pageQuery(String cid, String rname, String pageSize, String currentPage) {
        System.out.println("打印获取的参数cid" + cid + " 打印获取的参数rname" + rname + " 打印获取的参数page" + pageSize + " 打印获取的参数cu" + currentPage);
        int newcid = 0;
        int newcurrentPage = 1;
        int newpageSize = 5;
        if (cid != null && cid.length() > 0 && !"null".equals(cid)) {
            newcid = Integer.parseInt(cid);
        }
        System.out.println("获取cid" + cid);
        if (currentPage != null && currentPage.length() > 0 ) {
            newcurrentPage = Integer.parseInt(currentPage);
        }
        System.out.println(newcurrentPage);
        if (pageSize != null && pageSize.length() > 0) {
            newpageSize = Integer.parseInt(pageSize);
        }
        System.out.println(newpageSize);

        if("null".equals(rname))
        {
            rname = null;
        }

        PageBean<Route> routePageBean = routeService.pageQuery(newcid, newcurrentPage, newpageSize, rname);

        System.out.println(routePageBean.toString());

        return JSONObject.toJSONString(routePageBean);

    }


    @RequestMapping(value = "findOne", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findOne(String rid) {
        Route one = routeService.findOne(rid);
        return JSONObject.toJSONString(one);
    }


    @RequestMapping(value = "isFavorite", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String isFavorite(HttpServletRequest request, String rid) {
        System.out.println("isFavorite"+"获取的rid"+rid);
        User userinfo = (User) request.getSession().getAttribute("userinfo");

        int uid = 0;
        if (!"".equals(userinfo) && userinfo != null) {

            uid = userinfo.getUid();
        } else {
            uid = 0;
        }
        System.out.println("uid"+uid);
        boolean favorite = favoriteService.isFavorite(rid, uid);
        System.out.println("isFavorite"+favorite);
        return favorite ? "true" : "false";
    }


    @RequestMapping(value = "addFavorite", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addFavorite(HttpServletRequest request, String rid) {
        User userinfo = (User) request.getSession().getAttribute("userinfo");
        int uid = 0;
        if (!"".equals(userinfo) && userinfo != null) {

            uid = userinfo.getUid();
        } else {
            uid = 0;
        }
        System.out.println("uid"+uid);
        favoriteService.add(rid, uid);
        return "true";
    }


    @RequestMapping(value = "deleteFavorite", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteFavorite(HttpServletRequest request, String rid) {
        User userinfo = (User) request.getSession().getAttribute("userinfo");
        int uid = 0;
        if (!"".equals(userinfo) && userinfo != null) {

            uid = userinfo.getUid();
        } else {
            uid = 0;
        }
        System.out.println("uid"+uid);
        favoriteService.deleteFavorite(rid, uid);
        return "true";
    }



}
