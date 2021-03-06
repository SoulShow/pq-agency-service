package com.pq.agency.api;

import com.pq.agency.dto.AgencyNoticeDto;
import com.pq.agency.exception.AgencyException;
import com.pq.agency.param.*;
import com.pq.agency.service.AgencyClassService;
import com.pq.agency.service.AgencyService;
import com.pq.agency.utils.AgencyResult;
import com.pq.common.exception.CommonErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agency")
public class AgencyController {

    @Autowired
	private AgencyClassService agencyClassService;

	@GetMapping(value = "/list")
	@ResponseBody
	public AgencyResult getAgencyList(@RequestParam(value = "name",required = false)String name) {
		AgencyResult result = new AgencyResult();
		result.setData(agencyClassService.getAgencyList(name));
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
									 @RequestParam(value = "userId")String userId,
									 @RequestParam(value = "page",required = false)Integer page,
									 @RequestParam(value = "size",required = false)Integer size) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (size == null || size < 1) {
			size = 20;
		}
		int offset = (page - 1) * size;

		AgencyResult result = new AgencyResult();
		try {
			result.setData(agencyClassService.getAgencyClassShowList(agencyClassId,userId,offset,size));
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
			size = 20;
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
                                       @RequestParam(value = "userId")String userId,
									   @RequestParam(value = "studentId",required = false)Long studentId,
                                       @RequestParam(value = "isReceipt")int isReceipt,
                                       @RequestParam(value = "page",required = false)Integer page,
                                       @RequestParam(value = "size",required = false)Integer size) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (size == null || size < 1) {
			size = 20;
		}
		int offset = (page - 1) * size;

