package pers.myAuthweb.service;

import pers.myAuthweb.common.PageResult;
import pers.myAuthweb.model.LoginRecord;
/*
 * author : LH 2018-05-20 PM
 */
public interface LoginRecordService {
	boolean addLoginRecord(LoginRecord loginRecord);
	PageResult<LoginRecord> getLoginRecords(int pageIndex, int pageSize, String startDate, String endDate, String searchAccount);
}
