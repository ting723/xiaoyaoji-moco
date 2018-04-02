package com.github.ting723.http;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.github.ting723.entity.HttpRequestEntity;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Author : zhanglianwei
 * Create : 2018/4/1 11:02
 * Update : 2018/4/1 11:02
 * Descriptions :
 *
 * @author zhanglianwei
 */
public class GetRequestParse extends RequestParseFactory {

    @Override
    public String judgeIsConform(HttpRequestEntity oldRequest, HttpRequestEntity configRequest) {
        String content = super.judgeIsConform(oldRequest, configRequest);
        if ("success".equals(content)) {
            if (configRequest.getDataType().equals("JSON")) {
                content = JSON.toJSONString(ResponseParse.convertResponse(configRequest.getResponseArgs()));
            }
        }
        return content;
    }

    @Override
    public void writeIntoResponse(HttpRequestEntity configRequest, Object content, HttpServletResponse httpServletResponse) throws IOException {
        if ("success".equals(content) && configRequest.getDataType().equals("BINARY")) {
            httpServletResponse.setHeader("content-Type", "application/vnd.ms-excel");
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + new String("test.xls".getBytes("gb2312"), "iso8859-1"));
            List<Map<String, Object>> maps = Lists.newArrayList();
            Workbook workbook = ExcelExportUtil.exportExcel(maps, ExcelType.HSSF);
            workbook.write(httpServletResponse.getOutputStream());
        }else {
            super.writeIntoResponse(configRequest,content,httpServletResponse);
        }
    }
}
