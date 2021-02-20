package com.java.carrent.utils;

import com.java.carrent.entity.BusCustomer;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 导入数据工具类
 */
public class ImportDataUtils {

    public static List<BusCustomer> importCustomerData(String path) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(path)));
        HSSFSheet sheet = workbook.getSheetAt(0);
        List<BusCustomer> customerList = new ArrayList<>();
        List<String> contents = null;

//        获取每行的数据
        for (Row row : sheet) {
            contents = new ArrayList<>();
//            获取每个单元格中的数据
            for (Cell cell : row) {

                if(cell != null) {
                    cell.setCellType(CellType.STRING);
                    if(!"".equals(cell.getStringCellValue())) {
                        contents.add(cell.getStringCellValue());
                    }
                }
            }

            if(contents.size() == 6) {
                BusCustomer customer = new BusCustomer(contents.get(0), contents.get(1), contents.get(2).equals("男") ? 1 : 0, contents.get(3), contents.get(4), contents.get(5));
                customerList.add(customer);
            }
        }

        return customerList;
    }

}
