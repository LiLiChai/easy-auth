package pers.myAuthweb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pers.myAuthweb.model.User;
/*
 * author : LH 2018-05-20 AM
 */
public interface UserMapper {

	User selectUserByUserName(String userName);

	List<User> selectUsers(@Param("status") Integer status, @Param("searchKey") String searchKey,
			@Param("searchValue") String searchValue);
	
	boolean insertUser(User user);
	
	int insertSelective(User user);
	
	int deleteByPrimaryKey(String userId);
	
	int updateByPrimaryKeySelective(User user);

}