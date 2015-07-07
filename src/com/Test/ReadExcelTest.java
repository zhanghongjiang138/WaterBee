package com.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;



public class ReadExcelTest {
	
	public static final int FIRST=0;

	public static void main(String[] args) {
		HSSFWorkbook wb=null;
		String excelName="Spalding_BillingImportTemplate.xls";
		String dirPath="C:\\Users\\920643.TCSGEGDC\\Documents\\icam";
		String[] dirP=dirPath.split("\\\\");
		dirPath="";
		for(String s:dirP){
			dirPath=dirPath+s+File.separator;
		}
		File file=new File(dirPath+excelName);
		//System.out.println(dirPath+excelName);
/*		BufferedReader br=null;
		try {
			 br=new BufferedReader(
					new InputStreamReader(
							new FileInputStream(file),"utf8"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String line="";
		try {
			while(br.read()!=-1){
				System.out.println(br.readLine());
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		*/
		
		POIFSFileSystem poi=null;
		try {
			 poi=new POIFSFileSystem(new FileInputStream(file)) ;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			 wb =  new HSSFWorkbook(poi);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet sheet=wb.getSheetAt(0);
		
		
		int lastRowNum=sheet.getLastRowNum();
		int firstRow=sheet.getFirstRowNum();
		int columnWidth=sheet.getColumnWidth(1);
		//int firstRow=FIRST;
		int startColumn=FIRST;
		System.out.println(columnWidth);
		for(int i=firstRow;i<lastRowNum;i++){
			Row row=sheet.getRow(i);
			for(int j=startColumn;j<columnWidth;j++){
				System.out.println(row.getCell(j));
			}
		}
		

	}

}
