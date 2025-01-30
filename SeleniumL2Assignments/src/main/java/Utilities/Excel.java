package Utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	public Object[][] getExcelData(String filename, int sheetindex) {
		String[][] data =null;
		try {
			 
			FileInputStream fin = new FileInputStream(filename);
			XSSFWorkbook workbook = new XSSFWorkbook(fin);
			XSSFSheet sheet = workbook.getSheetAt(sheetindex);
			int noofrow = sheet.getLastRowNum()+1;
			int noofcol = sheet.getRow(0).getLastCellNum();
			System.out.println(noofrow+ " "+ noofcol );
			
			data = new String[noofrow-1][noofcol];
			
			for(int i=1;i<=noofrow-1;i++) {
				XSSFRow row = sheet.getRow(i);
				for(int j=0;j<noofcol;j++) {
					XSSFCell cell = row.getCell(j);
					data[i-1][j] = cell.getStringCellValue();
					System.out.println(data[i-1][j]);
	            }
	        }
	    } catch (Exception e) {
	        System.out.println("The exception is: " + e.getMessage());
	    }
		
		return data;
	}


		public String[] getExcelData1(String fileName, int sheetindex)  {
		    String[] data = null;
		    try {
		        FileInputStream fis = new FileInputStream(fileName);
		        XSSFWorkbook workbook = new XSSFWorkbook(fis);
		        XSSFSheet sheet = workbook.getSheetAt(sheetindex);
		        XSSFRow row = sheet.getRow(0);
		        int noOfRows = sheet.getPhysicalNumberOfRows();
		        int noOfCols = row.getLastCellNum();
		        Cell cell;
		        data = new String[noOfRows - 1];
		        for (int i = 1; i < noOfRows; i++) {
		            for (int j = 0; j < noOfCols; j++) {
		                row = sheet.getRow(i);
		                cell = row.getCell(j);
		                data[i - 1] = cell.getStringCellValue();
		            }
		        }
		    } catch (Exception e) {
		        System.out.println("The exception is: " + e.getMessage());
		    }
		    return data;
		    }
	}

