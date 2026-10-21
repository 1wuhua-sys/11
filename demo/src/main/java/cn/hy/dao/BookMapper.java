package cn.hy.dao;

import cn.hy.domain.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface BookMapper {
    //查询所有图书
    List<Book> findAll();
    //根据图书ID查询图书
    Book findById(Integer id);
    //添加图书
    int insert(Book book);
    //更新图书
    int update(Book book);
    //删除图书
    int delete(Integer id);
    int updateStock(@Param("id") Integer id, @Param("delta") int delta);
}

