import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateExcelSheet1 {
    public static void main(String[] args) {
        // Create a new workbook
        try (Workbook workbook = new XSSFWorkbook()) {
            // Create a sheet named "MendetoryLoad"
            Sheet mendetoryLoadSheet = workbook.createSheet("MendetoryLoad");

            // Create header row
            Row headerRow = mendetoryLoadSheet.createRow(0);
            String[] headers = {"ID", "Amount", "Status"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Sample data
            Object[][] data = {
                {101, 5000, "Active"},
                {102, 10000, "Active"},
                {103, 15000, "Closed"},
                {104, 20000, "Active"}
            };

            // Fill data rows
            for (int i = 0; i < data.length; i++) {
                Row row = mendetoryLoadSheet.createRow(i + 1);
                for (int j = 0; j < data[i].length; j++) {
                    Cell cell = row.createCell(j);
                    if (data[i][j] instanceof String) {
                        cell.setCellValue((String) data[i][j]);
                    } else if (data[i][j] instanceof Integer) {
                        cell.setCellValue((Integer) data[i][j]);
                    }
                }
            }

            // Write the workbook to a file
            try (FileOutputStream fileOut = new FileOutputStream("MendetoryLoad.xlsx")) {
                workbook.write(fileOut);
                System.out.println("Excel file created successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
