package com.robotcms.sys.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.IService;
import com.robotcms.sys.domain.RoleDO;

/**
 * <pre>
 * </pre>
 * |
 */
@Service
public interface RoleService extends IService<RoleDO> {
    List<RoleDO> findAll();
    List<RoleDO> findListByUserId(Serializable id);
}
