package com.robotcms.sys.dao;

import java.io.Serializable;
import java.util.List;

import com.robotcms.common.base.BaseDao;
import com.robotcms.sys.domain.RoleMenuDO;

/**
 * <pre>
 * 角色与菜单对应关系
 * </pre>
 * |
 */
public interface RoleMenuDao extends BaseDao<RoleMenuDO> {
	
	List<Long> listMenuIdByRoleId(Serializable roleId);
	
	int removeByRoleId(Serializable roleId);
	
	int batchSave(List<RoleMenuDO> list);
}
