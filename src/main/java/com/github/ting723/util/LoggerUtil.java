package com.github.ting723.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author : zhanglianwei
 * Create : 2018/3/30 9:52
 * Update : 2018/3/30 9:52
 * Descriptions :
 *
 * @author zhanglianwei
 */
public class LoggerUtil {

    private final static Logger logger = LoggerFactory.getLogger("InfoLogger");

    private LoggerUtil() {

    }

    public static Logger logger() {
        return logger;
    }

}
