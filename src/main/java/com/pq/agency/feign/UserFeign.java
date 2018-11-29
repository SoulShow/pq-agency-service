package com.pq.agency.feign;


import com.pq.agency.dto.UserDto;
import com.pq.agency.utils.AgencyResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 登录服务
 * @author liutao
 */
@FeignClient("service-user")
public interface UserFeign {
    /**
     * 获取用户信息
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    AgencyResult<UserDto> getUserInfo(@RequestParam("userId")String userId);
}
