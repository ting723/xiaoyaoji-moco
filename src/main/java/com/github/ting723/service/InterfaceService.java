package com.github.ting723.service;

import com.github.ting723.entity.HttpRequestEntity;
import com.github.ting723.entity.InterfaceEntity;

import java.util.List;
import java.util.Map;

/**
 * Author : zhanglianwei
 * Create : 2018/3/27 18:38
 * Update : 2018/3/27 18:38
 * Descriptions :
 *
 * @author zhanglianwei
 */
public interface InterfaceService {

    /**
     * 通过projectId获取http实体
     *
     * @param projectId
     * @return
     */
    List<InterfaceEntity> getEntityByProjectId(String projectId);


    /**
     * 转换为HttpRequestEntity
     *
     * @param projectId
     * @return
     */
    Map<String, HttpRequestEntity> getUrlMapByProjectId(String projectId);

    /**
     * 包含路径参数
     *
     * @param pathUrl
     * @return
     */
    HttpRequestEntity getUrlMapByPathVar(String project, String pathUrl);
}
