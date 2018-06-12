package pers.lihuan.authweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pers.lihuan.authweb.common.PageResult;
import pers.lihuan.authweb.model.LoginRecord;
import pers.lihuan.authweb.service.LoginRecordService;
import pers.lihuan.authweb.utils.StringUtil;

/*
 * author : LH 2018-05-21 PM
 */
@RestController
@RequestMapping("api/loginRecord")
public class LoginRecordController {
	
	@Autowired
	LoginRecordService recordService;
	
	@GetMapping()
	public PageResult<LoginRecord> getLoginRecords(Integer page, Integer limit,String startDate,String endDate,String account) {
		if(StringUtil.isBlank(startDate)){
			startDate = null;
		}else{
			startDate+=" 00:00:00";
		}
		if(StringUtil.isBlank(endDate)){
			endDate = null;
		}else{
			endDate+=" 23:59:59";
		}
		if(StringUtil.isBlank(account)){
			account = null;
		}
		return recordService.getLoginRecords(page, limit, startDate, endDate, account);
	}
}
