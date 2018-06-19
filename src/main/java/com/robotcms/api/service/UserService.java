package com.robotcms.api.service;

import com.robotcms.api.pojo.domain.AppUserDO;
import com.robotcms.api.pojo.vo.TokenVO;
import com.robotcms.common.base.CoreService;

/**
 * <pre>
 * </pre>
 *
 */
public interface UserService extends CoreService<AppUserDO> {
    
    TokenVO getToken(String uname, String passwd) ;
    AppUserDO findByUname(String uname);
}
