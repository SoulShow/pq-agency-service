package com.pq.agency.api;


import com.pq.agency.service.HystrixService;
import com.pq.agency.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private HystrixService hystrixService;

	@RequestMapping(value = "/test",method = RequestMethod.GET)
	@ResponseBody
	public Result getUsers() {
		Result result = new Result();
		result.setData(hystrixService.getOrderPageList());
		return result;
	}


	@RequestMapping(value = "/test1",method = RequestMethod.GET)
	public String test() {
		return "test1";
	}

}