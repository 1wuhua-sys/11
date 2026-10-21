package cn.hy.dao;

import cn.hy.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserMapper {
    //根据用户名查找用户
    User findByUsername(@Param("username") String username);
}

