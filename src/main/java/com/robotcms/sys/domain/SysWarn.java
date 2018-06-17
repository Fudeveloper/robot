package com.robotcms.sys.domain;

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
 * @since 2018-06-16
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
    /**
     * 报警信息接收者
     */
    private String receiver;
    /**
     * 报警内容

     */
    private String content;


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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        ", receiver=" + receiver +
        ", content=" + content +
        "}";
    }
}
