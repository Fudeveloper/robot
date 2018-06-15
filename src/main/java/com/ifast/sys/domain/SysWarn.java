package com.ifast.sys.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 报警系统
 * </p>
 *
 * @author lik123
 * @since 2018-06-08
 */
@TableName("sys_warn")
public class SysWarn extends Model<SysWarn> {

    private static final long serialVersionUID = 1L;

    /**
     * 报警方式id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 报警方式名称
     */
    @TableField("warn_name")
    private String warnName;
    /**
     * 报警方式状态：1.开启 0：关闭
     */
    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWarnName() {
        return warnName;
    }

    public void setWarnName(String warnName) {
        this.warnName = warnName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysWarn{" +
        ", id=" + id +
        ", warnName=" + warnName +
        ", status=" + status +
        "}";
    }
}
