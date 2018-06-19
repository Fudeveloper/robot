package com.robotcms.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.robotcms.common.domain.Tree;
import com.robotcms.sys.domain.DeptDO;

/**
 * <pre>
 * 部门管理
 * </pre>
 * |
 */
public interface DeptService extends IService<DeptDO> {
    
	Tree<DeptDO> getTree();
	
	boolean checkDeptHasUser(Long deptId);
}
