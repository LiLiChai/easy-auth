package pers.lihuan.authweb.dao;


import java.util.List;
import org.apache.ibatis.annotations.Param;

import pers.lihuan.authweb.model.RolePermission;
import pers.lihuan.authweb.model.RolePermissionExample;

/**
 * @author Fancy
 */
public interface RolePermissionMapper {
    
	int countByExample(RolePermissionExample example);

    int deleteByExample(RolePermissionExample example);

    int deleteByPrimaryKey(String id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    List<RolePermission> selectByExample(RolePermissionExample example);

    RolePermission selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

    int updateByExample(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);
}