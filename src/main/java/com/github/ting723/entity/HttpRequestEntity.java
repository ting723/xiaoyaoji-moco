package com.github.ting723.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Author : zhanglianwei
 * Create : 2018/3/28 11:24
 * Update : 2018/3/28 11:24
 * Descriptions :
 *
 * @author zhanglianwei
 */
@Getter
@Setter
public class HttpRequestEntity {

    private String projectId;

    private String requestMethod;

    private String dataType;

    private String contentType;

    private List<RequestArg> requestArgs;

    private List<ResponseArg> responseArgs;

    private String requestHeaders;

    private String responseHeaders;

    private String url;

    private String status;

    private String description;
}
