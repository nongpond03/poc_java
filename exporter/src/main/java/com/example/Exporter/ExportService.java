package com.example.Exporter;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class ExportService {
    public ByteArrayOutputStream convertEntitiesToExcel(String filename, List<?> entities) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(filename);

        createHeader(sheet, entities);
        createDataRow(sheet, entities);
        autoSizeColumn(sheet, entities);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return outputStream;
    }

    private void createHeader(Sheet sheet, List<?> entities) {
        Row headerRow = sheet.createRow(0);
        if (entities.isEmpty()) {
            return;
        }

        Object firstEntity = entities.get(0);
        Field[] fields = firstEntity.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            Cell headerCell = headerRow.createCell(i);
            headerCell.setCellValue(field.getName());
        }
    }

    private void createDataRow(Sheet sheet, List<?> entities) {
        for (int i = 0; i < entities.size(); i++) {
            Object entity = entities.get(i);
            Row row = sheet.createRow(i + 1);
            Field[] fields = entity.getClass().getDeclaredFields();
            for (int j = 0; j < fields.length; j++) {
                Field field = fields[j];
                field.setAccessible(true);
                try {
                    Object value = field.get(entity);
                    Cell cell = row.createCell(j);
                    if (value == null) {
                        continue;
                    }
                    if (value instanceof BigDecimal) {
                        cell.setCellValue(((BigDecimal) value).doubleValue());
                    } else if (value instanceof LocalDateTime) {
                        cell.setCellValue(((LocalDateTime) value).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                    } else {
                        cell.setCellValue(value.toString());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error accessing field value", e);
                }
            }
        }
    }

    private void autoSizeColumn(Sheet sheet, List<?> entities) {
        if (entities.isEmpty()) {
            return;
        }
        Object firstEntity = entities.get(0);
        Field[] fields = firstEntity.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}
