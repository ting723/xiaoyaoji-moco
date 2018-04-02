package com.github.ting723.service.impl;

import com.github.ting723.entity.HttpRequestEntity;
import com.github.ting723.entity.InterfaceEntity;
import com.github.ting723.entity.RequestArg;
import com.github.ting723.entity.ResponseArg;
import com.github.ting723.repository.InterfaceRepository;
import com.github.ting723.service.InterfaceService;
import com.alibaba.fastjson.JSON;
import com.github.ting723.util.MyHttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * Author : zhanglianwei
 * Create : 2018/3/30 14:08
 * Update : 2018/3/30 14:08
 * Descriptions :
 *
 * @author zhanglianwei
 */
@Service
public class InterfaceServiceImpl implements InterfaceService {

    @Autowired
    private InterfaceRepository interfaceRepository;

    @Override
    public List<InterfaceEntity> getEntityByProjectId(String projectId) {
        return interfaceRepository.findInterfaceEntityByProjectId(projectId);
    }

    @Cacheable("urlMap")
    @Override
    public Map<String, HttpRequestEntity> getUrlMapByProjectId(String projectId) {
        return getEntityByProjectId(projectId)
                .parallelStream()
                .filter(interfaceEntity ->
                        !StringUtils.isEmpty(interfaceEntity.getUrl())
                                && interfaceEntity.getProtocol().equals("HTTP")
                                && interfaceEntity.getStatus().equals("ENABLE"))
                .map(interfaceEntity -> {
                    HttpRequestEntity httpRequestEntity = new HttpRequestEntity();
                    httpRequestEntity.setRequestMethod(interfaceEntity.getRequestMethod());
                    httpRequestEntity.setDataType(interfaceEntity.getDataType());
                    httpRequestEntity.setContentType(interfaceEntity.getContentType());
                    httpRequestEntity.setUrl(MyHttpUtils.formatUrl(interfaceEntity.getProjectId() + interfaceEntity.getUrl()));
                    httpRequestEntity.setDescription(interfaceEntity.getDescription());
                    httpRequestEntity.setRequestArgs(JSON.parseArray(interfaceEntity.getRequestArgs(), RequestArg.class));
                    httpRequestEntity.setResponseArgs(JSON.parseArray(interfaceEntity.getResponseArgs(), ResponseArg.class));
                    httpRequestEntity.setProjectId(interfaceEntity.getProjectId());
                    return httpRequestEntity;
                }).collect(toList())
                .parallelStream()
                .collect(toMap(HttpRequestEntity::getUrl, hpr -> hpr));
    }

    @Cacheable(value = "singleUrlMap", key = "#projectId + #pathUrl")
    @Override
    public HttpRequestEntity getUrlMapByPathVar(String projectId, String pathUrl) {
        HttpRequestEntity httpRequestEntity = null;
        Map<String, HttpRequestEntity> urlMap = getUrlMapByProjectId(projectId);
        String[] uArr = pathUrl.split("/");
        Optional<String> urlKey = urlMap.keySet().stream().filter(k -> k.contains("{")).filter(k -> {
            String[] oArr = k.split("/");
            if (oArr.length != uArr.length) {
                return false;
            }

            for (int i = 0; i < oArr.length; i++) {
                if (oArr[i].contains("{")) {
                    oArr[i] = uArr[i];
                }
            }
            if (Arrays.toString(uArr).equals(Arrays.toString(oArr))) {
                return true;
            }
            return false;
        }).findFirst();
        if (urlKey.isPresent()) {
            httpRequestEntity = urlMap.get(urlKey.get());
            httpRequestEntity.setUrl(pathUrl);
        }
        return httpRequestEntity;
    }


}
