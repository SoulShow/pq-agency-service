package com.pq.agency.api;


import com.pq.agency.dto.AgencyClassDto;
import com.pq.agency.dto.UserDto;
import com.pq.agency.entity.AgencyStudent;
import com.pq.agency.exception.AgencyErrors;
import com.pq.agency.exception.AgencyException;
import com.pq.agency.param.AgencyUserRegisterCheckForm;
import com.pq.agency.param.AgencyUserRegisterForm;
import com.pq.agency.param.CollectionDeleteForm;
import com.pq.agency.param.NoticeFileCollectionForm;
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

	@GetMapping(value = "/collection/list")
	@ResponseBody
	public AgencyResult getCollectList(@RequestParam("userId")String userId,
									   @RequestParam(value = "studentId",required = false)Long studentId,
									   @RequestParam(value = "page",required = false)Integer page,
									   @RequestParam(value = "size",required = false)Integer size) {

		AgencyResult result = new AgencyResult();
		try {
			if (page == null || page < 1) {
				page = 1;
			}
			if (size == null || size < 1) {
				size = 20;
			}
			int offset = (page - 1) * size;
			result.setData(agencyClassService.getCollectList(userId, studentId, offset, size));
		} catch (AgencyException e){
			result.setStatus(e.getErrorCode().getErrorCode());
			result.setMessage(e.getErrorCode().getErrorMsg());
		}catch (Exception e) {
			e.printStackTrace();
			result.setStatus(CommonErrors.DB_EXCEPTION.getErrorCode());
			result.setMessage(CommonErrors.DB_EXCEPTION.getErrorMsg());
		}
		return result;
	}
	@PostMapping(value = "/collection")
	@ResponseBody
	public AgencyResult collection(@RequestBody NoticeFileCollectionForm collectionForm) {

		AgencyResult result = new AgencyResult();
		try {
			agencyClassService.noticeFileCollection(collectionForm);
		} catch (AgencyException e){
			result.setStatus(e.getErrorCode().getErrorCode());
			result.setMessage(e.getErrorCode().getErrorMsg());
		}catch (Exception e) {
			e.printStackTrace();
			result.setStatus(CommonErrors.DB_EXCEPTION.getErrorCode());
			result.setMessage(CommonErrors.DB_EXCEPTION.getErrorMsg());
		}
		return result;
	}
	@PostMapping(value = "/collection/delete")
	@ResponseBody
	public AgencyResult deleteCollection(@RequestBody CollectionDeleteForm deleteForm) {

		AgencyResult result = new AgencyResult();
		try {
			agencyClassService.deleteCollection(deleteForm.getCollectionId(),deleteForm.getUserId(),deleteForm.getStudentId());
		} catch (AgencyException e){
			result.setStatus(e.getErrorCode().getErrorCode());
			result.setMessage(e.getErrorCode().getErrorMsg());
		}catch (Exception e) {
			e.printStackTrace();
			result.setStatus(CommonErrors.DB_EXCEPTION.getErrorCode());
			result.setMessage(CommonErrors.DB_EXCEPTION.getErrorMsg());
		}
		return result;
	}

	@GetMapping(value = "/class/info")
	@ResponseBody
	public AgencyResult<List<AgencyClassDto>> getUserClassInfo(@RequestParam("userId")String userId) {
		AgencyResult result = new AgencyResult();
		try{
			result.setData(agencyClassService.getUserClassInfo(userId));
		}catch (Exception e){
			e.printStackTrace();
			result.setStatus(CommonErrors.DB_EXCEPTION.getErrorCode());
			result.setMessage(CommonErrors.DB_EXCEPTION.getErrorMsg());
		}
		return result;
	}

	@GetMapping(value = "/class/users")
	@ResponseBody
	public AgencyResult<List<UserDto>> getUserClassUsers(@RequestParam("classId")Long classId) {
		AgencyResult result = new AgencyResult();
		try{
			result.setData(agencyClassService.getClassUsers(classId));
		}catch (Exception e){
			e.printStackTrace();
			result.setStatus(CommonErrors.DB_EXCEPTION.getErrorCode());
			result.setMessage(CommonErrors.DB_EXCEPTION.getErrorMsg());
		}
		return result;
	}

}