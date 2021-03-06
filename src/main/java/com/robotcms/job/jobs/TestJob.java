package com.robotcms.job.jobs;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.robotcms.common.utils.DateUtils;
/**
 * <pre>
 * </pre>
 * 
 *
 */
@Component
public class TestJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.err.println("测试任务执 | " + DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN_19)
                + " | 定时统计人数：\" + userService.selectCount(null)");
    }

}
