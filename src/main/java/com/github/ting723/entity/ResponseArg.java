package com.github.ting723.entity;

import lombok.Data;

import java.util.List;

/**
 * Author : zhanglianwei
 * Create : 2018/3/28 11:31
 * Update : 2018/3/28 11:31
 * Descriptions :
 *
 * @author zhanglianwei
 */
@Data
public class ResponseArg {

    private boolean require;

    private String type;

    private String name;

    private String description;

    private List<ResponseArg> children;

    private String testValue;
}
