package cn.hy.service;

import cn.hy.dao.BookMapper;
import cn.hy.dao.BorrowRecordMapper;
import cn.hy.domain.Book;
import cn.hy.domain.BorrowRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BookServicelmpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> findAll() {
        return bookMapper.findAll();
    }

    @Override
    public Book findById(Integer id) {
        return bookMapper.findById(id);
    }

    @Override
    public boolean addBook(Book book) {
        return bookMapper.insert(book) > 0;
    }

    @Override
    public boolean updateBook(Book book) {
        return bookMapper.update(book) > 0;
    }

    @Override
    public boolean deleteBook(Integer id) {
        return bookMapper.delete(id) > 0;
    }
    @Autowired
    private BorrowRecordMapper borrowRecordMapper;

    // ========== 新增借阅功能 ==========
    @Override
    public boolean borrowBook(Integer userId, Integer bookId) {
        // 1. 检查图书是否存在
        Book book = bookMapper.findById(bookId);
        if (book == null) {
            return false;
        }
        // 2. 检查库存
        if (book.getStock() <= 0) {
            return false;
        }
        // 3. 检查是否已借阅未还
        List<BorrowRecord> records = borrowRecordMapper.findByUserId(userId);
        for (BorrowRecord record : records) {
            if (record.getBookId().equals(bookId) && "借出".equals(record.getStatus())) {
                return false;
            }
        }
        // 4. 计算应还日期（30天后）
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 30);
        // 5. 创建借阅记录
        BorrowRecord record = new BorrowRecord();
        record.setUserId(userId);
        record.setBookId(bookId);
        record.setDueDate(cal.getTime());
        record.setStatus("借出");
        // 6. 更新库存（减1）
        bookMapper.updateStock(bookId, -1);
        // 7. 插入借阅记录
        return borrowRecordMapper.insert(record) > 0;
    }

    @Override
    public boolean returnBook(Integer recordId) {
        // 1. 查询借阅记录
        BorrowRecord record = null;
        List<BorrowRecord> allRecords = borrowRecordMapper.findAll();
        for (BorrowRecord r : allRecords) {
            if (r.getId().equals(recordId)) {
                record = r;
                break;
            }
        }

        if (record == null || !"借出".equals(record.getStatus())) {
            return false;
        }
        // 2. 更新借阅记录
        record.setReturnDate(new Date());
        record.setStatus("已还");
        // 3. 更新库存（加1）
        bookMapper.updateStock(record.getBookId(), 1);
        return borrowRecordMapper.update(record) > 0;
    }

    @Override
    public List<BorrowRecord> getMyBorrows(Integer userId) {
        return borrowRecordMapper.findByUserId(userId);
    }

    @Override
    public List<BorrowRecord> getAllBorrows() {
        return borrowRecordMapper.findAll();
    }

}

