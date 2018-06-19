package com.robotcms.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.robotcms.common.annotation.Log;
import com.robotcms.common.base.AdminBaseController;
import com.robotcms.common.utils.Result;
import com.robotcms.sys.domain.SysLogoutConfig;
import com.robotcms.sys.domain.SysWarn;
import com.robotcms.sys.service.SysLogoutConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author lik
 */

@Controller
@RequestMapping("/sys/config")
public class SysLogoutConfigController extends AdminBaseController {

    @Autowired
    private SysLogoutConfigService sysLogoutConfigService;

    private String prefix = "sys/config";

    @Log("进入系统登出策略配置页面")
    @GetMapping("/logout")
    public String  syslogoutconfig(){
        return prefix+"/logout";
    }


    @ResponseBody
    @GetMapping("/list")
    public Result list(SysLogoutConfig sysLogoutConfig){
        EntityWrapper<SysLogoutConfig> wrapper = new EntityWrapper<>();
        Page<SysLogoutConfig> sysLogoutConfigPage = sysLogoutConfigService.selectPage(new Page<SysLogoutConfig>(), wrapper);
        return Result.ok(sysLogoutConfigPage);
    }

    @Log("编辑系统登出策略配置")
    @GetMapping("/edit/{name}")
    public String edit(@PathVariable String name, Model model){
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("name",name);
        SysLogoutConfig sysLogoutConfig= sysLogoutConfigService.selectByMap(stringObjectHashMap).get(0);
        model.addAttribute("warn",sysLogoutConfig);
        return prefix+"/edit";
    }


//    @Log("更新系统登出策略配置")
    @PostMapping("/update")
    @ResponseBody
    public Result update(SysLogoutConfig sysLogoutConfig){

        SysLogoutConfig prepare = new SysLogoutConfig();
        prepare.setName(sysLogoutConfig.getName());
        EntityWrapper<SysLogoutConfig> wrapper= new EntityWrapper<>();
        wrapper.setEntity(prepare);
        sysLogoutConfigService.update(sysLogoutConfig,wrapper);
        return Result.ok();
    }


}
