package com.pq.agency.api;

import com.pq.agency.exception.AgencyException;
import com.pq.agency.param.*;
import com.pq.agency.service.AgencyClassService;
import com.pq.agency.utils.AgencyResult;
import com.pq.common.exception.CommonErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agency/teacher")
public class AgencyTeacherController {

    @Autowired
	private AgencyClassService agencyClassService;

    @GetMapping(value = "/class/task")
    @ResponseBody
    public AgencyResult getClassTask(@RequestParam(value = "userId")String userId,
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
            result.setData(agencyClassService.getTaskList(userId,offset,size));
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
    @GetMapping(value = "/class/task/read")
    @ResponseBody
    public AgencyResult getClassTaskRead(@RequestParam(value = "taskId")Long taskId) {

        AgencyResult result = new AgencyResult();
        try {
            result.setData(agencyClassService.getTaskReadInfo(taskId));
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

    @PostMapping(value = "/class/task")
    @ResponseBody
    public AgencyResult getClassTaskCreate(@RequestBody TaskCreateForm createForm) {

        AgencyResult result = new AgencyResult();
        try {
            agencyClassService.createTask(createForm);
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

    @GetMapping(value = "/class/list")
    @ResponseBody
    public AgencyResult getClass(@RequestParam(value = "userId")String userId) {

        AgencyResult result = new AgencyResult();
        try {
            result.setData(agencyClassService.getTeacherClassList(userId));
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
    @PostMapping(value = "/class/vote")
    @ResponseBody
    public AgencyResult createVote(@RequestBody AgencyClassVoteForm voteForm) {
        AgencyResult result = new AgencyResult();
        try {
            agencyClassService.createVote(voteForm);
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
    @PostMapping(value = "/class/vote/delete")
    @ResponseBody
    public AgencyResult deleteVote(@RequestBody VoteDelForm voteDelForm) {
        AgencyResult result = new AgencyResult();
        try {
            agencyClassService.deleteVote(voteDelForm.getVoteId());
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
    @PostMapping(value = "/group/keepSilent")
    @ResponseBody
    public AgencyResult groupKeepSilent(@RequestBody ChatStatusForm chatStatusForm) {
        AgencyResult result = new AgencyResult();
        try{
            agencyClassService.groupKeepSilent(chatStatusForm.getGroupId(),chatStatusForm.getUserId(),
                    chatStatusForm.getStudentId(),chatStatusForm.getStatus());
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

    @PostMapping(value = "/create")
    @ResponseBody
    public AgencyResult createUser(@RequestBody AgencyTeacherRegisterForm teacherRegisterForm) {
        AgencyResult result = new AgencyResult();
        try{
            agencyClassService.createTeacher(teacherRegisterForm);
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

    @PostMapping(value = "/header/check")
    @ResponseBody
    public AgencyResult checkTeacherHeader(@RequestBody AgencyTeacherRegisterForm teacherRegisterForm) {
        AgencyResult result = new AgencyResult();
        try{
            agencyClassService.checkTeacherHeader(teacherRegisterForm.getClassList());
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

    @PostMapping(value = "/class/schedule")
    @ResponseBody
    public AgencyResult updateSchedule(@RequestBody ScheduleUpdateForm scheduleUpdateForm) {

        AgencyResult result = new AgencyResult();
        try {
            agencyClassService.updateSchedule(scheduleUpdateForm);
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