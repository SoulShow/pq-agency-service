package com.pq.agency.api;

import com.pq.agency.exception.AgencyException;
import com.pq.agency.param.CollectionDeleteForm;
import com.pq.agency.param.NoticeFileCollectionForm;
import com.pq.agency.param.NoticeReceiptForm;
import com.pq.agency.param.VoteSelectedForm;
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

}