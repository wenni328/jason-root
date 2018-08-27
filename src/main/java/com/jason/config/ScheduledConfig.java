package com.jason.config;

import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;

/**
 * 任务调度 **********暂时先注释掉注解****功能正常可用
 *
 * @author xy
 */
//@Configuration
//@EnableScheduling
//@EnableAsync
//@ConfigurationProperties(prefix = "jobs")
//@PropertySource(value = "classpath:boot.properties")
public class ScheduledConfig {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedDelayString = "${jobs.fixedDelay}")
    @Order(value = 1)
    public void getTask1() {
        System.out.println("任务1，当前时间：" + dateFormat.format(new Date()));
    }

    @Scheduled(cron = "${jobs.cron}")
    @Order(value = 0)
    public void getTask2() {
        System.out.println("任务2，当前时间：" + dateFormat.format(new Date()));
    }

    /**
     * 异步任务调度 ----开启请百度
     *
     * @return
     */
    @Async
    public Future<String> doSomeHeavyBackgroundTask() {
        try {
            Thread.sleep(3000);
            System.out.println("任务三：" + dateFormat.format(new Date()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
