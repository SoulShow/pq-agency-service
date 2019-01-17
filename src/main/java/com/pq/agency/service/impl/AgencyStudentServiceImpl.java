package com.pq.agency.service.impl;

import com.pq.agency.dto.AgencyStudentLifeDto;
import com.pq.agency.dto.AgencyStudentLifeListDto;
import com.pq.agency.entity.AgencyStudent;
import com.pq.agency.entity.AgencyStudentLife;
import com.pq.agency.entity.AgencyStudentLifeImg;
import com.pq.agency.exception.AgencyException;
import com.pq.agency.mapper.AgencyStudentLifeImgMapper;
import com.pq.agency.mapper.AgencyStudentLifeMapper;
import com.pq.agency.mapper.AgencyStudentMapper;
import com.pq.agency.param.StudentLifeForm;
import com.pq.agency.service.AgencyStudentService;
import com.pq.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liutao
 */
@Service
public class AgencyStudentServiceImpl implements AgencyStudentService {

    @Autowired
    private AgencyStudentMapper studentMapper;
    @Autowired
    private AgencyStudentLifeMapper studentLifeMapper;
    @Autowired
    private AgencyStudentLifeImgMapper studentLifeImgMapper;

    @Override
    public void updateStudentInfo(AgencyStudent agencyStudent){
        agencyStudent.setUpdatedTime(DateUtil.currentTime());
        studentMapper.updateByPrimaryKey(agencyStudent);
    }

    @Override
    public AgencyStudent getAgencyStudentById(Long id){
        return studentMapper.selectByPrimaryKey(id);
    }
    @Override
    public AgencyStudentLifeListDto getStudentLife(Long studentId, Long agencyClassId, int offset, int size){
        List<AgencyStudentLife> agencyStudentLifeList = studentLifeMapper.selectByStudentIdAndAgencyClassId(studentId,agencyClassId, offset, size);
        if(agencyStudentLifeList==null || agencyStudentLifeList.size()==0){
            return null;
        }
        List<AgencyStudentLifeDto> lifeList = new ArrayList<>();
        AgencyStudentLifeListDto lifeListDto = new AgencyStudentLifeListDto();
        for(AgencyStudentLife agencyStudentLife:agencyStudentLifeList){
            List<AgencyStudentLifeImg> imgList = studentLifeImgMapper.selectByLifeId(agencyStudentLife.getId());
            List<String> list = new ArrayList<>();
            for(AgencyStudentLifeImg img:imgList){
                list.add(img.getImg());
            }
            lifeListDto.setAgencyClassId(agencyStudentLife.getAgencyClassId());
            lifeListDto.setStudentId(agencyStudentLife.getStudentId());

            AgencyStudentLifeDto lifeDto = new AgencyStudentLifeDto();
            lifeDto.setImgList(list);
            lifeDto.setContent(agencyStudentLife.getContent());
            lifeDto.setTitle(agencyStudentLife.getTitle());

            lifeDto.setCreatedTime(DateUtil.formatDate(agencyStudentLife.getCreatedTime(),DateUtil.DEFAULT_DATE_FORMAT));
            lifeDto.setId(agencyStudentLife.getId());
            lifeList.add(lifeDto);
        }
        lifeListDto.setList(lifeList);
        return lifeListDto;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createStudentLife(StudentLifeForm studentLifeForm){
        AgencyStudentLife agencyStudentLife = new AgencyStudentLife();
        agencyStudentLife.setAgencyClassId(studentLifeForm.getAgencyClassId());
        agencyStudentLife.setStudentId(studentLifeForm.getStudentId());
        agencyStudentLife.setContent(studentLifeForm.getContent());
        agencyStudentLife.setTitle(studentLifeForm.getTitle());
        agencyStudentLife.setState(true);
        agencyStudentLife.setCreatedTime(DateUtil.currentTime());
        agencyStudentLife.setUpdatedTime(DateUtil.currentTime());
        studentLifeMapper.insert(agencyStudentLife);
        for(String img:studentLifeForm.getImgList()){
            AgencyStudentLifeImg studentLifeImg = new AgencyStudentLifeImg();
            studentLifeImg.setImg(img);
            studentLifeImg.setLifeId(agencyStudentLife.getId());
            studentLifeImg.setCreatedTime(DateUtil.currentTime());
            studentLifeImg.setUpdatedTime(DateUtil.currentTime());
            studentLifeImg.setState(true);
            studentLifeImgMapper.insert(studentLifeImg);
        }
    }
    @Override
    public int getStudentCount(Long classId){
        Integer count = studentMapper.selectCountByAgencyClassId(classId);
        if(count==null){
            count=0;
        }
        return count;
    }


}
