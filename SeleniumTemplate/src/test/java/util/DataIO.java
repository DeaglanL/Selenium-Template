package util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataIO {

    private DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private Workbook wB;


    public void readSpreadSheet(String filePath)
    {
        try {
            FileInputStream excelFile = new FileInputStream(new File(filePath));
            wB = new XSSFWorkbook(excelFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String> readRow(int rowNum, String sheetName)
    {
        ArrayList<String> rowContents = new ArrayList<String>();
        Sheet dataTypeSheet =  wB.getSheet(sheetName);
        Row currentRow =   dataTypeSheet.getRow(rowNum);

        for (Cell currentCell : currentRow)
        {
            switch (currentCell.getCellTypeEnum())
            {
                case STRING:
                    rowContents.add(currentCell.getStringCellValue());
                    break;
                case NUMERIC:
                    rowContents.add(String.valueOf(currentCell.getNumericCellValue()));
                    break;
                case BOOLEAN:
                    rowContents.add(String.valueOf(currentCell.getBooleanCellValue()));
                    break;
                case BLANK:
                    rowContents.add(currentCell.getStringCellValue());
                    break;
                case _NONE:
                    System.out.println("No Value in cell");
                    break;
                case ERROR:
                    System.out.println("Error in cell");
                    break;
                case FORMULA:
                    rowContents.add(currentCell.getStringCellValue());
                    break;
            }
        }
        return rowContents;
    }


    public void writeToSpreadSheet(String filePath, Object[][] contents)
    {
        Date date = new Date();
        Sheet sheet = wB.createSheet("Output" + sdf.format(date));

        int rowNum = 0;

        for (Object[] object : contents) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : object) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            wB.write(outputStream);
            wB.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
