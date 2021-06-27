package edu.lingnan.util;

import org.springframework.stereotype.Component;

import java.util.UUID;
/**
 * @author 18364
 */
@Component

/**
 * 产生UUID随机字符串工具类
 */
public final class UuidUtil {
    private UuidUtil(){}
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
