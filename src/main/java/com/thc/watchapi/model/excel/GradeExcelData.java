package com.thc.watchapi.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author thc
 * @Title:
 * @Package com.thc.watchapi.model.excel  处理excel表格数据
 * @Description:
 * @date 2020/12/26 2:19 下午
 */
@Data
public class GradeExcelData {

    /**
     * 学生编号
     */
    // 表头名 index表示第几列
    @ExcelProperty(value = "XH", index = 0)
    private String stuNumber;

    /**
     * 学生姓名
     */
    @ExcelProperty(value = "XM", index = 1)
    private String name;

    /**
     * 科目
     */
    @ExcelProperty(value = "KCMC", index = 2)
    private String subject;

    /**
     * 科目
     */
    @ExcelProperty(value = "ZPCJ2", index = 3)
    private Double score;

    /**
     * 学分
     */
    @ExcelProperty(value = "XF", index = 4)
    private Double weight;

    /**
     * 学分
     */
    @ExcelProperty(value = "BJMC", index = 5)
    private String bjmc;
}
