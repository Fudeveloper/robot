package com.robotcms.sys.dao;

import com.robotcms.common.base.BaseDao;
import com.robotcms.sys.domain.DeptDO;

/**
 * <pre>
 * 部门管理
 * </pre>
 * <small> 2018年3月23日 | Aron</small>
 */
public interface DeptDao extends BaseDao<DeptDO> {
	
	Long[] listParentDept();
	
	int getDeptUserNumber(Long deptId);
}
