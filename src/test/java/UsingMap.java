import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UsingMap {
    public static void main(String[] args) {
        try {
            FileInputStream file1 = new FileInputStream("MasterLoan.xlsx");
            FileInputStream file2 = new FileInputStream("MendetoryLoad.xlsx");

            // Create workbook instances
            XSSFWorkbook workbook1 = new XSSFWorkbook(file1);
            XSSFWorkbook workbook2 = new XSSFWorkbook(file2);

            // Assuming both files have only one sheet
            XSSFSheet sheet1 = workbook1.getSheetAt(0);
            XSSFSheet sheet2 = workbook2.getSheetAt(0);

            // Compare the sheets
            compareSheets(sheet1, sheet2);

            // Close workbooks and file streams
            workbook1.close();
            workbook2.close();
            file1.close();
            file2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void compareSheets(XSSFSheet sheet1, XSSFSheet sheet2) {
        Map<Integer, Map<Integer, String>> map1 = getSheetData(sheet1);
        Map<Integer, Map<Integer, String>> map2 = getSheetData(sheet2);

        // Compare maps
        for (Integer row : map1.keySet()) {
            Map<Integer, String> rowMap1 = map1.get(row);
            Map<Integer, String> rowMap2 = map2.get(row);
            if (rowMap2 == null) {
                System.out.println("Difference found at Row " + row + " in sheet2: Row not present in sheet1");
                continue;
            }
            for (Integer column : rowMap1.keySet()) {
                String value1 = rowMap1.get(column);
                String value2 = rowMap2.get(column);
                if (!value1.equals(value2)) {
                    System.out.println("Difference found at Row " + row + ", Column " + column + ":");
                    System.out.println("Value in MasterLoan.xlsx: " + value1);
                    System.out.println("Value in MendetoryLoad.xlsx: " + value2);
                }
            }
        }

        // Check for extra rows in sheet2
        for (Integer row : map2.keySet()) {
            if (!map1.containsKey(row)) {
                System.out.println("Difference found at Row " + row + " in sheet2: Row not present in sheet1");
            }
        }
    }

    private static Map<Integer, Map<Integer, String>> getSheetData(XSSFSheet sheet) {
        Map<Integer, Map<Integer, String>> map = new HashMap<>();
        int rowNum = 0;
        for (Row row : sheet) {
            Map<Integer, String> rowMap = new HashMap<>();
            int cellNum = 0;
            for (Cell cell : row) {
                rowMap.put(cellNum++, cell.toString());
            }
            map.put(rowNum++, rowMap);
        }
        return map;
    }
}
