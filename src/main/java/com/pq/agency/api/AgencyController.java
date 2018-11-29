package com.pq.agency.api;

import com.pq.agency.exception.AgencyException;
import com.pq.agency.service.AgencyClassService;
import com.pq.agency.service.AgencyService;
import com.pq.agency.utils.AgencyResult;
import com.pq.common.exception.CommonErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agency")
public class AgencyController {

    @Autowired
    private AgencyService agencyService;
    @Autowired
	private AgencyClassService agencyClassService;

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

	@GetMapping(value = "/class/show")
	@ResponseBody
	public AgencyResult getClassShow(@RequestParam(value = "agencyClassId")Long agencyClassId,
									 @RequestParam(value = "page",required = false)Integer page,
									 @RequestParam(value = "size",required = false)Integer size) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (size == null || size < 1) {
			size = 10;
		}
		int offset = (page - 1) * size;

		AgencyResult result = new AgencyResult();
		try {
			result.setData(agencyClassService.getAgencyClassShowList(agencyClassId,offset,size));
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

	@GetMapping(value = "/show")
	@ResponseBody
	public AgencyResult getClassShow(@RequestParam(value = "agencyClassId")Long agencyClassId,
									 @RequestParam(value = "isBanner")int isBanner,
									 @RequestParam(value = "page",required = false)Integer page,
									 @RequestParam(value = "size",required = false)Integer size) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (size == null || size < 1) {
			size = 10;
		}
		int offset = (page - 1) * size;

		AgencyResult result = new AgencyResult();
		try {
			result.setData(agencyClassService.getAgencyShowList(agencyClassId,isBanner,offset,size));
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

	@GetMapping(value = "/class/notice")
	@ResponseBody
	public AgencyResult getClassNotice(@RequestParam(value = "agencyClassId")Long agencyClassId,
									 @RequestParam(value = "isReceipt")int isReceipt,
									 @RequestParam(value = "page",required = false)Integer page,
									 @RequestParam(value = "size",required = false)Integer size) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (size == null || size < 1) {
			size = 10;
		}
		int offset = (page - 1) * size;

		AgencyResult result = new AgencyResult();
		try {
			result.setData(agencyClassService.getClassNoticeList(agencyClassId,isReceipt,offset,size));
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
	@GetMapping(value = "/class/notice/detail")
	@ResponseBody
	public AgencyResult getClassNoticeDetail(@RequestParam(value = "noticeId")Long noticeId) {

		AgencyResult result = new AgencyResult();
		try {
			result.setData(agencyClassService.getClassNoticeDetail(noticeId));
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


}