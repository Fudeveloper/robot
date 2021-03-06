package com.robotcms.common.base;

import com.baomidou.mybatisplus.plugins.Page;
import com.robotcms.api.dao.AppUserDao;
import com.robotcms.api.pojo.domain.AppUserDO;
import com.robotcms.api.util.JWTUtil;
import com.robotcms.common.utils.HttpContextUtils;
import com.robotcms.common.utils.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <pre>
 * </pre>
 * 
 * |
 */
public abstract class ApiBaseController {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    private final static AppUserDao appUserDao = SpringContextHolder.getBean(AppUserDao.class);

    public static AppUserDO getUser() {
        String jwt = (String) getSubjct().getPrincipal();
        String userId = JWTUtil.getUserId(jwt);
        return appUserDao.selectById(userId);
    }

    public static Long getUserId() {
        return getUser().getId();
    }

    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }

    /**
     * <pre>
     * 自动获取分页参数，返回分页对象page
     * </pre>
     * 
     * |
     * 
     * @param e
     * @return
     */
    public <E> Page<E> getPage(Class<E> e) {
        int pageNumber = getParaToInt("pageNumber", 1);
        int pageSize = getParaToInt("pageSize", 10);
        return new Page<>(pageNumber, pageSize);
    }

    private int getParaToInt(String key, int defalut) {
        String pageNumber = HttpContextUtils.getHttpServletRequest().getParameter(key);
        if (StringUtils.isBlank(pageNumber)) {
            return defalut;
        }
        return Integer.parseInt(pageNumber);
    }
}