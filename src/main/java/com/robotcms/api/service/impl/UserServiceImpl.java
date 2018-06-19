package com.robotcms.api.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.robotcms.api.dao.AppUserDao;
import com.robotcms.api.exception.IFastApiException;
import com.robotcms.api.pojo.domain.AppUserDO;
import com.robotcms.api.pojo.vo.TokenVO;
import com.robotcms.api.service.UserService;
import com.robotcms.api.util.JWTUtil;
import com.robotcms.common.base.CoreServiceImpl;
import com.robotcms.common.type.EnumErrorCode;

/**
 * <pre>
 * </pre>
 * 
 *
 */
@Service
public class UserServiceImpl extends CoreServiceImpl<AppUserDao, AppUserDO> implements UserService {

    @Override
    public TokenVO getToken(String uname, String passwd) {

        AppUserDO user = findByUname(uname);
        if (null == user) {
            throw new IFastApiException(EnumErrorCode.apiUserLoginError.getCodeStr());
        }

        if (!user.getPasswd().equals(passwd)) {
            throw new IFastApiException(EnumErrorCode.apiUserLoginError.getCodeStr());
        }

        TokenVO vo = new TokenVO();
        vo.setToken(JWTUtil.sign(user.getId() + "", uname + passwd));
        vo.setRefleshToken("refleshToken");
        return vo;
    }

    @Override
    public AppUserDO findByUname(String uname) {
        AppUserDO userDO = new AppUserDO();
        userDO.setUname(uname);
        Wrapper<AppUserDO> wrapper = new EntityWrapper<AppUserDO>(userDO);
        AppUserDO bean = selectOne(wrapper);
        return bean;
    }

}
