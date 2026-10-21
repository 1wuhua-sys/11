package cn.hy.dao;

import cn.hy.domain.BorrowRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface BorrowRecordMapper {

    // 根据用户ID查询借阅记录
    List<BorrowRecord> findByUserId(@Param("userId") Integer userId);

    // 查询所有借阅记录（管理员）
    List<BorrowRecord> findAll();

    // 添加借阅记录
    int insert(BorrowRecord record);

    // 更新借阅记录（还书）
    int update(BorrowRecord record);
}

