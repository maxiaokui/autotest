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
		//�ҵ�һ��excel���
		XSSFWorkbook excelvalue = new XSSFWorkbook(this.fileName);
		return excelvalue;
	}
	public XSSFSheet getsheet(int sheetIndex) throws Exception{
		XSSFWorkbook excelvalue =getExcel();
		//ָ��һ����ǰ����sheetҳ
		XSSFSheet sheetvalue =  excelvalue.getSheetAt(sheetIndex);
		return sheetvalue;
	}
	public Object getValue(int sheetIndex,int rowIndex,int cellIndex) throws Exception{
		Object value = null;
		XSSFSheet sheetvalue =getsheet(sheetIndex);
		//ָ����ǰsheet��ĳһ��
		XSSFRow rowvalue = sheetvalue.getRow(rowIndex);
		//ָ����ǰ�е�ĳһ����Ԫ��
		XSSFCell cellvalue = rowvalue.getCell(cellIndex);
		//��ȡ�˵�Ԫ������� ö��ֵ �п� ���� �ַ���  ���ʽ �ո� ��
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
		//�õ�һ��sheet
		XSSFSheet sheet = getsheet(sheetIndex);
		//�õ����sheet�����һ��
		int lastRomnum = sheet.getLastRowNum();
		//����һ����ά����
		Object[][] caseDate = new Object[lastRomnum][10];
		//����ÿһ�еı���
		for(int rowIndex =1;rowIndex<=lastRomnum;rowIndex++){
			XSSFRow row = sheet.getRow(rowIndex);
			if(row == null){
				continue;
			}
			//��ǰ�е�ÿһ����Ԫ����б���
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
		ExcelUtil6 excel =new ExcelUtil6("E:\\̫ԭ�����.xlsx");
		Object value = excel.getValue(0, 1, 0);
		System.out.println(value.toString());*/
	}

}
