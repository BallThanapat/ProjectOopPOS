import java.awt.Color;
import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;

public class FinancialChart {
    private JPanel pChart;
    public FinancialChart() {
        pChart = new JPanel();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try (FileInputStream fis = new FileInputStream("data.xlsx");
            Workbook data1 = WorkbookFactory.create(fis);){
            Sheet sheet2 = data1.getSheetAt(1);
            for(int rowIndex = 1; rowIndex <= sheet2.getLastRowNum(); rowIndex++) {
                    Row row = sheet2.getRow(rowIndex);
                Cell cellDay = row.getCell(0);
                Cell cellCost = row.getCell(1);
                Cell cellPrice = row.getCell(2);
                
                dataset.addValue(cellCost.getNumericCellValue(), "Total Cost", (int)cellDay.getNumericCellValue()+"");
                dataset.addValue(cellPrice.getNumericCellValue(), "Total Price", (int)cellDay.getNumericCellValue()+"");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        JFreeChart chart = ChartFactory.createBarChart("Net Income","Date",
                "Rate",dataset,PlotOrientation.VERTICAL,true,true,false);

        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        plot.setRangeGridlinePaint(Color.black);
        domainAxis.setCategoryMargin(0.2);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setItemMargin(0);

        ChartPanel cp = new ChartPanel(chart);
        pChart.add(cp);
        pChart.setBounds(150, 50, 700, 450);
    }
    public JPanel getPChart() {
        return this.pChart;
    }
}