package edu.lingnan.controller;

import com.alibaba.fastjson.JSONObject;
import edu.lingnan.mapper.RouteMapper;
import edu.lingnan.pojo.Category;
import edu.lingnan.pojo.Route;
import edu.lingnan.service.CategoryService;
import edu.lingnan.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 18364
 */
@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @Autowired
    Category category;

    @RequestMapping(value = "allCategory", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String category() throws IOException {
        List<Category> all = categoryService.findAll();
        return JSONObject.toJSONString(all);
    }



}
