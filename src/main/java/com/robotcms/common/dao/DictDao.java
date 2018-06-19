package com.robotcms.common.dao;

import java.util.List;

import com.robotcms.common.base.BaseDao;
import com.robotcms.common.domain.DictDO;


public interface DictDao extends BaseDao<DictDO>{

    List<DictDO> listType();
    
}
