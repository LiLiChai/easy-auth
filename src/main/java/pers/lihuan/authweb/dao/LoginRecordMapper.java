package pers.lihuan.authweb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pers.lihuan.authweb.model.LoginRecord;

/**
 * @author Fancy
 */
public interface LoginRecordMapper {
	
	List<LoginRecord> selectLoginRecords(@Param("startDate") String startDate, @Param("endDate") String endDate,
										 @Param("searchAccount") String searchAccount);
	int insert(LoginRecord loginRecord);
}
