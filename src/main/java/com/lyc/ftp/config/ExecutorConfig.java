package com.lyc.ftp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 类 TaskScheduler
 * </p>
 *
 * @author ChenQi
 * @since 2020/11/14 15:33
 */
@Configuration
@EnableAsync
public class ExecutorConfig {

    private static int CORE_POOL_SIZE = 3;
    private static int MAX_POOL_SIZE = 3000;

    @Bean(name = "taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        //线程池维护线程的最少数量
        poolTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        //线程池维护线程的最大数量
        poolTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        //线程池所使用的缓冲队列
        poolTaskExecutor.setQueueCapacity(200);
        //线程池维护线程所允许的空闲时间
        poolTaskExecutor.setKeepAliveSeconds(30000);
        // 线程默认名称
        poolTaskExecutor.setThreadNamePrefix("jd-ftp-");
        // 拒绝策略
        poolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        poolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        poolTaskExecutor.initialize();
        return poolTaskExecutor;
    }
}