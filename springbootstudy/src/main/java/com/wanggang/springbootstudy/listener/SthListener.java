package com.wanggang.springbootstudy.listener;

import com.wanggang.springbootstudy.domain.BlogModifiedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-23 09:56
 **/
@Component
@EnableAsync
public class SthListener {
    @Async    //Remember to enable asynchronous method execution  in your application with @EnableAsync
    @EventListener(condition = "#blogModifiedEvent.importantChange")
    public void onBlogModified(BlogModifiedEvent blogModifiedEvent) {
        System.out.println(blogModifiedEvent.isImportantChange());
    }
}
