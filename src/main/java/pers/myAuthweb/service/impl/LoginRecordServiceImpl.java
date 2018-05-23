package pers.myAuthweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import pers.myAuthweb.common.PageResult;
import pers.myAuthweb.dao.LoginRecordMapper;
import pers.myAuthweb.model.LoginRecord;
import pers.myAuthweb.service.LoginRecordService;
/*
 * author : LH 2018-05-20 PM
 */
@Service
public class LoginRecordServiceImpl implements LoginRecordService {

	
	@Autowired
	LoginRecordMapper loginRecordMapper;
	
	@Override
	public boolean addLoginRecord(LoginRecord loginRecord) {
		return false;
	}

	@Override
	public PageResult<LoginRecord> getLoginRecords(int pageIndex, int pageSize, String startDate, String endDate, String searchAccount) {
		
		Page<Object> startPage = PageHelper.startPage(pageIndex, pageSize);
		List<LoginRecord> list = loginRecordMapper.selectLoginRecords(startDate,endDate,searchAccount);
		return new PageResult<LoginRecord>(startPage.getTotal(), list);
	}
	
}