package cn.hy.controller;

import cn.hy.domain.BorrowRecord;
import cn.hy.domain.User;
import cn.hy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BorrowController {

    @Autowired
    private BookService bookService;

    // 借书
    @PostMapping("/borrow")
    @ResponseBody
    public Map<String, Object> borrow(@RequestParam Integer bookId, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) session.getAttribute("loginUser");

        if (user == null) {
            result.put("code", 401);
            result.put("message", "请先登录");
            return result;
        }

        boolean success = bookService.borrowBook(user.getId(), bookId);
        if (success) {
            result.put("code", 200);
            result.put("message", "借书成功，请在30天内归还");
        } else {
            result.put("code", 500);
            result.put("message", "借书失败，库存不足或您已借阅此书");
        }
        return result;
    }

    // 还书
    @PutMapping("/return/{recordId}")
    @ResponseBody
    public Map<String, Object> returnBook(@PathVariable Integer recordId, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) session.getAttribute("loginUser");

        if (user == null) {
            result.put("code", 401);
            result.put("message", "请先登录");
            return result;
        }

        boolean success = bookService.returnBook(recordId);
        if (success) {
            result.put("code", 200);
            result.put("message", "还书成功");
        } else {
            result.put("code", 500);
            result.put("message", "还书失败");
        }
        return result;
    }

    // 我的借阅记录
    @GetMapping("/myBorrows")
    @ResponseBody
    public Map<String, Object> myBorrows(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) session.getAttribute("loginUser");

        if (user == null) {
            result.put("code", 401);
            result.put("message", "请先登录");
            return result;
        }

        List<BorrowRecord> records = bookService.getMyBorrows(user.getId());
        result.put("code", 200);
        result.put("data", records);
        return result;
    }

    // 管理员：查看所有借阅记录
    @GetMapping("/allBorrows")
    @ResponseBody
    public Map<String, Object> allBorrows(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) session.getAttribute("loginUser");

        if (user == null || !"admin".equals(user.getRole())) {
            result.put("code", 403);
            result.put("message", "无权限");
            return result;
        }

        List<BorrowRecord> records = bookService.getAllBorrows();
        result.put("code", 200);
        result.put("data", records);
        return result;
    }
}

