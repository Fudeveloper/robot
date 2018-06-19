package com.robotcms.sys.dao;

import java.io.Serializable;
import java.util.List;

import com.robotcms.common.base.BaseDao;
import com.robotcms.sys.domain.UserRoleDO;

/**
 * <pre>
 * 用户与角色对应关系
 * </pre>
 * |
 */
public interface UserRoleDao extends BaseDao<UserRoleDO> {

	List<Long> listRoleId(Serializable userId);

	int removeByUserId(Serializable userId);

	int batchSave(List<UserRoleDO> list);

	int batchRemoveByUserId(Long[] ids);
}
