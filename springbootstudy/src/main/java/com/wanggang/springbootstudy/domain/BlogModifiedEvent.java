package com.wanggang.springbootstudy.domain;

import org.springframework.context.ApplicationEvent;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-23 09:55
 **/
public class BlogModifiedEvent extends ApplicationEvent {

    private boolean importantChange;

    public BlogModifiedEvent(Object source) {
        super(source);
        importantChange = (Boolean) source;
    }


    public boolean isImportantChange() {
        return importantChange;
    }
}
