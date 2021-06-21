package cn.cvzhanshi.mapper;

import cn.cvzhanshi.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author cVzhanshi
 * @create 2021-06-20 19:54
 */
@Repository
@Mapper
public interface UserMapper {
    public User queryByUserName(String username);
}
