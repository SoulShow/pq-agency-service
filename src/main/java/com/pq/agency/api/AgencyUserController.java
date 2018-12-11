package com.pq.agency.api;


import com.pq.agency.entity.AgencyStudent;
import com.pq.agency.exception.AgencyErrors;
import com.pq.agency.exception.AgencyException;
import com.pq.agency.param.AgencyUserRegisterCheckForm;
import com.pq.agency.param.AgencyUserRegisterForm;
import com.pq.agency.service.AgencyClassService;
import com.pq.agency.utils.AgencyResult;
import com.pq.common.exception.CommonErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 机构用户相关服务
 * @author liutao
 */
@RestController
@RequestMapping("/agency/user")
public class AgencyUserController {

    @Autowired
	private AgencyClassService agencyClassService;

	@PostMapping(value = "/student/check")
	@ResponseBody
	public AgencyResult checkUserInfo(@RequestBody AgencyUserRegisterCheckForm registerCheckForm) {
		AgencyResult result = new AgencyResult();
		try{
			agencyClassService.checkInvitationCodeAndStudent(registerCheckForm.getPhone(),registerCheckForm.getInvitationCode(),
					registerCheckForm.getStudentName());
		}catch (AgencyException a){
			result.setStatus(a.getErrorCode().getErrorCode());
			result.setMessage(a.getErrorCode().getErrorMsg());
		}catch (Exception e){
			e.printStackTrace();
			result.setStatus(CommonErrors.DB_EXCEPTION.getErrorCode());
			result.setMessage(CommonErrors.DB_EXCEPTION.getErrorMsg());
		}
		return result;
	}

	@GetMapping(value = "/student/relation")
	@ResponseBody
	public AgencyResult getRelation(@RequestParam("invitationCode") String invitationCode,
									@RequestParam("studentName")String studentName) {
		AgencyResult result = new AgencyResult();
		try{
			result.setData(agencyClassService.getUserStudentRelation(invitationCode,studentName));
		}catch (AgencyException a){
			result.setStatus(a.getErrorCode().getErrorCode());
			result.setMessage(a.getErrorCode().getErrorMsg());
		}catch (Exception e){
			e.printStackTrace();
			result.setStatus(CommonErrors.DB_EXCEPTION.getErrorCode());
			result.setMessage(CommonErrors.DB_EXCEPTION.getErrorMsg());
		}
		return result;
	}

	@GetMapping(value = "/student/check")
	@ResponseBody
	public AgencyResult getStudent(@RequestParam("agencyId")Long agencyId,@RequestParam("gradeId")Long gradeId,
								   @RequestParam("classId")Long classId,@RequestParam("studentName")String studentName) {
		AgencyResult result = new AgencyResult();
		try{
			List<AgencyStudent> list = agencyClassService.getStudentInfo(agencyId,gradeId, classId,studentName);
			if(list == null || list.size()<=0){
				result.setStatus(AgencyErrors.AGENCY_USER_STUDENT_NOT_EXIST_ERROR.getErrorCode());
				result.setMessage(AgencyErrors.AGENCY_USER_STUDENT_NOT_EXIST_ERROR.getErrorMsg());
			}
		}catch (AgencyException a){
			result.setStatus(a.getErrorCode().getErrorCode());
			result.setMessage(a.getErrorCode().getErrorMsg());
		}catch (Exception e){
			e.printStackTrace();
			result.setStatus(CommonErrors.DB_EXCEPTION.getErrorCode());
			result.setMessage(CommonErrors.DB_EXCEPTION.getErrorMsg());
		}
		return result;
	}
	@PostMapping(value = "/create")
	@ResponseBody
	public AgencyResult createUser(@RequestBody AgencyUserRegisterForm userRegisterForm) {
		AgencyResult result = new AgencyResult();
		try{
			agencyClassService.createUser(userRegisterForm);
		}catch (AgencyException a){
			result.setStatus(a.getErrorCode().getErrorCode());
			result.setMessage(a.getErrorCode().getErrorMsg());
		}catch (Exception e){
			e.printStackTrace();
			result.setStatus(CommonErrors.DB_EXCEPTION.getErrorCode());
			result.setMessage(CommonErrors.DB_EXCEPTION.getErrorMsg());
		}
		return result;
	}

	@GetMapping(value = "/student")
	@ResponseBody
	public AgencyResult getUserInfo(@RequestParam("userId")String userId) {
		AgencyResult result = new AgencyResult();
		try{
			result.setData(agencyClassService.getAgencyUserInfo(userId));
		}catch (AgencyException a){
			result.setStatus(a.getErrorCode().getErrorCode());
			result.setMessage(a.getErrorCode().getErrorMsg());
		}catch (Exception e){
			e.printStackTrace();
			result.setStatus(CommonErrors.DB_EXCEPTION.getErrorCode());
			result.setMessage(CommonErrors.DB_EXCEPTION.getErrorMsg());
		}
		return result;
	}
	@GetMapping(value = "/classId")
	@ResponseBody
	public AgencyResult<List<Long>> getUserClassId(@RequestParam("userId")String userId) {
		AgencyResult result = new AgencyResult();
		try{
			result.setData(agencyClassService.getUserClassId(userId));
		}catch (Exception e){
			e.printStackTrace();
			result.setStatus(CommonErrors.DB_EXCEPTION.getErrorCode());
			result.setMessage(CommonErrors.DB_EXCEPTION.getErrorMsg());
		}
		return result;
	}

	@GetMapping(value = "/disturb/group")
	@ResponseBody
	public AgencyResult getDisturbGroup(@RequestParam(value = "studentId",required = false)Long studentId,
									 @RequestParam(value = "userId")String userId) {
		AgencyResult result = new AgencyResult();
		result.setData(agencyClassService.getDisturbGroup(userId,studentId));
		return result;
	}
}