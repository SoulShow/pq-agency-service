package com.pq.agency.api;


import com.pq.agency.service.AgencyService;
import com.pq.agency.utils.AgencyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/agency")
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

	@GetMapping(value = "/list")
	@ResponseBody
	public AgencyResult getAgencyList(@RequestParam("name")String name) {
		AgencyResult result = new AgencyResult();
		result.setData(agencyService.getAgencyList(name));
		return result;
	}
	@GetMapping(value = "/grade/list")
	@ResponseBody
	public AgencyResult getGradeList(@RequestParam(value = "agencyId",required = false)Long agencyId) {
		AgencyResult result = new AgencyResult();
		result.setData(agencyService.getAgencyGradeList(agencyId));
		return result;
	}
	@GetMapping(value = "/class/list")
	@ResponseBody
	public AgencyResult getGradeList(@RequestParam(value = "agencyId")Long agencyId,
									 @RequestParam(value = "gradeId")Long gradeId) {
		AgencyResult result = new AgencyResult();
		result.setData(agencyService.getAgencyClassList(agencyId,gradeId));
		return result;
	}

}