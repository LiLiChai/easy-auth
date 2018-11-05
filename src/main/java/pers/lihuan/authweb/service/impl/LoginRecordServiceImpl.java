package pers.lihuan.authweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import pers.lihuan.authweb.common.PageResult;
import pers.lihuan.authweb.dao.LoginRecordMapper;
import pers.lihuan.authweb.model.LoginRecord;
import pers.lihuan.authweb.service.LoginRecordService;
import pers.lihuan.authweb.utils.UUIDUtil;

/**
 * @author Fancy
 */
@Service
public class LoginRecordServiceImpl implements LoginRecordService {

	
	@Autowired
	LoginRecordMapper loginRecordMapper;
	
	@Override
	public boolean addLoginRecord(LoginRecord loginRecord) {
		loginRecord.setId(UUIDUtil.randomUUID8());
		loginRecord.setCreateTime(new Date());
		return loginRecordMapper.insert(loginRecord) > 0;
	}

	@Override
	public PageResult<LoginRecord> getLoginRecords(int pageIndex, int pageSize, String startDate, String endDate, String searchAccount) {
		
		Page<Object> startPage = PageHelper.startPage(pageIndex, pageSize);
		List<LoginRecord> list = loginRecordMapper.selectLoginRecords(startDate,endDate,searchAccount);
		return new PageResult<LoginRecord>(startPage.getTotal(), list);
	}
	
}