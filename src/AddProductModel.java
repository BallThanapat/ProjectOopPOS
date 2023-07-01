import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JTable;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AddProductModel {
    public void AddNewData(String name, double cost, double price, int amount, String imagePath) {
        File file = new File("data.xlsx");
        try (FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);){
            // Get the first sheet
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            
            // Create a new row at index 5
            Row row = sheet.createRow(lastRowNum+1);
            
            // Write data to the cells in the new row
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(imagePath);
            
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(name);
            Cell cell3 = row.createCell(2);
            cell3.setCellValue(cost);
            Cell cell4 = row.createCell(3);
            cell4.setCellValue(price);
            Cell cell5 = row.createCell(4);
            cell5.setCellValue(amount);
            System.out.println(imagePath);
            
            // Save the changes to the Excel file
            try (FileOutputStream fos = new FileOutputStream(file);) {
                workbook.write(fos);
            }
            System.out.println("Data has been written to the new row successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while writing data to the Excel file.");
            e.printStackTrace();
        }
    }
    public void updateExcle(JTable tb) {
        try (FileInputStream fis = new FileInputStream("data.xlsx");
            Workbook workbook = WorkbookFactory.create(fis);){
            // Get the first sheet
            Sheet sheet = workbook.getSheetAt(0);
            // Get the JTable data
            JTable table = tb; // Replace with your own JTable reference
            int rowCount = table.getRowCount();
            int columnCount = table.getColumnCount();

            // Update the Excel file with the modified data
            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < columnCount; col++) {
                    Object value = table.getValueAt(row, col);
                    Cell cell = sheet.getRow(row+1).getCell(col+1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellValue(value.toString());
                }
            }

            // Save the updated Excel file
            try(FileOutputStream fos = new FileOutputStream("data.xlsx");) {
                workbook.write(fos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
