package com.myfw.Utility;

import com.myfw.library.CommonLibrary;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

public class GetAndSetdatafromExcel {

    private FileInputStream fis;
    private FileOutputStream fos;
    private Workbook wb;
    private Sheet sh;
    private Row row;
    private Cell cell;

    public static String excelfilepath = System.getProperty("user.dir") + "/InputData/Dosh_TestData.xls";

    private final Map<String, Integer> map = new HashMap<>();

    public void setExcelFile(String sheetName) throws Exception {

        try {
             fis = new FileInputStream(excelfilepath);
            wb = WorkbookFactory.create(fis);
            sh = wb.getSheet(sheetName);

            sh.getRow(0).forEach(cell -> {
                map.put(cell.getStringCellValue(), cell.getColumnIndex());
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Excel File not found");

        }
    }

    public String getCellData(int rownum, int colnum) throws IOException {
        String cellData = null;
        try {
            cell = sh.getRow(rownum).getCell(colnum);
            cellData = cell.getStringCellValue();
            //fis.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            {
                fis.close();
            }
        }
        return cellData;
    }

    public String getCellData(String colName, int rownum) throws IOException {
        return getCellData(rownum, map.get(colName));
    }

    public void setCellData(String iteration,String colName,String value) throws IOException {
        int rowNum = Integer.parseInt(iteration);
        try {
            fos = new FileOutputStream(excelfilepath);
            row = sh.getRow(0);
            int colNum = 0;

            for(int i=0;i< row.getLastCellNum();i++){
                if(row.getCell(i).getStringCellValue().trim().equals(colName))
                {
                    colNum = i;
                }
            }
            row = sh.getRow(rowNum);
            if(row == null){
                row=sh.createRow(rowNum);
            }
            cell = row.getCell(colNum);
            if(cell == null){
                cell = row.createCell(colNum);
            }
           cell.setCellValue(value);
            wb.write(fos);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            fos.flush();
            fos.close();
        }
    }

    public static void main(String args[]) throws Exception {
       // GetAndSetdatafromExcel g =new GetAndSetdatafromExcel();
       // g.setExcelFile("eBMSTestData");
       // g.setCellData("2","Password","12345");
        String arg0 ="1";
        String containerType = "CHE/firmQTN/22/06/00007";//CommonLibrary.getXlsTestData(sheetName, "Container Type", arg0);
        String sheetName = "Task_IDs";
        String enquiryID= CommonLibrary.getXlsTestData(sheetName,"Quotation ID",arg0);
        String sbEnquiryID = enquiryID.substring(5,8).toLowerCase(Locale.ROOT);
            System.out.println(enquiryID.replace(enquiryID.substring(5,8),enquiryID.substring(5,8).toLowerCase()));
        enquiryID = enquiryID.replace(enquiryID.substring(5,8),sbEnquiryID);

        System.out.println(sbEnquiryID + "++++" +enquiryID);


    }
}

