package com.robotcms.job.service.impl;

import java.io.Serializable;
import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robotcms.common.base.CoreServiceImpl;
import com.robotcms.common.config.Constant;
import com.robotcms.common.utils.ScheduleJobUtils;
import com.robotcms.job.dao.TaskDao;
import com.robotcms.job.domain.ScheduleJob;
import com.robotcms.job.domain.TaskDO;
import com.robotcms.job.quartz.QuartzManager;
import com.robotcms.job.service.JobService;

/**
 * <pre>
 * </pre>
 * |
 */
@Service
public class JobServiceImpl extends CoreServiceImpl<TaskDao, TaskDO> implements JobService {

    @Autowired
    QuartzManager quartzManager;


    @Override
    public boolean deleteById(Serializable id) {
        try {
            TaskDO scheduleJob = selectById(id);
            quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
            return retBool(baseMapper.deleteById(id));
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteBatchIds(List<? extends Serializable> ids) {
        for (Serializable id : ids) {
            try {
                TaskDO scheduleJob = selectById(id);
                quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
            } catch (SchedulerException e) {
                e.printStackTrace();
                return false;
            }
        }
        return retBool(baseMapper.deleteBatchIds(ids));
    }

    @Override
    public void initSchedule() throws SchedulerException {
        // 这里获取任务信息数据
        List<TaskDO> jobList = baseMapper.selectList(null);
        for (TaskDO scheduleJob : jobList) {
            if ("1".equals(scheduleJob.getJobStatus())) {
                ScheduleJob job = ScheduleJobUtils.entityToData(scheduleJob);
                quartzManager.addJob(job);
            }

        }
    }

    @Override
    public void changeStatus(Long jobId, String cmd) throws SchedulerException {
        TaskDO scheduleJob = selectById(jobId);
        if (scheduleJob == null) {
            return;
        }
        if (Constant.Job.STATUS_RUNNING_STOP.equals(cmd)) {
            quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
            scheduleJob.setJobStatus(ScheduleJob.STATUS_NOT_RUNNING);
        } else {
            if (!Constant.Job.STATUS_RUNNING_START.equals(cmd)) {
            } else {
                scheduleJob.setJobStatus(ScheduleJob.STATUS_RUNNING);
                quartzManager.addJob(ScheduleJobUtils.entityToData(scheduleJob));
            }
        }
        updateById(scheduleJob);
    }

    @Override
    public void updateCron(Long jobId) throws SchedulerException {
        TaskDO scheduleJob = selectById(jobId);
        if (scheduleJob == null) {
            return;
        }
        if (ScheduleJob.STATUS_RUNNING.equals(scheduleJob.getJobStatus())) {
            quartzManager.updateJobCron(ScheduleJobUtils.entityToData(scheduleJob));
        }
        updateById(scheduleJob);
    }

}
