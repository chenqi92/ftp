package com.lyc.ftp.handle;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.NamedThreadFactory;
import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.lyc.ftp.utils.ContinueFtp;
import com.lyc.ftp.utils.FtpProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

/**
 * 功能: 定时传送站点xml文件至ftp服务器
 *
 * @author chenQi
 * @version 1.0
 * @date 2020/11/16 15:47
 */
@Component
@AllArgsConstructor
@Slf4j
public class ReadFileHandle {

    private FtpProperties ftpProperties;

    private final ThreadPoolTaskExecutor taskExecutor;

    @Scheduled(cron = "0 0 * * * ?")
    private void readFileAndSend() {
        ContinueFtp myFtp = new ContinueFtp();
        log.info("开始执行" + DateUtil.now());
        try {
            // 查询本地文件夹中所有文件
            File file = new File(ftpProperties.getPath());
            File[] fileList = file.listFiles();
            if (fileList != null && fileList.length > 0) {

                for (File f : fileList) {
                    // 判断文件创建时间，如果文件时间大于当前时间跳过
                    String[] fileName = f.getName().split("_");
                    String time = fileName[2];
                    // 时间比较
                    if (LocalDateTime.now().isBefore(LocalDateTime.parse(time, DateTimeFormatter.ofPattern(DatePattern.PURE_DATETIME_PATTERN)))) {
                        continue;
                    }
                    taskExecutor.execute(() -> {
                        try {
                            myFtp.connect(ftpProperties.getUrl(), ftpProperties.getPort(), ftpProperties.getUsername(), ftpProperties.getPassword());
                            myFtp.upload(f, f.getName());
                            myFtp.disconnect();
                            Thread.sleep(120000L);
                        } catch (IOException e) {
                            System.out.println("ftp连接出错" + e.getLocalizedMessage());
                        } catch (InterruptedException e) {
                            System.out.println("线程出错" + e.getLocalizedMessage());
                        }
                    });
                }
            }
        } catch (Exception e) {
            System.out.println("系统错误：" + e.getMessage());
        }
    }
}