package com.javatrainingcamp.week08.controller;

import com.javatrainingcamp.week08.domain.EmallOrder;
import com.javatrainingcamp.week08.mapper.EmallOrderMapper;
import com.javatrainingcamp.week08.service.EmallOrderService;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.internal.guava.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author deguang
 * @date 2021/03/07
 */

@Slf4j
@RestController
public class TestController {

    private final int maxCount = 10;
    private final int part = 10;

    @Autowired
    private EmallOrderService emallOrderService;

    @Autowired
    private EmallOrderMapper emallOrderMapper;

    @RequestMapping("/insert")
    public String testInsert() {
        log.info("接收insert请求:");
        emallOrderService.insert(getSimpleEmallOrder());
        List<EmallOrder> emallOrderList = emallOrderMapper.selectByAll();
        return "success";
    }

    @RequestMapping("/testBatchInsert1")
    public String testBatchInsert1() {
        log.info("接收batchInsert1请求:");
        long start, end;
        start = System.currentTimeMillis();
        for (int i = 0; i < maxCount; i++) {
            emallOrderService.insert(getSimpleEmallOrder());
        }
        end = System.currentTimeMillis();
        log.info("start time:" + start+ "; end time:" + end+ "; Run Time:" + (end - start) + "(ms)");
        return "success";
    }

    @RequestMapping("/testBatchInsert2")
    public String testBatchInsert2() {
        log.info("接收batchInsert2请求:");
        long start, end;
        start = System.currentTimeMillis();
        EmallOrder[] emallOrder = new EmallOrder[maxCount];
        Arrays.fill(emallOrder, getSimpleEmallOrder());
        List<EmallOrder> emallOrderList = Arrays.asList(emallOrder);
        emallOrderService.batchInsert(emallOrderList);
        end = System.currentTimeMillis();
        log.info("start time:" + start+ "; end time:" + end+ "; Run Time:" + (end - start) + "(ms)");
        return "success";
    }

    @RequestMapping("/testBatchInsert3")
    public String testBatchInsert3() {
        log.info("接收batchInsert3请求:");
        long start, end;
        start = System.currentTimeMillis();
        int count = 10;
        ExecutorService executorService = new ThreadPoolExecutor(10, 10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), new ThreadFactoryBuilder()
                .setNameFormat("executorService-pool-%d").build(), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < count; i++) {
            executorService.submit(this::insertPart);
        }
        end = System.currentTimeMillis();
        log.info("start time:" + start+ "; end time:" + end+ "; Run Time:" + (end - start) + "(ms)");
        return "success";
    }

    private EmallOrder getSimpleEmallOrder() {
        EmallOrder emallOrder = new EmallOrder();
        emallOrder.setGmtCreateTime(new Date());
        emallOrder.setGmtModifiedTime(new Date());
        emallOrder.setIsDeleted("N");
        emallOrder.setMemberId(1L);
        emallOrder.setReceiverName("1");
        emallOrder.setReceiverPhone("1");
        return emallOrder;
    }

    private void insertPart() {
        int count = maxCount / part;
        for (int i = 0; i < count; i++) {
            emallOrderService.insert(getSimpleEmallOrder());
        }
    }
}
