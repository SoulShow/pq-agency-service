package com.pq.agency.mapper;

import com.pq.agency.entity.UserNoticeFileCollection;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserNoticeFileCollectionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserNoticeFileCollection record);

    UserNoticeFileCollection selectByPrimaryKey(Long id);

    List<UserNoticeFileCollection> selectAll();

    int updateByPrimaryKey(UserNoticeFileCollection record);

    List<UserNoticeFileCollection> selectByUserIdAndStudentId(@Param("userId")String userId,
                                                  @Param("studentId")Long studentId,
                                                  @Param(value = "offset")Integer offset,
                                                  @Param(value = "size")Integer size);

    UserNoticeFileCollection selectByUserIdAndStudentIdAndFileName(@Param("userId")String userId,
                                                                   @Param("studentId")Long studentId,
                                                                   @Param("fileName")String fileName);
}