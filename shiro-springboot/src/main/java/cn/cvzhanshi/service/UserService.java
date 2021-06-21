package cn.cvzhanshi.service;

import cn.cvzhanshi.entity.User;

/**
 * @author cVzhanshi
 * @create 2021-06-20 19:59
 */
public interface UserService {
    public User queryByUserName(String username);
}
