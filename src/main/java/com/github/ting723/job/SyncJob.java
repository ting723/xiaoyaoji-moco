package com.github.ting723.job;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Author : zhanglianwei
 * Create : 2018/3/27 18:56
 * Update : 2018/3/27 18:56
 * Descriptions :
 *
 * @author zhanglianwei
 */
@Component
public class SyncJob {

    /**
     * 初始化数据到缓存
     */
    @PostConstruct
    public void initData() {

    }
}
