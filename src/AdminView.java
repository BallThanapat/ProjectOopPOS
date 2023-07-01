import java.awt.*;
import java.net.URL;
import javax.swing.*;
public class AdminView {
    private JFrame fr;
    private JPopupMenu popup;
    private JPanel pUp, pLeft, pUpLeft, pMain, pChart, pPie;
    private JMenuItem miLogout;
    private JLabel lMain, lCheckStock, lCashier, lLogo, lPic, lChart, lPie;
    private FinancialChart fc;
    private PieChart pc;
    public AdminView() {
        fr = new JFrame();
        pUp = new JPanel();
        pLeft = new JPanel();
        pMain = new JPanel();
        pChart = new JPanel();
        pPie = new JPanel();
        lMain = new JLabel(" Main");
        lCheckStock = new JLabel(" CheckStock");
        lCashier = new JLabel(" Cashier");
        popup = new JPopupMenu();
        miLogout = new JMenuItem("         Logout");
        lChart = new JLabel("   Bar Chart");
        lPie = new JLabel("    Pie Chart");
        fc = new FinancialChart();
        pc = new PieChart();
        ImageIcon icon = null;
        URL imageURL = LoginAdminView.class.getResource("Image/logomarket.png");
        if (imageURL != null) {
            icon = new ImageIcon(imageURL);
        }
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(60,60,Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        lLogo = new JLabel("CatchCoin", icon, SwingConstants.CENTER);
        ImageIcon icon2 = null;
        URL imageURL2 = LoginAdminView.class.getResource("Image/human.png");
        if (imageURL != null) {
            icon2 = new ImageIcon(imageURL2);
        }
        Image img2 = icon2.getImage();
        Image newImg2 = img2.getScaledInstance(60,50,Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg2);
        lPic = new JLabel("Hi, Admin1234", icon, SwingConstants.CENTER);
        
        Font font = new Font("Arial", Font.PLAIN, 24);
        Color c = new Color(125, 232, 232);
        Color lc = Color.WHITE;
        lMain.setFont(font);
        lCheckStock.setFont(font);
        lCashier.setFont(font);
        
        miLogout.setFont(font);
        miLogout.setForeground(Color.RED);
        popup.setPreferredSize(new Dimension(220, 60));
        popup.add(miLogout);
        
        lMain.setBounds(10, 200, 220, 50);
        lCheckStock.setBounds(10, 260, 180, 50);
        lCashier.setBounds(10, 320, 180, 50);
        lMain.setBackground(lc);
        lCheckStock.setBackground(lc);
        lCashier.setBackground(lc);
        lMain.setOpaque(true);
        lCheckStock.setOpaque(true);
        lCashier.setOpaque(true);
        lMain.setEnabled(false);
        
        lLogo.setBounds(0, 0, 220, 60);
        lLogo.setFont(new Font("Arial", Font.BOLD, 28));
        lLogo.setBackground(new Color(100,149,237));
        lLogo.setOpaque(true);
        
        lPic.setFont(font);
        lPic.setBounds(1000, 0, 220, 60);
        lPic.setBackground(new Color(102,178,255));
        lPic.setOpaque(true);
        
        pUp.setBackground(new Color(102,178,255));
        pUp.setBounds(0, 0, 1250, 60);
        pUp.setLayout(null);
        pUp.add(lPic);
        pUp.add(lLogo);
        
        pLeft.setBackground(new Color(135,206,235));
        pLeft.setBounds(0, 0, 220, 750);
        pLeft.setLayout(null);
        pLeft.add(lMain);
        pLeft.add(lCheckStock);
        pLeft.add(lCashier);
        
        pChart.setBackground(Color.WHITE);
        pChart.add(fc.getPChart());
        pChart.setBounds(0, 100, 1030, 550);
        pChart.setLayout(null);
        
        pPie.setBackground(Color.WHITE);
        pPie.add(pc.getpChart());
        pPie.setBounds(0, 100, 1030, 550);
        pPie.setLayout(null);
        pPie.setVisible(false);
        
        lChart.setEnabled(false);
        lChart.setBackground(Color.WHITE);
        lChart.setOpaque(true);
        lChart.setFont(font);
        lChart.setBounds(50, 40, 150, 60);
        
        lPie.setBackground(Color.WHITE);
        lPie.setOpaque(true);
        lPie.setFont(font);
        lPie.setBounds(220, 40, 150, 50);
        
        pMain.setBackground(new Color(206,224,230));
        pMain.setBounds(220, 60, 1030, 690);
        pMain.setLayout(null);
        pMain.add(pChart);
        pMain.add(pPie);
        pMain.add(lChart);
        pMain.add(lPie);
        
        fr.setUndecorated(true);
        fr.add(pUp);
        fr.add(pLeft);
        fr.add(pMain);
        fr.getContentPane().setBackground(Color.WHITE);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(new Dimension(1250,750));
        fr.setLayout(null);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
        new AdminController(this);
    }
    public void resetChart() {
        pChart.remove(fc.getPChart());
        fc = new FinancialChart();
        pChart.add(fc.getPChart());
        pPie.remove(pc.getpChart());
        pc = new PieChart();
        pPie.add(pc.getpChart());
        switchPage(true);
    }
    public JLabel getlLogo() {
        return this.lLogo;
    }
    public JLabel getlPic() {
        return this.lPic;
    }
    public JLabel getlMain() {
        return this.lMain;
    }
    public JLabel getlCheckStock() {
        return this.lCheckStock;
    }
    public JLabel getlCashier() {
        return this.lCashier;
    }
    public JMenuItem getmiLogout() {
        return this.miLogout;
    }
    public JFrame getfr() {
        return this.fr;
    }
    public void switchPage(boolean b) {
        if (b) {
            lChart.setEnabled(false);
            lPie.setEnabled(true);
            pChart.setVisible(true);
            pPie.setVisible(false);
            lChart.setBounds(50, 40, 150, 60);
            lPie.setBounds(220, 40, 150, 50);
        } else {
            lChart.setEnabled(true);
            lPie.setEnabled(false);
            pChart.setVisible(false);
            pPie.setVisible(true);
            lChart.setBounds(50, 40, 150, 50);
            lPie.setBounds(220, 40, 150, 60);
        }
    }
    public void clickPopup() {
        popup.show(lPic, 0, lPic.getHeight());
    }
    public void setlPic(Color c) {
        lPic.setBackground(c);
    }
    public void setlMain(Color c) {
        lMain.setBackground(c);
    }
    public void setClChart(Color c) {
        lChart.setBackground(c);
    }
    public void setClPie(Color c) {
        lPie.setBackground(c);
    }
    public void setlCheckStock(Color c) {
        lCheckStock.setBackground(c);
    }
    public void setlCashier(Color c) {
        lCashier.setBackground(c);
    }
    public JLabel getlChart() {
        return this.lChart;
    }
    public JLabel getlPie() {
        return this.lPie;
    }
    public void setMainPage() {
        lMain.setBounds(10, 200, 220, 50);
        lCheckStock.setBounds(10, 260, 180, 50);
        lCashier.setBounds(10, 320, 180, 50);
        lMain.setEnabled(false);
        lCheckStock.setEnabled(true);
        lCashier.setEnabled(true);
        resetChart();
        pMain.setVisible(true);
    }
    public void setCheckStockPage() {
        lMain.setBounds(10, 200, 180, 50);
        lCheckStock.setBounds(10, 260, 220, 50);
        lCashier.setBounds(10, 320, 180, 50);
        lMain.setEnabled(true);
        lCheckStock.setEnabled(false);
        lCashier.setEnabled(true);
        pMain.setVisible(false);
    }
    public void setCashierPage() {
        lMain.setBounds(10, 200, 180, 50);
        lCheckStock.setBounds(10, 260, 180, 50);
        lCashier.setBounds(10, 320, 220, 50);
        lMain.setEnabled(true);
        lCheckStock.setEnabled(true);
        lCashier.setEnabled(false);
        pMain.setVisible(false);
    }
//    public static void main(String[] args) {
//        new AdminView();
//    }
}
