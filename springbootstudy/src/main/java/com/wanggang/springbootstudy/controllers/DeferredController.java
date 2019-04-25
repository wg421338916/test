package com.wanggang.springbootstudy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-24 20:05
 * https://blog.csdn.net/icarusliu/article/details/79539105
 **/
@RestController
public class DeferredController {

    @RequestMapping("/testCallable")
    public Callable<String> testCallable() {
        System.out.println("Controller开始执行！");
        Callable<String> callable = () -> {
            Thread.sleep(5000);

            System.out.println("实际工作执行完成！");

            return "succeed!";
        };
        System.out.println("Controller执行结束！");
        return callable;
    }

    @RequestMapping("/testDeferredResult")
    public DeferredResult<String> testDeferredResult() {
        //3s超时时间
        ResponseEntity<String> noModify = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        DeferredResult<String> deferredResult = new DeferredResult<>(3L, noModify);

        System.out.println("Controller开始执行！");
        new Thread(() -> {
            try {
                Thread.sleep(5000);

                System.out.println("实际工作执行完成！");

                deferredResult.setResult("succeed!" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
        System.out.println("Controller执行结束！");

        deferredResult.onCompletion(() -> {
            System.out.println("completion");
        });
        deferredResult.onTimeout(() -> {
            System.out.println("timeout");
        });
        deferredResult.onError((throwable) -> {
            System.out.println(throwable.getMessage());
        });

        return deferredResult;
    }

    @RequestMapping("/testSseEmitter")
    public SseEmitter testSseEmitter() throws IOException {
        SseEmitter sseEmitter = new SseEmitter();

        sseEmitter.onCompletion(() -> {
            System.out.println("completion");
        });
        sseEmitter.onTimeout(() -> {
            System.out.println("timeout");
        });
        sseEmitter.onError((throwable) -> {
            System.out.println(throwable.getMessage());
        });

        new Thread(() -> {
            try {
                sseEmitter.send(System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(1);
                sseEmitter.send(System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(10);
                sseEmitter.send(System.currentTimeMillis());//java.io.IOException: 你的主机中的软件中止了一个已建立的连接。
                TimeUnit.SECONDS.sleep(3);
                System.out.println("sth");
                sseEmitter.complete();//不抛异常
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        return sseEmitter;
    }
}
