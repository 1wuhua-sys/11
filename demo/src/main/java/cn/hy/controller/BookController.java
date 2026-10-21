package cn.hy.controller;

import cn.hy.domain.Book;
import cn.hy.domain.User;
import cn.hy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    @ResponseBody
    public Map<String, Object> listBooks() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("data", bookService.findAll());
        return result;
    }

    @PostMapping("/book")
    @ResponseBody
    public Map<String, Object> addBook(@RequestBody Book book, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) session.getAttribute("loginUser");
        if (user == null || !"admin".equals(user.getRole())) {
            result.put("code", 403);
            result.put("message", "无权限");
            return result;
        }
        boolean success = bookService.addBook(book);
        result.put("code", success ? 200 : 500);
        result.put("message", success ? "添加成功" : "添加失败");
        return result;
    }

    @PutMapping("/book")
    @ResponseBody
    public Map<String, Object> updateBook(@RequestBody Book book, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) session.getAttribute("loginUser");
        if (user == null || !"admin".equals(user.getRole())) {
            result.put("code", 403);
            result.put("message", "未登录");
            return result;
        }
        boolean success = bookService.updateBook(book);
        result.put("code", success ? 200 : 500);
        result.put("message", success ? "更新成功" : "更新失败");
        return result;
    }

    @DeleteMapping("/book/{id}")
    @ResponseBody
    public Map<String, Object> deleteBook(@PathVariable Integer id, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) session.getAttribute("loginUser");
        if (user == null || !"admin".equals(user.getRole())) {
            result.put("code", 403);
            result.put("message", "未登录");
            return result;
        }
        boolean success = bookService.deleteBook(id);
        result.put("code", success ? 200 : 500);
        result.put("message", success ? "删除成功" : "删除失败");
        return result;
    }
}

