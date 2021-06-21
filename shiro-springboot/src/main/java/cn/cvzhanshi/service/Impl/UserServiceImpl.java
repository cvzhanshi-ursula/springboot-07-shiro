package cn.cvzhanshi.service.Impl;

import cn.cvzhanshi.entity.User;
import cn.cvzhanshi.mapper.UserMapper;
import cn.cvzhanshi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cVzhanshi
 * @create 2021-06-20 20:00
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryByUserName(String username) {
        return userMapper.queryByUserName(username);
    }
}
