package pers.lihuan.authweb.service;

import pers.lihuan.authweb.common.PageResult;
import pers.lihuan.authweb.exception.BusinessException;
import pers.lihuan.authweb.exception.ParameterException;
import pers.lihuan.authweb.model.User;

/**
 * @author Fancy
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
