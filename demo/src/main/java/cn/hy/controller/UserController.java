package cn.hy.controller;

import cn.hy.domain.User;
import cn.hy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/toLogin")
    @ResponseBody
    public Map<String, Object> login(@RequestParam String username,
                                     @RequestParam String password,
                                     HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        try {
            User user = userService.login(username, password);
            if (user != null) {
                session.setAttribute("loginUser", user);
                result.put("code", 200);
                result.put("role", user.getRole());
            } else {
                result.put("code", 401);
                result.put("message", "用户名或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();  // 打印完整错误
            result.put("code", 500);
            result.put("message", "服务器错误：" + e.getMessage());
        }

        return result;
    }

    @GetMapping("/logout")
    @ResponseBody
    public Map<String, Object> logout(HttpSession session) {
        session.invalidate();
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        return result;
    }

    @GetMapping("/checkLogin")
    @ResponseBody
    public Map<String, Object> checkLogin(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) session.getAttribute("loginUser");
        if (user != null) {
            result.put("code", 200);
            result.put("role", user.getRole());
            result.put("username", user.getUsername());
        } else {
            result.put("code", 401);
        }
        return result;
    }
}

