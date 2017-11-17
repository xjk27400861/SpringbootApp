package com.Spring.App;

import java.util.Random;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.Component;

//异步调用定义
//Spring Boot的主程序中配置@EnableAsync
@Component
public class TaskAsync {
	public static Random random =new Random();
	
	@Async
	public Future<String> doTaskOne() throws Exception {
	    System.out.println("开始做任务一");
	    long start = System.currentTimeMillis();
	    Thread.sleep(random.nextInt(10000));
	    long end = System.currentTimeMillis();
	    System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
	    return new AsyncResult<>("任务一完成");
	}
	
	@Async
	public Future<String> doTaskTwo() throws Exception {
		System.out.println("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务二完成");
	}
	
	@Async
	public Future<String> doTaskThree() throws Exception {
		System.out.println("开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务三，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务三完成");
    }
}
