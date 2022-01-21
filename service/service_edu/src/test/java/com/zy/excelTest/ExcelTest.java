package com.zy.excelTest;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author ZY
 * @create 2022/1/20 14:51
 */
@Data
public class ExcelTest {
    @ExcelProperty(value = "id",index = 0)//设置写入的表头名字
    private Integer sno;
    @ExcelProperty(value = "name" ,index = 1)
    private String sname;
}
