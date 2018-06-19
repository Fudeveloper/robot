package com.robotcms;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.robotcms.sys.dao.SysLogoutConfigMapper;
import com.robotcms.sys.domain.SysLogoutConfig;
import com.robotcms.sys.service.SysLogoutConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RobotcmsApplicationTests {

    @Autowired
    private SysLogoutConfigMapper sysLogoutConfigService;

    @Test
    public void contextLoads() {
//        SysUser user = sysUserService.selectOne(new EntityWrapper<SysUser>().eq("username","admin"));
//		Map<String, Object> stringObjectMap =new HashMap<>();
        SysLogoutConfig sysLogoutConfig = new SysLogoutConfig();
        sysLogoutConfig.setName("status");
        SysLogoutConfig name = sysLogoutConfigService.selectOne(sysLogoutConfig);
        System.out.println(name.getValue());
    }

}
