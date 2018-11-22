package com.pq.agency.api;


import com.pq.agency.dto.AgencyStudentLifeDto;
import com.pq.agency.entity.AgencyStudent;
import com.pq.agency.exception.AgencyErrors;
import com.pq.agency.exception.AgencyException;
import com.pq.agency.param.StudentLifeForm;
import com.pq.agency.param.StudentModifyForm;
import com.pq.agency.service.AgencyStudentService;
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
@RequestMapping("/agency/student")
public class AgencyStudentController {

    @Autowired
	private AgencyStudentService agencyStudentService;

	@PostMapping(value = "/update/avatar")
	@ResponseBody
	public AgencyResult updateAvatar(@RequestBody StudentModifyForm studentModifyDto) {
		AgencyResult result = new AgencyResult();
		try{
            AgencyStudent student = agencyStudentService.getAgencyStudentById(studentModifyDto.getStudentId());
            student.setAvatar(studentModifyDto.getAvatar());
            agencyStudentService.updateStudentInfo(student);
        }catch (AgencyException a){
			result.setStatus(AgencyErrors.AGENCY_CLASS_USER_NOT_EXIST_ERROR.getErrorCode());
			result.setMessage(AgencyErrors.AGENCY_CLASS_USER_NOT_EXIST_ERROR.getErrorMsg());
		}catch (Exception e){
			result.setStatus(CommonErrors.DB_EXCEPTION.getErrorCode());
			result.setMessage(CommonErrors.DB_EXCEPTION.getErrorMsg());
		}
		return result;
	}

	@PostMapping(value = "/update/sex")
	@ResponseBody
	public AgencyResult updateSex(@RequestBody StudentModifyForm studentModifyDto) {
		AgencyResult result = new AgencyResult();
		try{
            AgencyStudent student = agencyStudentService.getAgencyStudentById(studentModifyDto.getStudentId());
            student.setSex(studentModifyDto.getSex());
            agencyStudentService.updateStudentInfo(student);
		}catch (AgencyException a){
			result.setStatus(AgencyErrors.AGENCY_CLASS_USER_NOT_EXIST_ERROR.getErrorCode());
			result.setMessage(AgencyErrors.AGENCY_CLASS_USER_NOT_EXIST_ERROR.getErrorMsg());
		}catch (Exception e){
			result.setStatus(CommonErrors.DB_EXCEPTION.getErrorCode());
			result.setMessage(CommonErrors.DB_EXCEPTION.getErrorMsg());
		}
		return result;
	}

	@GetMapping(value = "/life")
	@ResponseBody
	public AgencyResult<List<AgencyStudentLifeDto>> getStudentLife(@RequestParam("studentId")Long studentId,
									   @RequestParam("agencyClassId")Long agencyClassId,
									   @RequestParam("page")Integer page,
									   @RequestParam("size")Integer size) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (size == null || size < 1) {
			size = 10;
		}
		int offset = (page - 1) * size;

		AgencyResult result = new AgencyResult();
		try{
			List<AgencyStudentLifeDto> list = agencyStudentService.getStudentLifeList(studentId,agencyClassId,offset,size);
			result.setData(list);
		}catch (Exception e){
			result.setStatus(CommonErrors.DB_EXCEPTION.getErrorCode());
			result.setMessage(CommonErrors.DB_EXCEPTION.getErrorMsg());
		}
		return result;
	}
	@PostMapping(value = "/life")
	@ResponseBody
	public AgencyResult createLife(@RequestBody StudentLifeForm studentLifeForm) {
		AgencyResult result = new AgencyResult();
		try{
			agencyStudentService.createStudentLife(studentLifeForm);
		}catch (Exception e){
			result.setStatus(CommonErrors.DB_EXCEPTION.getErrorCode());
			result.setMessage(CommonErrors.DB_EXCEPTION.getErrorMsg());
		}
		return result;
	}
}