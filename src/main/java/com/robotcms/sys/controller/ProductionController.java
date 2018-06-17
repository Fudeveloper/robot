package com.robotcms.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.robotcms.common.annotation.Log;
import com.robotcms.common.base.AdminBaseController;
import com.robotcms.common.utils.Result;
import com.robotcms.sys.domain.SysWarn;
import com.robotcms.sys.domain.UserDO;
import com.robotcms.sys.service.SysWarnService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @author lik
 */

@RequestMapping("/production")
@Controller
public class ProductionController extends AdminBaseController {
    String prefix = "/production";
    @Autowired
    private SysWarnService sysWarnService;

    @RequiresPermissions("production:production")
    @Log("进入生产管理页面")
    @GetMapping()
    public String warn() {
        return prefix + "/production";
    }

    @RequiresPermissions("production:production")
    @Log("进入生产管理页面")
    @GetMapping("/manage")
    public String manage() {
        return prefix + "/manage";
    }


    @RequiresPermissions("production:technology")
    @Log("进入工艺流程页面")
    @GetMapping("/technology")
    public String technology() {
        return prefix + "/technology";
    }

    @RequiresPermissions("production:schedule")
    @Log("进入生产计划排程表页面")
    @GetMapping("/schedule")
    public String schedule() {
        return prefix + "/schedule";
    }

}
