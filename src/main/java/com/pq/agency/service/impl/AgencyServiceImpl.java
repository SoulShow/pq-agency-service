package com.pq.agency.service.impl;

import com.pq.agency.entity.Agency;
import com.pq.agency.mapper.AgencyMapper;
import com.pq.agency.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liutao
 */
@Service
public class AgencyServiceImpl implements AgencyService {
    @Autowired
    private AgencyMapper agencyMapper;
    @Override
    public List<Agency> getAgencyList(String name){
        return agencyMapper.selectByName(name);
    }

}
