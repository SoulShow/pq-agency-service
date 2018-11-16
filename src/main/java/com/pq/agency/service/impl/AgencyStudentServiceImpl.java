package com.pq.agency.service.impl;

import com.pq.agency.entity.AgencyClass;
import com.pq.agency.entity.AgencyStudent;
import com.pq.agency.mapper.AgencyStudentMapper;
import com.pq.agency.service.AgencyClassService;
import com.pq.agency.service.AgencyStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liutao
 */
@Service
public class AgencyStudentServiceImpl implements AgencyStudentService {
    @Autowired
    private AgencyStudentMapper agencyStudentMapper;
    @Autowired
    private AgencyClassService agencyClassService;
    @Override
    public  Boolean checkStudent(Long agencyId, Long gradeId, Long classId, String studentName){
        AgencyClass agencyClass = agencyClassService.getAgencyClass(agencyId,gradeId,classId);

        List<AgencyStudent> list = agencyStudentMapper.selectByAgencyClassIdAndName(agencyClass.getId(),studentName);
        if(list==null||list.size()==0){
            return false;
        }else {
            for(AgencyStudent student:list){
                if(studentName.equals(student.getName())){
                    return true;
                }
            }
        }
        return false;
    }




}
