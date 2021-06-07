package com.youceedu.interf.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookType;

public class ExcelUtil6 {
	
	private String fileName;
	
	public ExcelUtil6(String fileName) {
		this.fileName = fileName;
	}
	public XSSFWorkbook getExcel() throws Exception{
		//找到一个excel表格
		XSSFWorkbook excelvalue = new XSSFWorkbook(this.fileName);
		return excelvalue;
	}
	public XSSFSheet getsheet(int sheetIndex) throws Exception{
		XSSFWorkbook excelvalue =getExcel();
		//指定一个当前表格的sheet页
		XSSFSheet sheetvalue =  excelvalue.getSheetAt(sheetIndex);
		return sheetvalue;
	}
	public Object getValue(int sheetIndex,int rowIndex,int cellIndex) throws Exception{
		Object value = null;
		XSSFSheet sheetvalue =getsheet(sheetIndex);
		//指定当前sheet的某一行
		XSSFRow rowvalue = sheetvalue.getRow(rowIndex);
		//指定当前行的某一个单元格
		XSSFCell cellvalue = rowvalue.getCell(cellIndex);
		//获取此单元格的类型 枚举值 有空 数字 字符串  表达式 空格 等
		value = getCell(cellvalue);
		
		return value;
	}
	
	public Object getCell(XSSFCell cellvalue){
		Object value = null;
		CellType celltype =cellvalue.getCellTypeEnum();
		switch(celltype){
		case _NONE:
			value = "";
			break;
		case NUMERIC:
			value = cellvalue.getNumericCellValue();
			break;
		case STRING:
			value = cellvalue.getStringCellValue();
			break;
		case FORMULA:
			value = cellvalue.getCellFormula();
			break;
		case BLANK:
			value="";
			break;
		case BOOLEAN:
			value = cellvalue.getBooleanCellValue();
			break;
		case ERROR:
			value = "ERROR";
			break;
		default:
			value = cellvalue.getDateCellValue();
			break;
		}
		return value;
	}
	
	public Object[][] fromCellTypeGetCellValue(int sheetIndex) throws Exception{
		//得到一个sheet
		XSSFSheet sheet = getsheet(sheetIndex);
		//得到这个sheet中最后一行
		int lastRomnum = sheet.getLastRowNum();
		//定义一个二维数组
		Object[][] caseDate = new Object[lastRomnum][10];
		//进行每一行的编列
		for(int rowIndex =1;rowIndex<=lastRomnum;rowIndex++){
			XSSFRow row = sheet.getRow(rowIndex);
			if(row == null){
				continue;
			}
			//当前行的每一个单元格进行遍历
			for(int cellIndex=0;cellIndex<row.getLastCellNum();cellIndex++){
				XSSFCell cellvalue = row.getCell(cellIndex);
				Object value = getCell(cellvalue);
				caseDate[rowIndex-1][cellIndex]=value;
			}
		}
		return caseDate;
	}
	public static void main(String[]args) throws Exception{
		
		ExcelUtil6 excel =new ExcelUtil6("E:\\test.xlsx");
		Object[][] value = excel.fromCellTypeGetCellValue(0);
		System.out.println(value[0][1]);
		System.out.println(value[0][5]);
		
		/*
		ExcelUtil6 excel =new ExcelUtil6("E:\\太原意向金.xlsx");
		Object value = excel.getValue(0, 1, 0);
		System.out.println(value.toString());*/
	}

}
