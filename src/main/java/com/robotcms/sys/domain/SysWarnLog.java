package com.robotcms.sys.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 报警日志
 * </p>
 *
 * @author lik123
 * @since 2018-06-16
 */
@TableName("sys_warn_log")
public class SysWarnLog extends Model<SysWarnLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 报警日志id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 触发报警的用户（预留）
     */
    private String user;
    /**
     * 报警信息
     */
    private String info;
    /**
     * 报警开始时间
     */
    @TableField("start_time")
    private Date startTime;
    /**
     * 报警解除时间

     */
    @TableField("end_time")
    private Date endTime;
    /**
     * 报警发生地点

     */
    private String place;
    /**
     * 取消报警人
     */
    @TableField("cancel_user")
    private String cancelUser;
    /**
     * 报警代码
     */
    private String code;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCancelUser() {
        return cancelUser;
    }

    public void setCancelUser(String cancelUser) {
        this.cancelUser = cancelUser;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysWarnLog{" +
        ", id=" + id +
        ", user=" + user +
        ", info=" + info +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", place=" + place +
        ", cancelUser=" + cancelUser +
        ", code=" + code +
        "}";
    }
}
