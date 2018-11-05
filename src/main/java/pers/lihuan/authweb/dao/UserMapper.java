package pers.lihuan.authweb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pers.lihuan.authweb.model.User;
/**
 * @author Fancy
 */
public interface UserMapper {

	User selectUserByUserName(String userName);
	
	User selectUserByUserId(String userId);

	List<User> selectUsers(@Param("status") Integer status, @Param("searchKey") String searchKey,
			@Param("searchValue") String searchValue);
	
	boolean insertUser(User user);
	
	int insertSelective(User user);
	
	int deleteByPrimaryKey(String userId);
	
	int updateByPrimaryKeySelective(User user);

}