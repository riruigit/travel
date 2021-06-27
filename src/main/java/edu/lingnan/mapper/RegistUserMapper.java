package edu.lingnan.mapper;

import edu.lingnan.pojo.User;
import org.apache.ibatis.annotations.Param;


/**
 * @author 18364
 */

public interface RegistUserMapper {
    /**
     * 注册的时候使用的，先判断数据库中有没有这一条数据记录。
     *
     * @param username
     * @return
     */
    int ifExist(String username);


    int updateStatus(@Param("code") String code, @Param("status") String status);


    User selectByUserName(String username);


    /**
     * 插入一条数据，注册用户
     *
     * @param user
     * @return
     */
    int insertUser(User user);
}
