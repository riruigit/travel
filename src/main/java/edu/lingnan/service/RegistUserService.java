package edu.lingnan.service;

import edu.lingnan.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * @author 18364
 */

public interface RegistUserService {
    int ifExist(String username);

    User selectByUserName(String username);

    int insertUser(User user);

    int updateStatus( String code,  String status);


}
