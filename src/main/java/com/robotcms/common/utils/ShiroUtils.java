package com.robotcms.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.robotcms.api.dao.AppUserDao;
import com.robotcms.api.pojo.domain.AppUserDO;
import com.robotcms.api.util.JWTUtil;
import com.robotcms.sys.domain.UserDO;

public class ShiroUtils {
	public static Subject getSubjct() {
		return SecurityUtils.getSubject();
	}
	
	// api
	private final static AppUserDao appUserDao = SpringContextHolder.getBean(AppUserDao.class);
	public static AppUserDO getAppUserDO() {
	    String jwt = (String)getSubjct().getPrincipal();
	    String userId = JWTUtil.getUserId(jwt);
	    return appUserDao.selectById(userId);
	}
	
	// admin
	public static UserDO getSysUser() {
		return (UserDO)getSubjct().getPrincipal();
	}
	public static Long getUserId() {
		return getSysUser().getId();
	}
	
	public static void logout() {
		getSubjct().logout();
	}
}
