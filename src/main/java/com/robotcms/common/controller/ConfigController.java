package com.robotcms.common.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.robotcms.common.annotation.Log;
import com.robotcms.common.base.AdminBaseController;
import com.robotcms.common.domain.ConfigDO;
import com.robotcms.common.service.ConfigService;
import com.robotcms.common.utils.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;



@Controller
@RequestMapping("/common/config")
public class ConfigController extends AdminBaseController {
    @Autowired
    private ConfigService configService;
    
    @Log("进入系统配置页面")
    @GetMapping()
    @RequiresPermissions("common:config:config")
    String Config() {
        return "common/config/config";
    }
    
    @Log("查询系统配置列表")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("common:config:config")
    public Result<Page<ConfigDO>> list(ConfigDO configDTO) {
        // 查询列表数据
        Page<ConfigDO> page = getPage(ConfigDO.class);

        Wrapper<ConfigDO> wrapper = new EntityWrapper<ConfigDO>(configDTO);
        page = configService.selectPage(page, wrapper);
        return Result.ok(page);
    }
    
    @Log("进入系统配置添加页面")
    @GetMapping("/add")
    @RequiresPermissions("common:config:add")
    String add() {
        return "common/config/add";
    }
    
    @Log("进入配置编辑页面")
    @GetMapping("/edit/{id}")
    @RequiresPermissions("common:config:edit")
    String edit(@PathVariable("id") Long id, Model model) {
        ConfigDO config = configService.selectById(id);
        model.addAttribute("config", config);
        return "common/config/edit";
    }

    /**
     * 保存
     */
    @Log("添加系统配置")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("common:config:add")
    public Result<String> save(ConfigDO config) {
        if (configService.insert(config)) {
            return Result.ok();
        }
        return Result.fail();
    }

    /**
     * 修改
     */
    @Log("更新系统配置")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("common:config:edit")
    public Result<String> update(ConfigDO config) {
        configService.updateById(config);
        return Result.ok();
    }

    /**
     * 删除
     */
    @Log("删除系统配置")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("common:config:remove")
    public Result<String> remove(Long id) {
        configService.deleteById(id);
        return Result.ok();
    }

    /**
     * 删除
     */
    @Log("批量删除系统配置")
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("common:config:batchRemove")
    public Result<String> remove(@RequestParam("ids[]") Long[] ids) {
        configService.deleteBatchIds(Arrays.asList(ids));
        return Result.ok();
    }

}
