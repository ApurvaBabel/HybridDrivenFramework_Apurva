package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperations {

	public static Object[][] readExcelData(String inputFile, String sheetName) throws IOException {
		File file = new File(inputFile);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(inputStream);

		Sheet sheet = wb.getSheet(sheetName);
		int totalRow = sheet.getLastRowNum();
		int totalColumn = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[totalRow][totalColumn];

		for (int rowIndex = 0; rowIndex < totalRow; rowIndex++) {
			for (int columnIndex = 0; columnIndex < totalColumn; columnIndex++) {
				Cell cell = sheet.getRow(rowIndex + 1).getCell(columnIndex);
				if (cell.getCellType() == CellType.STRING) {
					data[rowIndex][columnIndex] = cell.getStringCellValue();
				} else if (cell.getCellType() == CellType.BOOLEAN) {
					data[rowIndex][columnIndex] = cell.getBooleanCellValue();
				} else if (cell.getCellType() == CellType.NUMERIC) {
					if (DateUtil.isCellDateFormatted(cell)) {
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						data[rowIndex][columnIndex] = cell.getDateCellValue();
					} else {
						data[rowIndex][columnIndex] = cell.getNumericCellValue();
					}
				}
			}
		}
		wb.close();
		return data;
	}
}
