package cn.hy.service;

import cn.hy.domain.User;

public interface UserService {
    User login(String username, String password);
}

