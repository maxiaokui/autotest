package com.youceedu.interf.util;


import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test1 {
	private String file;
	
	public Test1(String file) {
		this.file = file;
	}
	
	public XSSFWorkbook excelValue() throws Exception{
		XSSFWorkbook wb = new XSSFWorkbook(this.file);
		return wb;
	}
	
	public XSSFSheet sheetValue(int index) throws Exception{
		XSSFWorkbook wb = excelValue();
		XSSFSheet sheet = wb.getSheetAt(index);
		return sheet;
	}
	
	/*public XSSFCell cellValue(int index,int rowindex,int cellnum) throws Exception{
		XSSFSheet sheet = sheetValue(index);
		XSSFRow row = sheet.getRow(rowindex);
		XSSFCell cell =row.getCell(cellnum);
		//fromCellType(cell);
		return cell;
	}*/
	public Object fromCellType(XSSFCell cell){
		Object value = null;
		CellType celltype = cell.getCellTypeEnum();
		switch(celltype){
		case _NONE:
			break;
		case NUMERIC:
			value = cell.getNumericCellValue();
			break;
		case STRING:
			value = cell.getStringCellValue();
			break;
		case FORMULA:
			value = cell.getCellFormula();
			break;
		case BLANK:
			break;
		default:
			value = cell.getDateCellValue();
			break;
		}
		return value;
	}
	
	public Object [][] excelCellValue(int index) throws Exception{
		XSSFWorkbook wb = excelValue();
		XSSFSheet sheet =wb.getSheetAt(index);
		Object [][] cellValue=new Object [sheet.getLastRowNum()][16];
		//int i  = sheet.getFirstRowNum();
		for(int a=1;a<=sheet.getLastRowNum();a++){
			if(sheet.getRow(a)==null){
				break;
			}
			XSSFRow row = sheet.getRow(a);
			for(int b=0;b<row.getLastCellNum();b++){
				XSSFCell cell=row.getCell(b);
				cellValue[a-1][b]=fromCellType(cell);
			}
		}
		return cellValue;
	}
	public static void main(String[]args) throws Exception{
		Test1 excel = new Test1("E:\\太原意向金.xlsx");
		Object[][] value = excel.excelCellValue(0);
		System.out.println(value[5][2]);
		System.out.println(value[5][3]);
		System.out.println(value[5][4]);
		System.out.println(value[5][2]);
		/*
		Object cell =excel.cellValue(0, 0, 0);
		System.out.println(cell);
		*/

	}

}
