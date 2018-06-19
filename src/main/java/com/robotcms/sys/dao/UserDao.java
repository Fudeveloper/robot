package com.robotcms.sys.dao;

import com.robotcms.common.base.BaseDao;
import com.robotcms.sys.domain.UserDO;

/**
 * <pre>
 * </pre>
 * |
 */
public interface UserDao extends BaseDao<UserDO> {
	
	Long[] listAllDept();

}
