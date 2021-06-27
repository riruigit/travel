package edu.lingnan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 旅游线路图片实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RouteImg implements Serializable {
    private int rgid;//商品图片id
    private int rid;//旅游商品id
    private String bigPic;//详情商品大图
    private String smallPic;//详情商品小图


}
