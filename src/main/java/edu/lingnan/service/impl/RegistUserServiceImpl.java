package edu.lingnan.service.impl;

import edu.lingnan.mapper.RegistUserMapper;
import edu.lingnan.pojo.User;
import edu.lingnan.service.RegistUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 18364
 */

@Service
public class RegistUserServiceImpl implements RegistUserService {

    @Autowired
    RegistUserMapper registUserMapper;

    @Override
    public int ifExist(String username) {
        return registUserMapper.ifExist(username);
    }

    @Override
    public User selectByUserName(String username) {
        return registUserMapper.selectByUserName(username);
    }

    @Override
    public int insertUser(User user) {
        return registUserMapper.insertUser(user);
    }

    @Override
    public int updateStatus(String code, String status) {
        return registUserMapper.updateStatus(code,status);
    }
}
