package com.robotcms.sys.dao;

import com.robotcms.common.base.BaseDao;
import com.robotcms.sys.domain.UserDO;

/**
 * <pre>
 * </pre>
 * <small> 2018年3月23日 | Aron</small>
 */
public interface UserDao extends BaseDao<UserDO> {
	
	Long[] listAllDept();

}
