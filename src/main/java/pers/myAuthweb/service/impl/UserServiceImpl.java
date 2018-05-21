package pers.myAuthweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import pers.myAuthweb.common.PageResult;
import pers.myAuthweb.common.encrypt.utils.EndecryptUtils;
import pers.myAuthweb.dao.UserMapper;
import pers.myAuthweb.exception.BusinessException;
import pers.myAuthweb.exception.ParameterException;
import pers.myAuthweb.model.User;
import pers.myAuthweb.service.UserService;
import pers.myAuthweb.utils.UUIDUtil;

/*
 * author : LH 2018-05-20 PM
 */
@Service(value="userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getUserByUserName(String userName) {
		User user = userMapper.selectUserByUserName(userName);
		System.out.println(user);
		return userMapper.selectUserByUserName(userName);
	}

	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult<User> getUsers(int pageNum, int pageSize, Integer status, String searchKey, String searchValue) {
		Page<Object> startPage = PageHelper.startPage(pageNum, pageSize);
		List<User> users = userMapper.selectUsers(status, searchKey, searchValue);
		PageResult<User> result = new PageResult<User>();
		result.setData(users);
		result.setCount(startPage.getTotal());
		return result;
	}

	@Override
	public boolean addUser(User user) throws BusinessException {
		if(getUserByUserName(user.getUserAccount())!=null){
			throw new BusinessException("账号已经存在");
		}
		user.setUserId(UUIDUtil.randomUUID8());
		String decryptMd5 = EndecryptUtils.encrytMd5(user.getUserPassword(), user.getUserId(), 3);
		user.setUserPassword(decryptMd5);
		user.setUserStatus(0);
		user.setCreateTime(new Date());
		return userMapper.insertSelective(user)>0;
	}

	@Override
	public boolean deleteUser(String userId) throws BusinessException{
		try{
			return userMapper.deleteByPrimaryKey(userId)>0;
		}catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("用户已被关联");
		}
	}

	@Override
	public boolean updateUser(User user) {
		return userMapper.updateByPrimaryKeySelective(user)>0;
	}

	@Override
	public boolean updateUserStatus(String userId, int status) throws ParameterException {
		if(status !=0 && status !=1){
			throw new ParameterException();
		}
		User user = new User();
		user.setUserId(userId);
		user.setUserStatus(status);
		return userMapper.updateByPrimaryKeySelective(user)>0;
	}

	@Override
	public boolean updateUserPassword(String userId, String password) {
		User user = new User();
		user.setUserId(userId);
		String decryptMd5 = EndecryptUtils.encrytMd5(password, userId, 3);
		user.setUserPassword(decryptMd5);
		return userMapper.updateByPrimaryKeySelective(user)>0;
	}
	
	
}
