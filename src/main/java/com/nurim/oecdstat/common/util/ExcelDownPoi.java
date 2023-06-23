package com.nurim.oecdstat.common.util;


import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.nurim.oecdstat.common.bean.CommonBean;

public class ExcelDownPoi {

	// POI Excel DownLoad Method - Basic
	// ex_headers : Header Text
	// ex_contents : Field name (case Sensitive)
	public void ExcelDownLoad(List<? extends CommonBean> list, String[] ex_headers, String[] ex_contents, String xls_file_name, String xls_title, HttpServletResponse response){
		Workbook wb = null;
		String ext = "";

		wb = new HSSFWorkbook();
		ext = ".xls";

		CellStyle _title = this.createCellStyle(wb, BorderStyle.THIN, true, (short)15,HSSFColorPredefined.WHITE.getIndex() , HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
		CellStyle _header = this.createCellStyle(wb, BorderStyle.THIN, false, (short)10,HSSFColorPredefined.LIGHT_GREEN.getIndex() , HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
		CellStyle _content_c = this.createCellStyle(wb, BorderStyle.THIN, false, (short)10,HSSFColorPredefined.WHITE.getIndex() , HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
		CellStyle _content_l = this.createCellStyle(wb, BorderStyle.THIN, false, (short)10,HSSFColorPredefined.WHITE.getIndex() , HorizontalAlignment.LEFT, VerticalAlignment.CENTER);

		Sheet sheet = wb.createSheet(xls_file_name);
		Row row = null;
		Cell cell = null;
		int rowNo = 0;

		//맨 윗줄 파일명
		row = sheet.createRow(rowNo++);
		cell = row.createCell(0);
		cell.setCellStyle(_title);
		cell.setCellValue(xls_title);
		row = sheet.createRow(rowNo++);
		sheet.addMergedRegion(new CellRangeAddress(0,1,0,ex_headers.length-1));

		if(list != null && list.size()>0) {
			row = sheet.createRow(rowNo++);
			for (int i = 0; i < ex_headers.length;  i++){
				cell = row.createCell(i);
				cell.setCellStyle(_header);
				cell.setCellValue(ex_headers[i]);
			}

			try {
				Class<? extends CommonBean> cl = list.get(0).getClass();
				String str = "";
				for (int k = 0; k < list.size(); k++) {
					row = sheet.createRow(rowNo++);
					for (int i = 0; i < ex_contents.length ; i ++ ) {
						if (i == 0 && ex_contents[i].equals("Index")){
							str = ""+(k + 1);
						}else {
							Field cellData = null;
							try{
								cellData = cl.getDeclaredField(ex_contents[i]);
							}catch (Throwable e){
								cellData = cl.getSuperclass().getDeclaredField(ex_contents[i]);
							}
							cellData.setAccessible(true);
							str =(""+cellData.get(list.get(k))).toString();
							if (str.equals(""+"null")){
								str = "";
							}
						}
						cell = row.createCell(i);
						if (ex_contents[i].toLowerCase().contains("subject") || ex_contents[i].toLowerCase().contains("content") || ex_contents[i].toLowerCase().contains("tbl_nm")|| ex_contents[i].toLowerCase().contains("tbl_id")) {
							cell.setCellStyle(_content_l);
						}else {
							cell.setCellStyle(_content_c);
						}

						//remove HTML Tags from String
						str = removeTag(str);

						//check if contents overs limit Length of a cell
						if (str.length() < 32767) {
							cell.setCellValue(str);
						}
					}	
				}
			}

			catch (Throwable e) {
			}

			for (int i = 0; i < ex_headers.length;  i++){
				sheet.autoSizeColumn(i);

				if(ex_contents[0].equals("dt2")){ //Quiz @ Force Cell Minimum Width 
					sheet.setColumnWidth(i,  7000);
				}else if (sheet.getColumnWidth(i) < 25000) { //Cell width + padding
					sheet.setColumnWidth(i,  sheet.getColumnWidth(i) + 1000);
				}else{//Force Cell Maximum Width 
					sheet.setColumnWidth(i,  25000);
				}
			}
			response.setHeader("Content-Description", "JSP Generated Data");

			try{
				response.setHeader("Content-Disposition", "attachment; filename="+new String(xls_file_name.getBytes("ksc5601"), "8859_1")+ext);
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}

			// 엑셀 출력
			try{
				wb.write(response.getOutputStream());
				wb.close();
			}
			catch(Exception e) {
				e.printStackTrace();
				response.setHeader("Set-Cookie", "fileDownload=false; path=/");
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
				response.setHeader("Content-Type", "text/html; charset=utf-8");
			}
		}
	}


	public void ExcelDownLoad_multiHeader(List<? extends CommonBean> list, String[][] ex_headers, String[] ex_contents, String xls_file_name, String xls_title, HttpServletResponse response){
		// 워크북 생성
		Workbook wb = null;
		String ext = "";

		wb = new HSSFWorkbook();
		ext = ".xls";

		CellStyle _title = this.createCellStyle(wb, BorderStyle.THIN, true, (short)15,HSSFColorPredefined.WHITE.getIndex() , HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
		CellStyle _header = this.createCellStyle(wb, BorderStyle.THIN, false, (short)15,HSSFColorPredefined.LIGHT_GREEN.getIndex() , HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
		CellStyle _content_c = this.createCellStyle(wb, BorderStyle.THIN, false, (short)15,HSSFColorPredefined.WHITE.getIndex() , HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
		CellStyle _content_l = this.createCellStyle(wb, BorderStyle.THIN, false, (short)15,HSSFColorPredefined.WHITE.getIndex() , HorizontalAlignment.LEFT, VerticalAlignment.CENTER);

		Sheet sheet = wb.createSheet(xls_file_name);
		Row row = null;
		Cell cell = null;
		int rowNo = 0;

		//맨 윗줄 파일명
		row = sheet.createRow(rowNo++);
		cell = row.createCell(0);
		cell.setCellStyle(_title);
		cell.setCellValue(xls_title);
		row = sheet.createRow(rowNo++);
		sheet.addMergedRegion(new CellRangeAddress(0,1,0,ex_headers.length-1));

		if(list != null && list.size()>0) {
			for (int k = 0; k < ex_headers.length; k ++) 
			{
				row = sheet.createRow(rowNo++);
				for (int i = 0; i < ex_headers[k].length;  i++)
				{
					cell = row.createCell(i);
					cell.setCellStyle(_header);
					cell.setCellValue(ex_headers[k][i]);
				}
			}

			try {
				Class<? extends CommonBean> cl = list.get(0).getClass();
				String str = "";
				for (int k = 0; k < list.size(); k++) {
					row = sheet.createRow(rowNo++);
					for (int i = 0; i < ex_contents.length ; i ++ ) {
						if (i == 0 && ex_contents[i].equals("Index")){
							str = ""+(k + 1);
						}else {
							Field cellData = cl.getDeclaredField(ex_contents[i]);
							cellData.setAccessible(true);
							str = (String)cellData.get(list.get(k));
						}

						cell = row.createCell(i);
						if (ex_contents[i].toLowerCase().contains("subject") || ex_contents[i].toLowerCase().contains("content") || ex_contents[i].toLowerCase().contains("tbl_nm")|| ex_contents[i].toLowerCase().contains("tbl_id")) {
							cell.setCellStyle(_content_l);
						}else {
							cell.setCellStyle(_content_c);
						}

						str = removeTag(str);

						if (str.length() < 32767) {	
							cell.setCellValue(str);
						}
					}	
				}
			}

			catch (Throwable e) {

			}

			for (int i = 0; i < ex_headers.length;  i++){
				sheet.autoSizeColumn(i);
				if(ex_contents[0].equals("dt2")) {
					sheet.setColumnWidth(i,  7000);
				}else if (sheet.getColumnWidth(i) < 25000) {
					sheet.setColumnWidth(i,  sheet.getColumnWidth(i) + 1000);
				}else {
					sheet.setColumnWidth(i,  25000);
				}
			}
			response.setHeader("Content-Description", "JSP Generated Data");

			try {
				response.setHeader("Content-Disposition", "attachment; filename="+new String(xls_file_name.getBytes("ksc5601"), "8859_1")+ext);
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}

			// 엑셀 출력
			try{
				wb.write(response.getOutputStream());
				wb.close();
			} catch(Exception e) {
				e.printStackTrace();
				response.setHeader("Set-Cookie", "fileDownload=false; path=/");
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
				response.setHeader("Content-Type", "text/html; charset=utf-8");
			}
		}
	}

	//remove HTML Tags from a string
	private String removeTag(String html) {
		return ((html.replaceAll("&nbsp;","  ")).replaceAll("</p>", "\r\n")).replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
	}

	//create a CellStyle
	private CellStyle createCellStyle(Workbook wb,BorderStyle border, Boolean isBold, Short size,  Short color,HorizontalAlignment ha, VerticalAlignment va ) {
		CellStyle rs = wb.createCellStyle();

		rs.setBorderTop(border);
		rs.setBorderBottom(border);
		rs.setBorderLeft(border);
		rs.setBorderRight(border);

		Font _font = wb.createFont();
		_font.setBold(isBold);
		_font.setFontHeightInPoints(size);

		rs.setFont(_font);

		rs.setAlignment(ha);
		rs.setVerticalAlignment(va);

		rs.setFillForegroundColor(color);
		rs.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		rs.setWrapText(true);
		return rs;
	}



}

