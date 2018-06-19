package com.robotcms.sys.domain;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.beans.factory.InitializingBean;

import java.io.Serializable;

/**
 * <p>
 * 登出策略配置表
 * </p>
 *
 * @author lik123
 * @since 2018-06-18
 */
@TableName("sys_logout_config")
public class SysLogoutConfig extends Model<SysLogoutConfig> implements InitializingBean {

    private static final long serialVersionUID = 1L;

    /**
     * 配置名
     */
    private String name;
    private String value;
    /**
     * 备注
     */
    private String remark;
    /**
     * 最后更新时间
     */
    @TableField("update_time")
    private Date updateTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.name;
    }

    @Override
    public String toString() {
        return "SysLogoutConfig{" +
        ", name=" + name +
        ", value=" + value +
        ", remark=" + remark +
        ", updateTime=" + updateTime +
        "}";
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
