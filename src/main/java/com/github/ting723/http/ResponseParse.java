package com.github.ting723.http;

import com.github.ting723.entity.ResponseArg;
import com.google.common.collect.Maps;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Author : zhanglianwei
 * Create : 2018/4/1 11:09
 * Update : 2018/4/1 11:09
 * Descriptions :
 *
 * @author zhanglianwei
 */
public class ResponseParse {

    public static Map<String, Object> convertResponse(List<ResponseArg> responseArgList) {
        if (CollectionUtils.isEmpty(responseArgList)) {
            return Maps.newHashMap();
        }
        Map<String, Object> response = Maps.newHashMap();
        responseArgList.parallelStream().forEach(arg -> response.putAll(mockResponse(arg)));
        return response;

    }

    public static Map<String, Object> mockResponse(ResponseArg responseArg) {
        Map<String, Object> mpb = Maps.newHashMap();
        Object value = "";
        if (responseArg.getType().equals("string")) {
            value = "mock";
        }
        if (responseArg.getType().equals("number")) {
            value = new Random().nextInt(100);
        }
        if (responseArg.getType().equals("boolean")) {
            value = true;
        }
        if (responseArg.getType().equals("object")) {
            value = convertResponse(responseArg.getChildren());
        }
        if (responseArg.getType().equals("array[object]")) {
            Map<String, Object> objectMap = convertResponse(responseArg.getChildren());
            value = Collections.singletonList(objectMap);
        }
        mpb.put(responseArg.getName(), value);
        return mpb;
    }
}
