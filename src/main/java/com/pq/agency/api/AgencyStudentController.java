package com.pq.agency.api;


import com.pq.agency.dto.StudentModifyDto;
import com.pq.agency.entity.AgencyStudent;
import com.pq.agency.exception.AgencyErrors;
import com.pq.agency.exception.AgencyException;
import com.pq.agency.service.AgencyStudentService;
import com.pq.agency.utils.AgencyResult;
import com.pq.common.exception.CommonErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	public AgencyResult updateAvatar(@RequestBody StudentModifyDto studentModifyDto) {
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
	public AgencyResult updateSex(@RequestBody StudentModifyDto studentModifyDto) {
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
}