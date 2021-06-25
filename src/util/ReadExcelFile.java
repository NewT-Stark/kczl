package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import dto.Teacher;

public class ReadExcelFile {
	// 总行数
	private int totalRows = 0;
	// 总条数
	private int totalCells = 0;
	// 错误信息接收器
	private String errorMsg;
	// 构造方法
	public ReadExcelFile() {
	}
	// 获取总行数
	public int getTotalRows() {
		return totalRows;
	}
	// 获取总列数
	public int getTotalCells() {
		return totalCells;
	}
	// 获取错误信息
	public String getErrorInfo() {
		return errorMsg;
	}
	/**
	 * 读EXCEL文件，获取信息集合
	 * @param fielName
	 * @return
	 */
	public List<Teacher> getExcelInfo(File mFile) {
		String fileName = mFile.getName();// 获取文件名
		List<Teacher> goodsList = null;
		try {
			if (!validateExcel(fileName)) {// 验证文件名是否合格
				return null;
			}
			boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
			if (isExcel2007(fileName)) {
				isExcel2003 = false;
			}
			goodsList = createExcel(mFile, isExcel2003);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodsList;
	}
	/**
	 * 根据excel里面的内容读取客户信息
	 * @param is输入流
	 * @param isExcel2003 excel是2003还是2007版本
	 * @return
	 * @throws IOException
	 */
	public List<Teacher> createExcel(File mFile, boolean isExcel2003) {
		List<Teacher> goodsList = null;
		try {
			Workbook wb = null;
			if (isExcel2003) {// 当excel是2003时,创建excel2003
				wb = new HSSFWorkbook(new FileInputStream(mFile));
			} else {// 当excel是2007时,创建excel2007
				wb = new XSSFWorkbook(mFile);
			}
			goodsList = readExcelValue(wb);// 读取Excel里面客户的信息
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodsList;
	}
	/**
	 * 读取Excel里面客户的信息
	 * @param wb
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private List<Teacher> readExcelValue(Workbook wb) {
		// 得到第一个shell
		Sheet sheet = wb.getSheetAt(0);
		// 得到Excel的行数
		this.totalRows = sheet.getPhysicalNumberOfRows();
		// 得到Excel的列数(前提是有行数)
		if (totalRows > 1 && sheet.getRow(0) != null) {
			this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		List<Teacher> goodsList = new ArrayList<Teacher>();
		// 循环Excel行数，第一行是表头，不需要读
		for (int r = 1; r < totalRows; r++) {
			Row row = sheet.getRow(r);
			if (row == null) {
				continue;
			}
			Teacher tea = new Teacher();
			// 循环Excel的列
			for (int c = 0; c < this.totalCells; c++) {
				Cell cell = row.getCell(c);
				cell.setCellType(CellType.STRING);
				if (null != cell) {
					if (c == 0) {
						tea.setName(cell.getStringCellValue());
					} else if (c == 1) {
						tea.setPassword(cell.getStringCellValue());
					} else if (c == 2) {
						tea.setSex(cell.getStringCellValue());
					} else if (c == 3) {
						tea.setContact(cell.getStringCellValue());
					} else if (c == 4) {
						tea.setTitle(cell.getStringCellValue());
					}
				}
			}
			// 添加到list
			goodsList.add(tea);
		}
		return goodsList;
	}
	/**
	 * 验证EXCEL文件
	 * @param filePath
	 * @return
	 */
	public boolean validateExcel(String filePath) {
		if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
			errorMsg = "文件名不是excel格式";
			return false;
		}
		return true;
	}
	/**
	 * 是否是2003的excel，返回true是2003
	 * @param filePath
	 * @return
	 */  
	public static boolean isExcel2003(String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$");
	}
	/**
	 * 是否是2007的excel，返回true是2007
	 * @param filePath
	 * @return
	 */
	public static boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}
}