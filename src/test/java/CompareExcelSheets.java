import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CompareExcelSheets {
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

            // Get the number of rows in each sheet
            int rows1 = sheet1.getPhysicalNumberOfRows();
            int rows2 = sheet2.getPhysicalNumberOfRows();
            int rows = Math.min(rows1, rows2);

            // Compare the sheets row by row and cell by cell
            for (int i = 0; i < rows; i++) {
                Row row1 = sheet1.getRow(i);
                Row row2 = sheet2.getRow(i);
                
                // Handle cases where one row is null
                if (row1 == null || row2 == null) {
                    System.out.println("Difference found at Row " + (i + 1) + ": One row is null.");
                    continue;
                }

                // Get the number of cells in each row
                int cells1 = row1.getPhysicalNumberOfCells();
                int cells2 = row2.getPhysicalNumberOfCells();
                int maxCells = Math.max(cells1, cells2);

                // Compare the cells in each row
                for (int j = 0; j < maxCells; j++) {
                    Cell cell1 = row1.getCell(j);
                    Cell cell2 = row2.getCell(j);
                    
                    // Handle cases where one cell is null
                    if (cell1 == null || cell2 == null) {
                        System.out.println("Difference found at Row " + (i + 1) + ", Column " + (j + 1) + ": One cell is null.");
                        continue;
                    }

                    // Compare cell values
                    if (!cell1.toString().equals(cell2.toString())) {
                        System.out.println("Difference found at Row " + (i + 1) + ", Column " + (j + 1) + ":");
                        System.out.println("Value in MasterLoan.xlsx: " + cell1.toString());
                        System.out.println("Value in MendetoryLoad.xlsx: " + cell2.toString());
                    }
                }
            }

            // Close workbooks and file streams
            workbook1.close();
            workbook2.close();
            file1.close();
            file2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
