package com.github.ting723.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Author : zhanglianwei
 * Create : 2018/3/30 13:53
 * Update : 2018/3/30 13:53
 * Descriptions :
 *
 * @author zhanglianwei
 */
@Getter
@Setter
@Entity
@Table(name = "interface")
public class InterfaceEntity implements Serializable {
    private static final long serialVersionUID = 6772765789044785866L;

    /**
     * id
     */
    @Id
    @Column(name = "id")
    private String id;
    /**
     * 接口名
     */
    @Column(name = "name")
    private String name;
    /**
     * 接口描述
     */
    @Column(name = "description")
    private String description;
    /**
     * 所属文件夹id
     */
    @Column(name = "folderId")
    private String folderId;
    /**
     * 接口url
     */
    @Column(name = "url")
    private String url;
    /**
     * 请求类型
     */
    @Column(name = "requestMethod")
    private String requestMethod;
    /**
     * 请求内容类型
     */
    @Column(name = "dataType")
    private String contentType;
    /**
     * 请求头
     */
    @Column(name = "requestHeaders")
    private String requestHeaders;
    /**
     * 请求参数
     */
    @Column(name = "requestArgs")
    private String requestArgs;
    /**
     * 响应参数
     */
    @Column(name = "responseArgs")
    private String responseArgs;
    /**
     * 示例
     */
    @Column(name = "example")
    private String example;
    /**
     * 模块Id
     */
    @Column(name = "moduleId")
    private String moduleId;
    /**
     * 所隶属工程id
     */
    @Column(name = "projectId")
    private String projectId;
    /**
     * 上次更新时间
     */
    @Column(name = "lastUpdateTime")
    private Date lastUpdateTime;
    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createTime;
    /**
     * 返回数据类型
     */
    @Column(name = "contentType")
    private String dataType;
    /**
     * 协议类型
     */
    @Column(name = "protocol")
    private String protocol;
    /**
     * 是否可用
     */
    @Column(name = "status")
    private String status;
    /**
     * 排序
     */
    @Column(name = "sort")
    private Long sort;


}
