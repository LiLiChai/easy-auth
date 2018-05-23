package pers.myAuthweb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pers.myAuthweb.model.LoginRecord;

/*
 * author : LH 2018-05-22 NT
 */
public interface LoginRecordMapper {
	
	public List<LoginRecord> selectLoginRecords(@Param("startDate") String startDate, @Param("endDate") String endDate,
			@Param("searchAccount") String searchAccount);
}
