package edu.lingnan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 收藏实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Favorite implements Serializable {
    private Route route;//旅游线路对象
    private String date;//收藏时间
    private User user;//所属用户


}
