package com.chinamobile.cmss.bcse.evaluate.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.util.CollectionUtils;

import com.chinamobile.cmss.bcse.evaluate.utils.annotation.ColType;
import com.chinamobile.cmss.bcse.evaluate.utils.annotation.ExcelAnnotationReader;
import com.chinamobile.cmss.bcse.evaluate.utils.annotation.ExcelCol;


/**
 * ClassName:ExcelUtil.java
 * Description:Excel数据读取工具类，POI实现，兼容Excel2003，及Excel2007
 **/
public class ExcelUtil<T> {
	private OutputStream out;		//输出
	
	private Workbook wb = null;
	private ExcelAnnotationReader annReader;
	private List<String[]> dataList; 
	private Integer errorNum = 0;		//初步解析问题数
	private String template;			//模版路径
	
	/**
	 * 读EXCEL
	 * @param path 服务器文件路径
	 * @param annReader 解析reader
	 * @throws Exception
	 */
	public ExcelUtil(String path, ExcelAnnotationReader annReader) throws Exception {
		this.annReader = annReader;
		InputStream inp = new FileInputStream(path);
		wb = WorkbookFactory.create(inp);
	}
	
	/**
	 * 写EXCEL
	 * @param out 输出流
	 * @param annReader
	 * @param template 模板路径
	 * @throws Exception
	 */
	public ExcelUtil(OutputStream out, ExcelAnnotationReader annReader, String template) throws Exception{
		this.out = out;
		this.annReader = annReader;
		this.template = template;
		if(StringUtils.isNotEmpty(template)) {
			wb = WorkbookFactory.create(new FileInputStream(template));
		} else {
			wb = new HSSFWorkbook();
		}
	}
	
	
	
	private Object convert(Field field, String cellValue) {
		if(field == null) {
			return cellValue;
		}
		
		Class<?> type = field.getType();
		if (type.equals(int.class) || type.equals(Integer.class)) {
            return StringUtils.convertToInt(cellValue);
        }
        if (type.equals(long.class) || type.equals(Long.class)) {
            return StringUtils.convertToLong(cellValue);
        }
        if (type.equals(float.class) || type.equals(Float.class)) {
            return StringUtils.convertToFloat(cellValue);
        }
        if (type.equals(double.class) || type.equals(Double.class)) {
            return StringUtils.convertToDouble(cellValue);
        }
//        if(type.equals(Date.class) || type.equals(Timestamp.class)) {
//        	return DateUtil.stringToTimestamp(cellValue);
//        } 
		
		return cellValue;
	}

	/**
	 * 导出
	 * @throws Exception 
	 */
	public void export(List<? extends Object> list) throws Exception {
		Map<Integer, Map<String, Annotation>> annMap = annReader.getAnnotationMap();
		Sheet sheet = wb.createSheet("sheet");
		// 文件头字体
		Font font0 = createFonts(wb, Font.BOLDWEIGHT_BOLD, "宋体", false, (short) 200);
		Font font1 = createFonts(wb, Font.BOLDWEIGHT_NORMAL, "宋体", false, (short) 200);
		
		CellStyle cellStyle = wb.createCellStyle();   
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_BOTTOM);
		cellStyle.setFont(font0);
		
