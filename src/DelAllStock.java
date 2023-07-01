import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DelAllStock {
    public DelAllStock () {
        try (FileInputStream fis = new FileInputStream(new File("data.xlsx"));
            Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = sheet.getLastRowNum(); i >= 1; i--) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    sheet.removeRow(row);
                }
            }
            try (FileOutputStream fos = new FileOutputStream(new File("data.xlsx"))) {
                workbook.write(fos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}