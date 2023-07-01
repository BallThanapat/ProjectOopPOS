import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.DefaultListModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.time.LocalDate;
class GetDataExcel {
    private double totalP, totalC;
    private DefaultListModel<String> cartListModel;
    public double gettotolP() {
        return this.totalP;
    }
    public void setCartListModel(DefaultListModel<String> cartListModel) {
        this.cartListModel = cartListModel;
    }
    public void getData() {
        try (Workbook data1 = new XSSFWorkbook("data.xlsx")){
            Sheet sheet = data1.getSheet("Stock");
            for(int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                Cell cellName = row.getCell(1);
                Cell cellCost = row.getCell(2);
                Cell cellPrice = row.getCell(3);
                for(int i=0; i<cartListModel.size(); i++){
                    if(cellName.getStringCellValue().equals(cartListModel.get(i))){
                        switch (cellPrice.getCellType()) {
                            case STRING:
                                totalP += Double.parseDouble(cellPrice.getStringCellValue());
                                break;
                            case NUMERIC:
                                totalP += cellPrice.getNumericCellValue();
                                break;
                            default:
                                totalP += 0;
                                break;
                        }
                        switch (cellCost.getCellType()) {
                            case STRING:
                                totalC += Double.parseDouble(cellCost.getStringCellValue());
                                break;
                            case NUMERIC:
                                totalC += cellCost.getNumericCellValue();
                                break;
                            default:
                                totalP += 0;
                                break;
                        }
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void decreaseAmount() {
        try (FileInputStream fileInputStream = new FileInputStream("data.xlsx");
            Workbook data1 = new XSSFWorkbook(fileInputStream);) {
            Sheet sheet = data1.getSheet("Stock");
            for(int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                Cell cellName = row.getCell(1);
                Cell cellAmount = row.getCell(4);
                for(int i=0; i<cartListModel.size(); i++){
                    if(cellName.getStringCellValue().equals((String)cartListModel.get(i))){
                        switch (cellAmount.getCellType()) {
                            case STRING:
                                cellAmount.setCellValue(Double.parseDouble(cellAmount.getStringCellValue())-1);
                                break;
                            case NUMERIC:
                                cellAmount.setCellValue(cellAmount.getNumericCellValue()-1);
                                break;
                            default:
                                totalP += 0;
                                break;
                        }
                    }
                }
            }
            LocalDate currentDate = LocalDate.now();
            int day = currentDate.getDayOfMonth();

            Sheet sheet2 = data1.getSheetAt(1);
            boolean check = false;
            for (int i=2; i<sheet2.getLastRowNum(); i++) {
                Row row = sheet2.getRow(i);
                Cell c1 = row.getCell(1);
                Cell c2 = row.getCell(2);
                if (c1.getNumericCellValue()!=0||c2.getNumericCellValue()!=0) {
                    check = true;
                }
            }
            if (day==1&&check) {
                for (int i=1; i<sheet2.getLastRowNum(); i++) {
                    Row row = sheet2.getRow(i);
                    Cell c1 = row.getCell(1);
                    Cell c2 = row.getCell(2);
                    c1.setCellValue(0.0);
                    c2.setCellValue(0.0);
                }
            }
            Row row2 = sheet2.getRow(day);
            Cell cellC = row2.getCell(1);
            cellC.setCellValue(totalC+cellC.getNumericCellValue());
            Cell cellP = row2.getCell(2);
            cellP.setCellValue(totalP+cellP.getNumericCellValue());
            try (FileOutputStream fos = new FileOutputStream("data.xlsx");) {
                data1.write(fos);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void setStandard(DefaultListModel<String> cartListModel) {
        this.cartListModel = cartListModel;
        totalP = 0;
        totalC = 0;
    }
}
