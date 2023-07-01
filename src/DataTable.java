import java.awt.Dimension;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataTable {
    private JPanel p;
    private JTable tbData;
    public DataTable() {
        p = new JPanel();
        tbData = new JTable();
        tbData.setPreferredScrollableViewportSize(new Dimension(1000, 580));
        DefaultTableModel tableModel = new DefaultTableModel();
        
        try (FileInputStream file = new FileInputStream(new File("data.xlsx"));
                Workbook bookData = new XSSFWorkbook(file);){
            
            Sheet sheet = bookData.getSheet("Stock");

            Row headRow = sheet.getRow(0);
            for (int columnIndex = 1; columnIndex < headRow.getLastCellNum(); columnIndex++) {
                Cell cell = headRow.getCell(columnIndex);
                tableModel.addColumn(cell.getStringCellValue());
            
            }
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row dataRow = sheet.getRow(rowIndex);
                Object[] rowData = new Object[dataRow.getLastCellNum() - 1];

                for (int columnIndex = 1; columnIndex < dataRow.getLastCellNum(); columnIndex++) {
                    Cell cell = dataRow.getCell(columnIndex);
                    switch (cell.getCellType()) {
                        case STRING:
                            rowData[columnIndex - 1] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            rowData[columnIndex - 1] = cell.getNumericCellValue();
                            break;
                        default:
                            rowData[columnIndex - 1] = "";
                            break;
                    }
                }
            tableModel.addRow(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        tbData.setModel(tableModel);
        tbData.setEnabled(false);
        tbData.setRowHeight(50);
        tbData.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 21));

        JScrollPane scrollPane = new JScrollPane(tbData);
        p.add(scrollPane);
        p.setBounds(0, 0, 1030, 610);
    }
    public JPanel getP() {
        return this.p;
    }
    public JTable gettbData() {
        return this.tbData;
    }
}