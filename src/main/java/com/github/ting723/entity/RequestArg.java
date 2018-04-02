package com.github.ting723.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Author : zhanglianwei
 * Create : 2018/3/28 11:31
 * Update : 2018/3/28 11:31
 * Descriptions :
 *
 * @author zhanglianwei
 */
@Getter
@Setter
public class RequestArg {

    private boolean require;

    private String type;

    private String name;

    private String description;

    private List<RequestArg> children;

    private String testValue;
}
