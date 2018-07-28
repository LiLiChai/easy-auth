package pers.lihuan.authweb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pers.lihuan.authweb.model.LoginRecord;

/*
 * author : LH 2018-05-22 NT
 */
public interface LoginRecordMapper {
	
	public List<LoginRecord> selectLoginRecords(@Param("startDate") String startDate, @Param("endDate") String endDate,
			@Param("searchAccount") String searchAccount);
	int insert(LoginRecord loginRecord);
	public List<LoginRecord> selectLoginRecords2(
			@Param("pageIndex") int pageIndex,@Param("pageSize") 
			int pageSize,@Param("startDate") String startDate, @Param("endDate") String endDate,
			@Param("searchAccount") String searchAccount);
	public Integer selectLoginRecords3();
}
