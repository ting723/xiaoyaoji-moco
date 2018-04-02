package com.github.ting723.util;

import com.github.ting723.entity.HttpRequestEntity;
import com.github.ting723.entity.RequestArg;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author : zhanglianwei
 * Create : 2018/3/29 18:07
 * Update : 2018/3/29 18:07
 * Descriptions :
 *
 * @author zhanglianwei
 */
public class MyHttpUtils {

    public static HttpRequestEntity convertServletRequest(HttpServletRequest request) throws IOException {
        HttpRequestEntity httpRequestEntity = new HttpRequestEntity();
        List<RequestArg> argList = Lists.newArrayList();
        httpRequestEntity.setUrl(formatUrl(request.getRequestURI()));
        httpRequestEntity.setRequestMethod(request.getMethod());
        if ("GET".equalsIgnoreCase(request.getMethod())) {

            Map<String, String[]> paramsMap = request.getParameterMap();
            paramsMap.forEach((k, v) -> {
                RequestArg requestArg = new RequestArg();
                requestArg.setName(String.valueOf(k));
                if (!StringUtils.isEmpty(v[0])) {
                    requestArg.setType("string");
                }
                argList.add(requestArg);
            });
        }
        if ("application/json".equals(request.getContentType())) {
            httpRequestEntity.setContentType("JSON");
            String json = new BufferedReader(new InputStreamReader(request.getInputStream()))
                    .lines().collect(Collectors.joining(""));
            Map jsonMap = JSON.parseObject(json, Map.class);
            jsonMap.forEach((k, v) -> {
                RequestArg requestArg = new RequestArg();
                requestArg.setName(String.valueOf(k));
                if (v instanceof String) {
                    requestArg.setType("string");
                }

                if (v instanceof Integer || v instanceof Double) {
                    requestArg.setType("number");
                }

                argList.add(requestArg);
            });

        } else if ("application/x-www-form-urlencoded".equalsIgnoreCase(request.getContentType())) {
            Map<String, String[]> paramsMap = request.getParameterMap();
            paramsMap.forEach((k, v) -> {
                RequestArg requestArg = new RequestArg();
                requestArg.setName(String.valueOf(k));
                if (!StringUtils.isEmpty(v[0])) {
                    requestArg.setType("string");
                }
                argList.add(requestArg);
            });

        } else if ("multipart/form-data".equalsIgnoreCase(request.getContentType())) {
            Map<String, String[]> paramsMap = request.getParameterMap();
            paramsMap.forEach((k, v) -> {
                RequestArg requestArg = new RequestArg();
                requestArg.setName(String.valueOf(k));
                if (!StringUtils.isEmpty(v[0])) {
                    requestArg.setType("string");
                }
                argList.add(requestArg);
            });
        }
        httpRequestEntity.setRequestArgs(argList);
        return httpRequestEntity;
    }

    /**
     * 转换为url
     *
     * @param url
     * @return
     */
    public static String formatUrl(String url) {
        if (StringUtils.isEmpty(url)) {
            return "/";
        }
        url = "/".equalsIgnoreCase(url.substring(0, 1)) ? url : "/" + url;
        return url.endsWith("/") ? url : url + "/";
    }
}
