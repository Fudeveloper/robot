package com.ifast.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ifast.common.annotation.Log;
import com.ifast.common.base.AdminBaseController;
import com.ifast.common.utils.Result;
import com.ifast.sys.domain.SysWarn;
import com.ifast.sys.domain.UserDO;
import com.ifast.sys.service.SysWarnService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @author lik
 */

@RequestMapping("/sys/warn")
@Controller
public class WarnController extends AdminBaseController {
    String prefix = "sys/warn";
    @Autowired
    private SysWarnService sysWarnService;

    @RequiresPermissions("sys:warn:warn")
    @Log("进入报警管理页面")
    @GetMapping()
    public String warn() {
        return prefix + "/warn";
    }

    @Log("查询所有报警方式")
    @GetMapping("/list")
    @ResponseBody
    public Result<Page<SysWarn>> list(SysWarn sysWarn) {
        // 查询列表数据
        Wrapper<SysWarn> wrapper = new EntityWrapper<SysWarn>(sysWarn);
        Page<SysWarn> page = sysWarnService.selectPage(getPage(SysWarn.class), wrapper);
        return Result.ok(page);
    }

    @Log("修改报警方式状态")
    @PostMapping("/status/{id}")
    @ResponseBody
    public  Result changeWarnStatus(@PathVariable("id") Integer id){
//        System.out.println("修改报警方式状态");
        SysWarn sysWarn = sysWarnService.selectById(id);
        Integer status = sysWarn.getStatus();
//        System.out.println(sysWarn.getStatus());
        sysWarn.setStatus(status==1?0:1);
//        System.out.println(sysWarn.getStatus());
        sysWarnService.updateById(sysWarn);
        return Result.ok();
    }
}
