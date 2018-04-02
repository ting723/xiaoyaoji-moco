package com.github.ting723.http;

import com.github.ting723.entity.HttpRequestEntity;
import com.github.ting723.entity.RequestArg;
import com.github.ting723.util.ResultWrap;
import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * Author : zhanglianwei
 * Create : 2018/4/1 10:59
 * Update : 2018/4/1 10:59
 * Descriptions : 请求方法处理工厂
 *
 * @author zhanglianwei
 */
public class RequestParseFactory {

    public static RequestParseFactory getRequestInfo(String requestMethod, String contentType) {
        if (requestMethod.equals("GET")) {
            return new GetRequestParse();
        } else if (requestMethod.equals("POST")) {
            return new PostRequestParse(contentType);
        } else if (requestMethod.equals("PUT")) {
            return new PutRequestParse();
        } else if (requestMethod.equals("DELETE")) {
            return new DeleteRequestParse();
        }
        return new RequestParseFactory();
    }

    public String judgeIsConform(HttpRequestEntity oldRequest, HttpRequestEntity configRequest) {
        if (configRequest == null || !oldRequest.getUrl().equals(configRequest.getUrl())) {
            return JSON.toJSONString(ResultWrap.FAILURE("Url不正确"));
        }

        if (!oldRequest.getRequestMethod().equals(configRequest.getRequestMethod())) {
            return JSON.toJSONString(ResultWrap.FAILURE("请求方法不正确"));
        }

        if (!StringUtils.isEmpty(oldRequest.getContentType()) &&
                !oldRequest.getContentType().equals(configRequest.getContentType())) {
            return JSON.toJSONString(ResultWrap.FAILURE("请求ContentType 不正确"));
        }

        Map<String, String> requireBody = configRequest.getRequestArgs().stream()
                .filter(RequestArg::isRequire)
                .collect(toMap(RequestArg::getName, RequestArg::getType));

        Map<String, String> realBody = oldRequest.getRequestArgs().stream()
                .collect(toMap(RequestArg::getName, RequestArg::getType));
        if (!CollectionUtils.isEmpty(realBody)) {
            for (String key : requireBody.keySet()) {
                if (!realBody.containsKey(key)) {
                    return JSON.toJSONString(ResultWrap.FAILURE("缺失参数:" + key));
                }
                if (!realBody.get(key).equals(requireBody.get(key))) {
                    return JSON.toJSONString(ResultWrap.FAILURE("参数类型不正确:" + key));
                }
            }
        }
        return "success";
    }

    public void writeIntoResponse(HttpRequestEntity configRequest, Object content, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(content.toString());
    }
}