		int rownum = 0;
		Row row = null;
		Row row1 = null;
		if(StringUtils.isEmpty(getTemplate())) {
			row = sheet.createRow(rownum);
			row1 = sheet.createRow(++ rownum);
			// 合并第一行的单元格
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, annMap.size()));     
			createCell(wb, row, 0, annReader.getDesc(), cellStyle);
			
			cellStyle = wb.createCellStyle();      
			cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_BOTTOM);
			cellStyle.setFont(font1);
			
			createCell(wb, row1, 0, "序号", cellStyle);  
			for (Map.Entry<Integer, Map<String, Annotation>> entry : annMap.entrySet()) {
				createCell(wb, row1, entry.getKey(), ((ExcelCol)entry.getValue().get(entry.getValue().keySet().iterator().next())).desc(), cellStyle);
			}
		} else { 
			rownum = annReader.getStart();
		}

		if(CollectionUtils.isEmpty(list)){
			wb.write(out);
			free();
		}
		for(Object t : list) {
			row1 = sheet.createRow(++rownum);  
			createCell(wb, row1, 0, rownum - 1 + "", cellStyle);  
			for (Map.Entry<Integer, Map<String, Annotation>> entry : annMap.entrySet()) {
//				createCell(wb, row1, entry.getKey(), StringUtils.toString(PropertyUtils.getSimpleProperty(t, entry.getValue().keySet().iterator().next())), cellStyle);
				ExcelCol col = (ExcelCol) entry.getValue().values().iterator().next();	//获取导出实体类中的字段类型
				createCell(wb, row1, entry.getKey(), PropertyUtils.getSimpleProperty(t, entry.getValue().keySet().iterator().next()), col.type(), cellStyle);
			}
		}
		wb.write(out);
		free();
	}
	
	private void createCell(Workbook wb, Row row, int column, Object value, ColType colType, CellStyle cellStyle) {
		if(value==null){
			value = "";
		}
		Cell cell = row.createCell(column);
		if(colType == ColType.NUMBER){			//如果是数字类型，那么就在导出excel中的对应单元格，输出数字
			cell.setCellValue(Double.parseDouble(value.toString()));
		}else{
			cell.setCellValue(value.toString());
		}
		cell.setCellStyle(cellStyle);
	}

	public void free() {
		try {
			if (out != null) {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 返回Excel最大行index值，实际行数要加1
	 * 
	 * @return
	 */
	public int getRowNum(int sheetIndex) {
		Sheet sheet = wb.getSheetAt(sheetIndex);
		return sheet.getLastRowNum();
	}

	/**
	 * 返回数据的列数
	 * 
	 * @return
	 */
	public int getColumnNum(int sheetIndex) {
		Sheet sheet = wb.getSheetAt(sheetIndex);
		Row row = sheet.getRow(0);
		if (row != null && row.getLastCellNum() > 0) {
			return row.getLastCellNum();
		}
		return 0;
	}

	/**
	 * 获取某一行数据
	 * 
	 * @param rowIndex
	 *            计数从0开始，rowIndex为0代表header行
	 * @return
	 */
	public String[] getRowData(int sheetIndex, int rowIndex) {
		String[] dataArray = null;
		if (rowIndex > this.getColumnNum(sheetIndex)) {
			return dataArray;
		} else {
			dataArray = new String[this.getColumnNum(sheetIndex)];
			return this.dataList.get(rowIndex);
		}

	}

	/**
	 * 获取某一列数据
	 * 
	 * @param colIndex
	 * @return
	 */
	public String[] getColumnData(int sheetIndex, int colIndex) {
		String[] dataArray = null;
		if (colIndex > this.getColumnNum(sheetIndex)) {
			return dataArray;
		} else {
			if (this.dataList != null && this.dataList.size() > 0) {
				dataArray = new String[this.getRowNum(sheetIndex) + 1];
				int index = 0;
				for (String[] rowData : dataList) {
					if (rowData != null) {
						dataArray[index] = rowData[colIndex];
						index++;
					}
				}
			}
		}
		return dataArray;
	}
	
	/**
	 * 创建单元格并设置样式,值
	 * 
	 * @param wb
	 * @param row
	 * @param column
	 * @param
	 * @param
	 * @param value
	 */
	public static void createCell(Workbook wb, Row row, int column, String value, CellStyle cellStyle) {
		Cell cell = row.createCell(column);
		cell.setCellValue(value);
		cell.setCellStyle(cellStyle);
	}
	
	/**
	 * 设置字体
	 * 
	 * @param wb
	 * @return
	 */
	public static Font createFonts(Workbook wb, short bold, String fontName, boolean isItalic, short hight) {
		Font font = wb.createFont();
		font.setFontName(fontName);
		font.setBoldweight(bold);
		font.setItalic(isItalic);
		font.setFontHeight(hight);
		return font;
	}


	public Integer getErrorNum() {
		return errorNum;
	}

	public void setErrorNum(Integer errorNum) {
		this.errorNum = errorNum;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}
	
	/**
	 * 导出评测体系结果，总分放在最上面
	 * @throws Exception 
	 */
	public void exportEx(List<? extends Object> list) throws Exception {
		Map<Integer, Map<String, Annotation>> annMap = annReader.getAnnotationMap();
		Sheet sheet = wb.createSheet("sheet");
		sheet.setDefaultColumnWidth(15);
		// 文件头字体
		Font font0 = createFonts(wb, Font.BOLDWEIGHT_BOLD, "宋体", false, (short) 200);
		Font font1 = createFonts(wb, Font.BOLDWEIGHT_NORMAL, "宋体", false, (short) 200);
		
		CellStyle cellStyle = wb.createCellStyle();   
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_BOTTOM);
		cellStyle.setFont(font0);
		
		int rownum = 0;
		Row row = null;
		Row row1 = null;
		Row rowTotalPoint = null;
		if(StringUtils.isEmpty(getTemplate())) {
			row = sheet.createRow(rownum);
			rowTotalPoint = sheet.createRow(++ rownum);
			row1 = sheet.createRow(++ rownum);
			// 合并第一行的单元格
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, annMap.size()));     
			createCell(wb, row, 0, annReader.getDesc(), cellStyle);
			createCell(wb, rowTotalPoint, 0, "平均得分", cellStyle);
			
			cellStyle = wb.createCellStyle();      
			cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_BOTTOM);
			cellStyle.setFont(font1);
			
			createCell(wb, row1, 0, "序号", cellStyle);  
			for (Map.Entry<Integer, Map<String, Annotation>> entry : annMap.entrySet()) {
				createCell(wb, row1, entry.getKey(), ((ExcelCol)entry.getValue().get(entry.getValue().keySet().iterator().next())).desc(), cellStyle);
			}
		} else { 
			rownum = annReader.getStart();
		}

		if(CollectionUtils.isEmpty(list)){
			wb.write(out);
			free();
		}
		for(int i = 0 ; i < list.size() ; i ++) {
			Object t = list.get(i);
			if(i == 0){
				for (Map.Entry<Integer, Map<String, Annotation>> entry : annMap.entrySet()) {
					ExcelCol col = (ExcelCol) entry.getValue().values().iterator().next();	//获取导出实体类中的字段类型
					createCell(wb, rowTotalPoint, entry.getKey(), PropertyUtils.getSimpleProperty(t, entry.getValue().keySet().iterator().next()), col.type(), cellStyle);
				}
				continue;
			}
			row1 = sheet.createRow(++rownum);  
			createCell(wb, row1, 0, rownum - 2 + "", cellStyle);  
			for (Map.Entry<Integer, Map<String, Annotation>> entry : annMap.entrySet()) {
//				createCell(wb, row1, entry.getKey(), StringUtils.toString(PropertyUtils.getSimpleProperty(t, entry.getValue().keySet().iterator().next())), cellStyle);
				ExcelCol col = (ExcelCol) entry.getValue().values().iterator().next();	//获取导出实体类中的字段类型
				createCell(wb, row1, entry.getKey(), PropertyUtils.getSimpleProperty(t, entry.getValue().keySet().iterator().next()), col.type(), cellStyle);
			}
		}
		wb.write(out);
		free();
	}
	
}
