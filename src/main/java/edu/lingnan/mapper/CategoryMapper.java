package edu.lingnan.mapper;

import edu.lingnan.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 18364
 */
public interface CategoryMapper {
    /**
     *  把8条数据拿出来
     */

    List<Category> findAll();
}
