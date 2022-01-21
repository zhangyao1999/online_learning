package com.zy.excelTest;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author ZY
 * @create 2022/1/20 15:53
 */
public class ExcelTestListener extends AnalysisEventListener<ExcelTest> {
    @Override
    public void invoke(ExcelTest excelTest, AnalysisContext analysisContext) {
        System.out.println("data"+excelTest);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("dataHead"+headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
