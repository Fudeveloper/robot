package com.robotcms.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.robotcms.common.domain.Tree;
import com.robotcms.sys.domain.DeptDO;

/**
 * <pre>
 * 部门管理
 * </pre>
 * <small> 2018年3月23日 | Aron</small>
 */
public interface DeptService extends IService<DeptDO> {
    
	Tree<DeptDO> getTree();
	
	boolean checkDeptHasUser(Long deptId);
}
