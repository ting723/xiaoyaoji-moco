package com.github.ting723.interceptor;

import com.github.ting723.entity.HttpRequestEntity;
import com.github.ting723.http.RequestParseFactory;
import com.github.ting723.service.InterfaceService;
import com.github.ting723.util.MyHttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * Author : zhanglianwei
 * Create : 2018/3/27 18:12
 * Update : 2018/3/27 18:12
 * Descriptions :
 *
 * @author zhanglianwei
 */
@Component
public class MockInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private InterfaceService interfaceService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpRequestEntity oldRequest = MyHttpUtils.convertServletRequest(request);
        Map<String, HttpRequestEntity> urlMap;
        // 对路径url进行处理
        HttpRequestEntity configRequest = null;
        if (!StringUtils.isEmpty(oldRequest.getUrl())) {
            String[] urlArr = oldRequest.getUrl().split("/");
            if (urlArr.length > 1) {
                String projectId = urlArr[1];
                urlMap = interfaceService.getUrlMapByProjectId(projectId);
                if (urlMap.get(oldRequest.getUrl()) != null) {
                    configRequest = urlMap.get(oldRequest.getUrl());
                } else {
                    configRequest = interfaceService.getUrlMapByPathVar(projectId, oldRequest.getUrl());
                }
            }
        }

        RequestParseFactory requestParseFactory = RequestParseFactory
                .getRequestInfo(oldRequest.getRequestMethod()
                        , oldRequest.getContentType());
        requestParseFactory.writeIntoResponse(configRequest,
                requestParseFactory.judgeIsConform(oldRequest, configRequest),
                response);
        return false;
    }
}
