package com.pagesjaunes.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelManager {

    //--- Excel File Parameters -----------------
    private String filePath = (System.getProperty("user.dir")+"src/test/resources/excel_report");
    public void writeExcel(String filePath,String fileName,String sheetName,String[] dataToWrite) throws IOException {
        //Create an object of File class to open xlsx file
        String os = System.getProperty("os.name");
        File file = new File(filePath+"/"+fileName);;
        if(os.startsWith("W")){
            file =    new File(filePath+"\\"+fileName);
        }else {
            file =    new File(filePath+"/"+fileName);
        }
        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);
        Workbook guru99Workbook = null;
        //Find the file extension by splitting  file name in substring and getting only extension name
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        //Check condition if the file is xlsx file
        if(fileExtensionName.equals(".xlsx")){
            //If it is xlsx file then create object of XSSFWorkbook class
            guru99Workbook = new XSSFWorkbook(inputStream);
        }
        //Check condition if the file is xls file
        else if(fileExtensionName.equals(".xls")){
            //If it is xls file then create object of XSSFWorkbook class
            guru99Workbook = new HSSFWorkbook(inputStream);
        }
        //Read excel sheet by sheet name
        Sheet sheet = guru99Workbook.getSheet(sheetName);
        //Get the current count of rows in excel file
        int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
        //Get the first row from the sheet
        Row row = sheet.getRow(0);
        int position1 = -1; // line
        for (Row line : sheet) {       //browse lines
            for (Cell cell : line) {
                if (cell.getStringCellValue().equals(dataToWrite[0]) ){
                    position1 = cell.getRowIndex();
                } //browse columns
            }
        }
        try {
            if (position1 != -1){
                Row newRow = sheet.createRow(position1);
                for(int j = 0; j < row.getLastCellNum(); j++){
                    //Fill data in row
                    Cell cell = newRow.createCell(j);
                    cell.setCellValue(dataToWrite[j]);
                }
            } else {
                Row newRow = sheet.createRow(rowCount+1);
                //Create a loop over the cell of newly created Row
                for(int j = 0; j < row.getLastCellNum(); j++){
                    //Fill data in row
                    Cell cell = newRow.createCell(j);
                    cell.setCellValue(dataToWrite[j]);
                }
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        //Close input stream
        inputStream.close();
        //Create an object of FileOutputStream class to create write data in excel file
        FileOutputStream outputStream = new FileOutputStream(file);
        //write data in the excel file
        guru99Workbook.write(outputStream);
        //close output stream
        outputStream.close();
    }
    public void excelWriting(String nbrBug, String bugStatut, String valueDate, String valueTime ) throws IOException {
        String[] valueToWrite = {nbrBug, bugStatut, valueDate, valueTime};
        ExcelManager objExcelFile = new ExcelManager();
        objExcelFile.writeExcel(filePath,"Verification.xlsx","Data",valueToWrite);
    }
}