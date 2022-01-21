package com.zy.excelTest;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZY
 * @create 2022/1/20 15:03
 */
public class ExcelTestWrite {
    public static void main(String[] args) {
//        EasyExcel.write("D://01.xlsx",ExcelTest.class).sheet("1").doWrite(getData());
        EasyExcel.read("D://01.xlsx",ExcelTest.class,new ExcelTestListener()).sheet().doRead();
    }
    static List<ExcelTest> getData(){
        ArrayList<ExcelTest> excelTests = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExcelTest excelTest = new ExcelTest();
            excelTest.setSno(i);
            excelTest.setSname("zy"+i+"号");
            excelTests.add(excelTest);
        }
        return excelTests;
    }
}
