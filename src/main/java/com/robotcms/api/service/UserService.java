package com.robotcms.api.service;

import com.robotcms.api.pojo.domain.AppUserDO;
import com.robotcms.api.pojo.vo.TokenVO;
import com.robotcms.common.base.CoreService;

/**
 * <pre>
 * </pre>
 * <small> 2018年4月27日 | Aron</small>
 */
public interface UserService extends CoreService<AppUserDO> {
    
    TokenVO getToken(String uname, String passwd) ;
    AppUserDO findByUname(String uname);
}
