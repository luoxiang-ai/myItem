package com.java.carrent.utils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

public class ExportHSSFCellStyle {

    /**
     * 创建基础样式，水平垂直居中
     * @param workbook
     * @return
     */
    public static HSSFCellStyle createBaseStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
//		设置水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
//		设置垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        return style;
    }

    /**
     * 创建数据表格的头样式
     * @param workbook
     * @return
     */
    public static HSSFCellStyle createTableTitleStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = createBaseStyle(workbook);

//		设置字体
        HSSFFont font = workbook.createFont();
        font.setBold(true);  // 是否加粗
        font.setItalic(true);  // 是不斜体
//		font.setUnderline();
        font.setFontHeightInPoints((short)25);	// 设置字体大小
        font.setColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());	// 设置字体颜色
        font.setFontName("宋体");  // 设置字体名称
        style.setFont(font);

        return style;
    }

    /**
     * 创建小标题样式
     * @param workbook
     * @return
     */
    public static HSSFCellStyle createSubTitleStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = createBaseStyle(workbook);

//		设置字体
        HSSFFont font = workbook.createFont();
        font.setBold(true);  // 是否加粗
        font.setItalic(true);
        font.setFontHeightInPoints((short) 25);
        font.setFontName("宋体");
        style.setFont(font);

        return style;
    }

    /**
     * 创建标题样式
     * @param workbook
     * @return
     */
    public static HSSFCellStyle createTitleStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = createBaseStyle(workbook);

        HSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setItalic(true);
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 30);
        font.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        style.setFont(font);

        return style;
    }
}
