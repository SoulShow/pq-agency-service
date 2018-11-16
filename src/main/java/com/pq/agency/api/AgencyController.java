package com.pq.agency.api;


import com.pq.agency.entity.AgencyUserStudent;
import com.pq.agency.exception.AgencyErrors;
import com.pq.agency.param.AgencyUserRegisterCheckForm;
import com.pq.agency.service.AgencyClassService;
import com.pq.agency.service.AgencyService;
import com.pq.agency.service.AgencyStudentService;
import com.pq.agency.utils.AgencyResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/agency")
public class AgencyController {

    @Autowired
    private AgencyService agencyService;
    @Autowired
	private AgencyClassService agencyClassService;
	@Autowired
	private AgencyStudentService agencyStudentService;

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
		result.setData(agencyClassService.getAgencyGradeList(agencyId));
		return result;
	}
	@GetMapping(value = "/class/list")
	@ResponseBody
	public AgencyResult getGradeList(@RequestParam(value = "agencyId")Long agencyId,
									 @RequestParam(value = "gradeId")Long gradeId) {
		AgencyResult result = new AgencyResult();
		result.setData(agencyClassService.getAgencyClassList(agencyId,gradeId));
		return result;
	}

	@PostMapping(value = "/user/check")
	@ResponseBody
	public AgencyResult checkUserInfo(@RequestBody AgencyUserRegisterCheckForm registerCheckForm) {
		AgencyResult result = new AgencyResult();
		Boolean invitationCodeCheckStatus = agencyClassService.checkInvitationCode(registerCheckForm.getAgencyId(),registerCheckForm.getGradeId(),
				registerCheckForm.getClassId(),registerCheckForm.getInvitationCode());
		if(!invitationCodeCheckStatus){
			result.setStatus(AgencyErrors.INVITATION_CODE_ERROR.getErrorCode());
			result.setMessage(AgencyErrors.INVITATION_CODE_ERROR.getErrorMsg());
			return result;
		}
		Boolean userCheckStatus = agencyStudentService.checkStudent(registerCheckForm.getAgencyId(),registerCheckForm.getGradeId(),
				registerCheckForm.getClassId(),registerCheckForm.getStudentName());
		if(!userCheckStatus){
			result.setStatus(AgencyErrors.INVITATION_CODE_ERROR.getErrorCode());
			result.setMessage(AgencyErrors.INVITATION_CODE_ERROR.getErrorMsg());
			return result;
		}
		return result;
	}

}