		AgencyResult result = new AgencyResult();
		try {
			result.setData(agencyClassService.getClassNoticeList(agencyClassId,userId,studentId,isReceipt,offset,size));
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
	public AgencyResult getClassNoticeDetail(@RequestParam(value = "noticeId")Long noticeId,
                                             @RequestParam(value = "userId")String userId,
											 @RequestParam(value = "studentId",required = false)Long studentId) {

		AgencyResult result = new AgencyResult();
		try {
			result.setData(agencyClassService.getClassNoticeDetail(noticeId,userId,studentId));
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

	@PostMapping(value = "/class/notice/receipt")
	@ResponseBody
	public AgencyResult createReceipt(@RequestBody NoticeReceiptForm noticeReceiptForm) {

		AgencyResult result = new AgencyResult();
		try {
			agencyClassService.receiptNotice(noticeReceiptForm);
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

    @GetMapping(value = "/class/schedule")
    @ResponseBody
    public AgencyResult getClassSchedule(@RequestParam("agencyClassId")Long agencyClassId) {

        AgencyResult result = new AgencyResult();
        try {
            result.setData(agencyClassService.getScheduleList(agencyClassId));
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

    @GetMapping(value = "/class/task")
    @ResponseBody
    public AgencyResult getClassTask(@RequestParam(value = "agencyClassId")Long agencyClassId,
									 @RequestParam(value = "userId")String userId,
									 @RequestParam(value = "studentId")Long studentId,
									 @RequestParam(value = "page",required = false)Integer page,
									 @RequestParam(value = "size",required = false)Integer size) {
        if (page == null || page < 1) {
            page = 1;
        }
        if (size == null || size < 1) {
            size = 20;
        }
        int offset = (page - 1) * size;

        AgencyResult result = new AgencyResult();
        try {
            result.setData(agencyClassService.getTaskList(agencyClassId,userId,studentId,offset,size));
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
    @GetMapping(value = "/class/task/detail")
    @ResponseBody
    public AgencyResult getClassTaskDetail(@RequestParam(value = "taskId")Long taskId,
										   @RequestParam(value = "studentId",required = false)Long studentId,
										   @RequestParam(value = "userId")String userId) {

        AgencyResult result = new AgencyResult();
        try {
            result.setData(agencyClassService.getTaskDetail(taskId,studentId,userId));
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
	@GetMapping(value = "/class/vote/list")
	@ResponseBody
	public AgencyResult getClassVoteList(@RequestParam("agencyClassIdList")List<Long> agencyClassIdList,
									 @RequestParam(value = "userId")String userId,
									 @RequestParam(value = "studentId",required = false) Long studentId,
									 @RequestParam(value = "page",required = false)Integer page,
									 @RequestParam(value = "size",required = false)Integer size) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (size == null || size < 1) {
			size = 20;
		}
		int offset = (page - 1) * size;

		AgencyResult result = new AgencyResult();
		try {
			result.setData(agencyClassService.getVoteList(agencyClassIdList,userId,studentId,offset,size));
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

	@GetMapping(value = "/class/vote/detail")
	@ResponseBody
	public AgencyResult getClassVoteDetail(@RequestParam(value = "voteId")Long voteId,
										 @RequestParam(value = "userId")String userId,
										 @RequestParam(value = "studentId",required = false) Long studentId) {

		AgencyResult result = new AgencyResult();
		try {
			result.setData(agencyClassService.getVoteDetail(voteId,userId,studentId));
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

	@PostMapping(value = "/class/vote/selected")
	@ResponseBody
	public AgencyResult classVoteSelect(@RequestBody VoteSelectedForm voteSelectedForm) {

		AgencyResult result = new AgencyResult();
		try {
			agencyClassService.voteSelected(voteSelectedForm);
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

	@GetMapping(value = "/class/vote/statistics")
	@ResponseBody
	public AgencyResult getClassVoteStatistics(@RequestParam(value = "voteId")Long voteId) {

		AgencyResult result = new AgencyResult();
		try {
			result.setData(agencyClassService.getVoteStatistics(voteId));
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

	@GetMapping(value = "/group")
	@ResponseBody
	public AgencyResult getAgencyClassInfo(@RequestParam(value = "studentId",required = false)Long studentId,
										   @RequestParam(value = "userId")String userId) {
		AgencyResult result = new AgencyResult();
		try {
			result.setData(agencyClassService.getClassInfo(userId,studentId));
		}catch (AgencyException e){
			result.setStatus(e.getErrorCode().getErrorCode());
			result.setMessage(e.getErrorCode().getErrorMsg());
		}catch (Exception e) {
			e.printStackTrace();
			result.setStatus(CommonErrors.DB_EXCEPTION.getErrorCode());
			result.setMessage(CommonErrors.DB_EXCEPTION.getErrorMsg());
		}
		return result;
	}

	@GetMapping(value = "/group/user")
	@ResponseBody
	public AgencyResult getAgencyClassUserInfo(@RequestParam(value = "groupId")Long groupId,
											   @RequestParam(value = "studentId",required = false)Long studentId,
											   @RequestParam(value = "userId")String userId,
											   @RequestParam(value = "isCreate")int isCreate) {
		AgencyResult result = new AgencyResult();
		try {
			result.setData(agencyClassService.getClassUserInfo(groupId,studentId,userId,isCreate));
		}catch (AgencyException e){
			result.setStatus(e.getErrorCode().getErrorCode());
			result.setMessage(e.getErrorCode().getErrorMsg());
		}catch (Exception e) {
			e.printStackTrace();
			result.setStatus(CommonErrors.DB_EXCEPTION.getErrorCode());
			result.setMessage(CommonErrors.DB_EXCEPTION.getErrorMsg());
		}
		return result;
	}

	@PostMapping(value = "/group/disturb")
	@ResponseBody
	public AgencyResult groupDisturb(@RequestBody DisturbForm disturbForm) {
		AgencyResult result = new AgencyResult();
		try {
			agencyClassService.groupDisturb(disturbForm.getGroupId(),disturbForm.getUserId(),
					disturbForm.getStudentId(),disturbForm.getStatus());
		}catch (AgencyException e){
			result.setStatus(e.getErrorCode().getErrorCode());
			result.setMessage(e.getErrorCode().getErrorMsg());
		}catch (Exception e) {
			e.printStackTrace();
			result.setStatus(CommonErrors.DB_EXCEPTION.getErrorCode());
			result.setMessage(CommonErrors.DB_EXCEPTION.getErrorMsg());
		}

		return result;
	}

	@GetMapping(value = "/class/user/chatStatus")
	@ResponseBody
	public AgencyResult getGroupChatStatus(@RequestParam(value = "groupId")Long groupId,
										   @RequestParam(value = "userId")String userId) {
		AgencyResult result = new AgencyResult();
		try{
			result.setData(agencyClassService.getClassChatStatus(groupId,userId));
		}catch (AgencyException e){
			result.setStatus(e.getErrorCode().getErrorCode());
			result.setMessage(e.getErrorCode().getErrorMsg());
		}catch (Exception e) {
			e.printStackTrace();
			result.setStatus(CommonErrors.DB_EXCEPTION.getErrorCode());
			result.setMessage(CommonErrors.DB_EXCEPTION.getErrorMsg());
		}
		return result;
	}


	@GetMapping(value = "/group/search/user")
	@ResponseBody
	public AgencyResult getGroupSearchUserInfo(@RequestParam(value = "groupId")Long groupId,
											   @RequestParam(value = "name",required = false)String name) {
		AgencyResult result = new AgencyResult();
		try{
			result.setData(agencyClassService.getGroupSearchUserInfo(groupId,name));
		}catch (AgencyException e){
			result.setStatus(e.getErrorCode().getErrorCode());
			result.setMessage(e.getErrorCode().getErrorMsg());
		}catch (Exception e) {
			e.printStackTrace();
			result.setStatus(CommonErrors.DB_EXCEPTION.getErrorCode());
			result.setMessage(CommonErrors.DB_EXCEPTION.getErrorMsg());
		}
		return result;
	}
	@GetMapping(value = "/group/forward/info")
	@ResponseBody
	public AgencyResult getGroupForwardSearchUserInfo(@RequestParam(value = "userId")String userId,
											   @RequestParam(value = "studentId",required = false)Long studentId,
											   @RequestParam(value = "name",required = false)String name,
											   @RequestParam(value = "role")int role) {
		AgencyResult result = new AgencyResult();
		try{
			result.setData(agencyClassService.getClassAndTeacherInfo(userId, studentId, role,name));
		}catch (AgencyException e){
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
	public AgencyResult getAgencyClassInfo(@RequestParam(value = "agencyClassId")Long agencyClassId) {
		AgencyResult result = new AgencyResult();
		result.setData(agencyClassService.getClassInfoByClassId(agencyClassId));
		return result;
	}

	@GetMapping(value = "/class/last/notice")
	@ResponseBody
	public AgencyResult<AgencyNoticeDto> getLastNotice(@RequestParam(value = "agencyClassId",required = false)Long agencyClassId,
													   @RequestParam(value = "userId")String userId,
													   @RequestParam(value = "studentId",required = false)Long studentId,
													   @RequestParam(value = "role")int role) {
		AgencyResult result = new AgencyResult();
		result.setData(agencyClassService.getLastNotice(agencyClassId,userId,studentId,role));
		return result;
	}

	@GetMapping(value = "/classIds")
	@ResponseBody
	public AgencyResult getClassIds(@RequestParam(value = "classId")Long classId) {
		AgencyResult result = new AgencyResult();
		result.setData(agencyClassService.getAgencyClassIds(classId));
		return result;
	}
}