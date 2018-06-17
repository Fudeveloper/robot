package com.robotcms.common.service;

import com.robotcms.common.base.CoreService;
import com.robotcms.common.domain.ConfigDO;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * </pre>
 * 
 * <small> 2018年4月6日 | Aron</small>
 */
public interface ConfigService extends CoreService<ConfigDO> {
    ConfigDO getByKey(String k);

    String getValuByKey(String k);
    
    void updateKV(Map<String, String> kv);
    
    List<ConfigDO> findListByKvType(int kvType);
}
