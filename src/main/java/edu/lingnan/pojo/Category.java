package edu.lingnan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 分类实体类
 * @author 18364
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Category implements Serializable {

    private int cid;//分类id
    private String cname;//分类名称

}
