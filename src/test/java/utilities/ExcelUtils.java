package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private Workbook workbook;
    private org.apache.poi.ss.usermodel.Sheet sheet;
    private String filePath;

    // Constructor - loads workbook
    public ExcelUtils(String filePath, String sheetName) throws IOException {
        this.filePath = filePath;
        FileInputStream fis = new FileInputStream(filePath);
        this.workbook = new XSSFWorkbook(fis);
        this.sheet = workbook.getSheet(sheetName);
        fis.close();
    }

    // Get cell data by row and column index
    public String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        if (row == null) return "";
        Cell cell = row.getCell(colNum);
        if (cell == null) return "";

        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    // Set cell data
    public void setCellData(int rowNum, int colNum, String value) throws IOException {
        Row row = sheet.getRow(rowNum);
        if (row == null) row = sheet.createRow(rowNum);

        Cell cell = row.getCell(colNum);
        if (cell == null) cell = row.createCell(colNum);

        cell.setCellValue(value);

        // Write changes to the file
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        }
    }

    // Get total row count
    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    // Get total column count (based on the first row)
    public int getColumnCount() {
        Row row = sheet.getRow(0);
        return row == null ? 0 : row.getPhysicalNumberOfCells();
    }

    // Read all data from sheet
    public List<List<String>> readAllData() {
        List<List<String>> data = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        for (Row row : sheet) {
            List<String> rowData = new ArrayList<>();
            for (Cell cell : row) {
                rowData.add(formatter.formatCellValue(cell));
            }
            data.add(rowData);
        }

        return data;
    }

    // Close workbook
    public void close() throws IOException {
        workbook.close();
    }
}
