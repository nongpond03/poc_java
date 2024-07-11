package com.example.Exporter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Service
public class ExcelHelper {
    public static List<Sheet> readExcel(ByteArrayOutputStream input) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(new ByteArrayInputStream(input.toByteArray()));
        List<Sheet> l = new ArrayList<>();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            l.add(workbook.getSheetAt(i));
        }
        return l;
    }

    public static void azzertEquals(Object[][] xData, Sheet sheet) {
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Object[] xRow = xData[i];
            for (int j = 0; j < xRow.length; j++) {
                Cell cell = row.getCell(j);
                Object expectedValue = xRow[j];
                Object actualValue = getCellValue(cell);
                assertEquals(expectedValue, actualValue, "Cell value mismatch at row " + (i + 1) + ", column " + (j + 1));
            }
        }
    }

    private static Object getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }

        if (cell.getCellType().equals(CellType.STRING)) {
            return cell.getStringCellValue();
        } else if (cell.getCellType().equals(CellType.NUMERIC)) {
            return cell.getNumericCellValue();
        } else {
            return null;
        }
    }

}
