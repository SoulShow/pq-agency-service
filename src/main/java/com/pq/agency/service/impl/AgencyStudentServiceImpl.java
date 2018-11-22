package com.pq.agency.service.impl;

import com.pq.agency.entity.AgencyStudent;
import com.pq.agency.mapper.AgencyStudentMapper;
import com.pq.agency.service.AgencyStudentService;
import com.pq.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liutao
 */
@Service
public class AgencyStudentServiceImpl implements AgencyStudentService {

    @Autowired
    private AgencyStudentMapper studentMapper;

    @Override
    public void updateStudentInfo(AgencyStudent agencyStudent){
        agencyStudent.setUpdatedTime(DateUtil.currentTime());
        studentMapper.updateByPrimaryKey(agencyStudent);
    }

    @Override
    public AgencyStudent getAgencyStudentById(Long id){
        return studentMapper.selectByPrimaryKey(id);
    }

}
