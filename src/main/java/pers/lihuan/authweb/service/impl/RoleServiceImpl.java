package pers.lihuan.authweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import pers.lihuan.authweb.common.PageResult;
import pers.lihuan.authweb.dao.RoleMapper;
import pers.lihuan.authweb.model.Role;
import pers.lihuan.authweb.model.RoleExample;
import pers.lihuan.authweb.service.RoleService;

/*
 * author : LH 2018-05-20 PM
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleMapper roleMapper;
	
	@Override
	public PageResult<Role> getRoles(Integer pageNum, Integer pageSize, String searchKey, String searchValue,
			Integer isDelete) {
		PageResult<Role> result = new PageResult<Role>();
		RoleExample example = new RoleExample();
		example.setOrderByClause("create_time asc");
		RoleExample.Criteria criteria = example.createCriteria();
		if(isDelete!=null){
			criteria.andIsDeleteEqualTo(isDelete);
		}
		if(searchKey!=null){
			if(searchKey.equals("role_id")){
				criteria.andRoleIdLike("%"+searchValue+"%");
			}else if(searchKey.equals("role_name")){
				criteria.andRoleNameLike("%"+searchValue+"%");
			}else if(searchKey.equals("comments")){
				criteria.andCommentsLike("%"+searchValue+"%");
			}
		}
		Page<Object> startPage = PageHelper.startPage(pageNum, pageSize);
		result.setData(roleMapper.selectByExample(example));
		result.setCount(startPage.getTotal());
		return result;
	}

	@Override
	public boolean addRole(Role role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteRole(String roleId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateRole(Role role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateStatus(String roleId, int status) {
		// TODO Auto-generated method stub
		return false;
	}

}
