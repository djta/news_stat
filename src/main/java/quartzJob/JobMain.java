package quartzJob;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by 范志伟 on 2018-02-28.
 */
public class JobMain {
    public static void main(String args[]){
//        System.out.println("【任务启动】开始(每10秒输出一次)...");
//        //创建调度器
//        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
//        //创建任务
//        //创建触发器 每3秒钟执行一次
//        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger_1min", "group_marketkline")
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(50).repeatForever())
//                .build();
//        try {
//            //将任务及其触发器放入调度器
//            schedulerFactory.getScheduler().scheduleJob(jobDetail, trigger);
//            //调度器开始调度任务
//            schedulerFactory.getScheduler().start();
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
    }
}
