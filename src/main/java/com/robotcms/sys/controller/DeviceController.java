package com.robotcms.sys.controller;

import com.robotcms.common.annotation.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 设备管理相关页面
 * @author lik
 */

@RequestMapping("/device")
@Controller
public class DeviceController {
    String prefix = "/device";

    @Log("进入设备监视页面")
    @GetMapping("/monitor")
    public String monitor(){
        return prefix+"/monitor";
    }

    @Log("进入基本情况页面")
    @GetMapping("/baseinfo")
    public String baseinfo(){
        return prefix+"/baseinfo";
    }

    @Log("进入错误反馈页面")
    @GetMapping("/report")
    public String report(){
        return prefix+"/report";
    }

    @Log("进入首检页面")
    @GetMapping("/check")
    public String check(){
        return prefix+"/check";
    }
}
