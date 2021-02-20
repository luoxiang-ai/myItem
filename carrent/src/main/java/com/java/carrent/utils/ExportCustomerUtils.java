package com.java.carrent.utils;

import com.java.carrent.entity.BusCustomer;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 导出工具类
 */
public class ExportCustomerUtils {


    public static ByteArrayOutputStream exportCustomer(List<BusCustomer> customerList, String sheetName) {
//        组装excel文档
//        1.创建工作
        HSSFWorkbook workbook = new HSSFWorkbook();
//        2.创建样式
        HSSFCellStyle baseStyle = ExportHSSFCellStyle.createBaseStyle(workbook);
        HSSFCellStyle subTitleStyle = ExportHSSFCellStyle.createSubTitleStyle(workbook);
        HSSFCellStyle tableTitleStyle = ExportHSSFCellStyle.createTableTitleStyle(workbook);
        HSSFCellStyle titleStyle = ExportHSSFCellStyle.createTitleStyle(workbook);

        HSSFSheet sheet = workbook.createSheet(sheetName);
//        4.设置sheet
        sheet.setDefaultColumnWidth(25);
//        合并
        CellRangeAddress region1 = new CellRangeAddress(0, 0, 0, 6);
        CellRangeAddress region2 = new CellRangeAddress(1, 1, 0, 6);

//        6.创建第一行
        int index = 0;
        HSSFRow row1 = sheet.createRow(index);
//        6.1在第一行里面创建一个单元格
        HSSFCell cell1 = row1.createCell(0);
//        6.2设置标题样式
        cell1.setCellStyle(titleStyle);
//        6.3设置单元格内容
        cell1.setCellValue("客户数据列表");

//        7.第二行
        index++;
        HSSFRow row2 = sheet.createRow(index);
        HSSFCell cell2 = row2.createCell(0);
        cell2.setCellStyle(subTitleStyle);
        cell2.setCellValue("总条数：" + customerList.size() + "  导出时间" + LocalDateTime.now());

//        第三行
        String[] titles = { "身份证号", "客户姓名", "客户电话", "客户地址", "客户职位", "性别", "录入时间" };
        index++;
        HSSFRow row3 = sheet.createRow(index);
        for (int i = 0; i < titles.length; i++) {
            HSSFCell row3_cell = row3.createCell(i);
            row3_cell.setCellStyle(tableTitleStyle);
            row3_cell.setCellValue(titles[i]);
        }

        //9第四行
        for (int i = 0; i < customerList.size(); i++) {
            index++;
            BusCustomer customer=customerList.get(i);
            HSSFRow row = sheet.createRow(index);
            //9.1创建列身份证号
            HSSFCell row_identity = row.createCell(0);
            row_identity.setCellStyle(baseStyle);
            row_identity.setCellValue(customer.getIdentity());
            //9.2创建列客户姓名
            HSSFCell row_custname = row.createCell(1);
            row_custname.setCellStyle(baseStyle);
            row_custname.setCellValue(customer.getCustname());
            //9.3创建列客户电话
            HSSFCell row_phone = row.createCell(2);
            row_phone.setCellStyle(baseStyle);
            row_phone.setCellValue(customer.getPhone());
            //9.4创建列客户地址
            HSSFCell row_address= row.createCell(3);
            row_address.setCellStyle(baseStyle);
            row_address.setCellValue(customer.getAddress());
            //9.5创建列客户职位
            HSSFCell row_career = row.createCell(4);
            row_career.setCellStyle(baseStyle);
            row_career.setCellValue(customer.getCareer());
            //9.6创建列性别
            HSSFCell row_sex = row.createCell(5);
            row_sex.setCellStyle(baseStyle);
            row_sex.setCellValue(customer.getSex()==1?"男":"女");
            //9.7创建列录入时间
            HSSFCell row_createtime = row.createCell(6);
            row_createtime.setCellStyle(baseStyle);
            row_createtime.setCellValue(customer.getCreatetime().toLocaleString());
        }
        //到此excel组装完成

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        把workbook里面的数据写到outputStream中
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputStream;
    }
}
