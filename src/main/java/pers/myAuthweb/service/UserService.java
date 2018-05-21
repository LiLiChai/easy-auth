package pers.myAuthweb.service;

import pers.myAuthweb.common.PageResult;
import pers.myAuthweb.exception.BusinessException;
import pers.myAuthweb.exception.ParameterException;
import pers.myAuthweb.model.User;
/*
 * author : LH 2018-05-20 AM
 */
public interface UserService {
	
	User getUserByUserName(String userName);
	
	User getUserById(String userId);
	
	PageResult<User> getUsers(int pageNum, int pageSize, Integer status, String searchKey, String searchValue);
	
	boolean addUser(User user) throws BusinessException;
	
	boolean deleteUser(String userId) throws BusinessException;
	
	boolean updateUser(User user);
	
	boolean updateUserStatus(String userId, int status) throws ParameterException;
	
	boolean updateUserPassword(String userId, String newPsw);
}
