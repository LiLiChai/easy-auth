package pers.lihuan.authweb.service;

import pers.lihuan.authweb.common.PageResult;
import pers.lihuan.authweb.model.LoginRecord;
/*
 * author : LH 2018-05-20 PM
 */
public interface LoginRecordService {
	boolean addLoginRecord(LoginRecord loginRecord);
	PageResult<LoginRecord> getLoginRecords(int pageIndex, int pageSize, String startDate, String endDate, String searchAccount);

}
