package com.pq.agency.service;


import com.pq.agency.entity.Agency;

import java.util.List;

/**
 * 机构服务
 * @author liutao
 */
public interface AgencyService {

    /**
     * 获取机构列表
     *
     * @param name
     * @return
     * @throws Exception
     */
    List<Agency> getAgencyList(String name);




}
