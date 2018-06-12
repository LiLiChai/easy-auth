package test;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import pers.lihuan.authweb.dao.RoleMapper;
import pers.lihuan.authweb.model.Role;

public class Test {
	
	@Autowired
	RoleMapper roleMapper;
	
	@org.junit.Test
	public void test() {
		Role role = new Role();
		role.setComments("tes");
		role.setCreateTime(new Date());
		role.setIsDelete(0);
		role.setRoleId("role");
		role.setUpdateTime(new Date());
		roleMapper.insertRole(role);
	}
}
