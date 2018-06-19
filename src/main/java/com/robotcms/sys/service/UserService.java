package com.robotcms.sys.service;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.service.IService;
import com.robotcms.common.domain.Tree;
import com.robotcms.sys.domain.DeptDO;
import com.robotcms.sys.domain.UserDO;
import com.robotcms.sys.vo.UserVO;

/**
 * <pre>
 * </pre>
 * 
 * |
 */
@Service("sysUserService")
public interface UserService extends IService<UserDO> {

    boolean exit(Map<String, Object> params);

    Set<String> listRoles(Long userId);

    int resetPwd(UserVO userVO, UserDO userDO);

    int adminResetPwd(UserVO userVO);

    Tree<DeptDO> getTree();

    /**
     * 更新个人信息
     * 
     * @param userDO
     * @return
     */
    int updatePersonal(UserDO userDO);

    /**
     * 更新个人图片
     * 
     * @param file
     *            图片
     * @param avatar_data
     *            裁剪信息
     * @param userId
     *            用户ID
     * @throws Exception
     */
    Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception;
}
