package cn.hy.service;

import cn.hy.domain.Book;
import cn.hy.domain.BorrowRecord;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    Book findById(Integer id);
    boolean addBook(Book book);
    boolean updateBook(Book book);
    boolean deleteBook(Integer id);
    boolean borrowBook(Integer userId, Integer bookId);      // 借书
    boolean returnBook(Integer recordId);                    // 还书
    List<BorrowRecord> getMyBorrows(Integer userId);         // 我的借阅
    List<BorrowRecord> getAllBorrows();

}

