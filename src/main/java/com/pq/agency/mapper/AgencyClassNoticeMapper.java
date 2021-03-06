package com.pq.agency.mapper;

import com.pq.agency.entity.AgencyClassNotice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgencyClassNoticeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgencyClassNotice record);

    AgencyClassNotice selectByPrimaryKey(Long id);

    List<AgencyClassNotice> selectAll();

    int updateByPrimaryKey(AgencyClassNotice record);

    List<AgencyClassNotice> selectByClassIdAndIsReceipt(@Param("agencyClassId")Long agencyClassId,
                                                        @Param("isReceipt")int isReceipt,
                                                        @Param("offset")int offset,
                                                        @Param("size") int size);

    List<AgencyClassNotice> selectByUserIdAndClassId(@Param("userId")String userId,
                                                     @Param("classId")Long classId,
                                                     @Param("offset")int offset,
                                                     @Param("size") int size);

    List<AgencyClassNotice> selectByNoUserIdAndClassId(@Param("userId")String userId,
                                                       @Param("classId")Long classId,
                                                       @Param("offset")int offset,
                                                       @Param("size") int size);

    List<AgencyClassNotice> selectNotReceiptByClassId(@Param("agencyClassId")Long agencyClassId,
                                                      @Param("studentId")Long studentId,
                                                      @Param("offset")int offset,
                                                      @Param("size") int size);

    List<AgencyClassNotice> selectReceiptByClassId(@Param("agencyClassId")Long agencyClassId,
                                                   @Param("studentId")Long studentId,
                                                   @Param("offset")int offset,
                                                   @Param("size") int size);

    List<AgencyClassNotice> selectTeacherLastNotice(@Param("userId")String userId,
                                                    @Param("classIdList")List<Long> classIdList);

}