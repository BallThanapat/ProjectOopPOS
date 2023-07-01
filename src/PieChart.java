import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jfree.chart.ChartPanel;

public class PieChart {
    private CalculatePercent pc;
    private int totalMcost0;
    private int totalMprice0;
    private CalculateProfit m1;
    private JPanel pChart;
    public PieChart() {
        pc = new CalculatePercent();
        pChart = new JPanel();
        try (FileInputStream fis = new FileInputStream("data.xlsx");
            Workbook data1 = WorkbookFactory.create(fis);){
            Sheet sheet2 = data1.getSheetAt(1);
            for(int rowIndex = 1; rowIndex <= sheet2.getLastRowNum(); rowIndex++) {
                    Row row = sheet2.getRow(rowIndex);
                    Cell cellCost = row.getCell(1);
                    Cell cellPrice = row.getCell(2);
                    totalMcost0 += (int)cellCost.getNumericCellValue();
                    totalMprice0 += (int)cellPrice.getNumericCellValue();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(totalMcost0+" ss "+totalMprice0);
        pc.setTotalmcost(totalMcost0);
        pc.setTotalmprice(totalMprice0);
        pc.totalProfit();
        pc.setPercentM();
        System.out.println(100-pc.getPcpf()+" sed "+pc.getPcpf());
        
        JFrame frame = new JFrame("Net Income");
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Total Month Cost\n"+(100-pc.getPcpf())+"%", 100-pc.getPcpf());
        dataset.setValue("Total Month Price\n"+pc.getPcpf()+"%", pc.getPcpf());
//        dataset.setValue("Total Month Cost", 100-pc.getPcpf());
//        dataset.setValue("Total Month Price", pc.getPcpf());

        JFreeChart chart = ChartFactory.createPieChart("Net Income",dataset,true,true,false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Total Month Cost", Color.orange);
        plot.setSectionPaint("Total Month Price", Color.blue);

        ChartPanel chartPanel = new ChartPanel(chart);
        pChart.add(chartPanel);
        pChart.setBounds(150, 50, 700, 450);
    }
    public JPanel getpChart() {
        return this.pChart;
    }
}

