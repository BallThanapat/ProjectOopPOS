import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class CashierView {
    private JDesktopPane dp;
    private JInternalFrame ifProduct, ifList, ifPay;
    private JPanel pProduct, pList;
    private ArrayList ap;
    private JLabel l, l2;
    private ImageIcon icon;
    private JButton bDel, bPay;
    private DefaultListModel<String> cartListModel;
    private JList<String> cartList;
    private String str;
    private Payment pm;
    private GetDataExcel gde;
    public CashierView() {
        dp = new JDesktopPane();
        ifProduct = new JInternalFrame();
        ifList = new JInternalFrame();
        ifPay = new JInternalFrame();
        pProduct = new JPanel();
        ap = new ArrayList();
        bDel = new JButton("Delete");
        bPay = new JButton("Pay");
        pm = new Payment();
        gde = new GetDataExcel();
        try (FileInputStream file = new FileInputStream(new File("data.xlsx"));
            Workbook bookData = new XSSFWorkbook(file);){

            Sheet sheet = bookData.getSheet("Stock");

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row dataRow = sheet.getRow(rowIndex);
                for (int columnIndex = 0; columnIndex < 2; columnIndex++) {
                    Cell cell = dataRow.getCell(columnIndex);
                    switch (columnIndex) {
                        case 0:
                            File fImg = new File(cell.getStringCellValue());
                            BufferedImage bufImg = ImageIO.read(fImg);
                            icon = new ImageIcon(bufImg);
                            Image newImg = icon.getImage().getScaledInstance(150,150,Image.SCALE_SMOOTH);
                            icon = new ImageIcon(newImg);
                            break;
                        case 1:
                            str = (String)cell.getStringCellValue();
                            break;
                        default:
                            String str1 = "";
                            break;
                    }
                }
                JLabel l = new JLabel(str, icon, JLabel.CENTER);
                l.setOpaque(true);
                ap.add(l);
            }
            for (int i=0; i<ap.size(); i++) {
                pProduct.add((JLabel)ap.get(i));
            }
            pProduct.setLayout(new GridLayout((sheet.getLastRowNum()+1)/2,2));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JScrollPane spProduct = new JScrollPane(pProduct);
        spProduct.setBounds(0, 0, 500, 500);
        BasicInternalFrameUI ui = (BasicInternalFrameUI) ifProduct.getUI();
        ui.setNorthPane(null);
        ifProduct.setBorder(null);
        ifProduct.setBounds(0, 0, 700, 690);
        ifProduct.setVisible(true);
        ifProduct.add(spProduct);
        
        JScrollPane spList = new JScrollPane(pList);
        cartListModel = new DefaultListModel<>();
        cartList = new JList<>(cartListModel);
        cartList.setFont(new java.awt.Font("Arail", java.awt.Font.PLAIN, 24));
        spList.setViewportView(cartList);
        
        spList.setBounds(0, 0, 330, 415);
        BasicInternalFrameUI ui2 = (BasicInternalFrameUI) ifList.getUI();
        ui2.setNorthPane(null);
        bDel.setFont(new java.awt.Font("Arail", java.awt.Font.PLAIN, 18));
        bDel.setBounds(125, 425, 100, 25);
        bPay.setFont(new java.awt.Font("Arail", java.awt.Font.PLAIN, 36));
        bPay.setBounds(90, 500, 150, 75);
        ifList.setBorder(null);
        ifList.setBounds(700, 0, 330, 690);
        ifList.setVisible(true);
        ifList.add(bDel);
        ifList.add(bPay);
        ifList.add(spList);
        ifList.setLayout(null);
        
        ifPay.add(pm.getpBig());
        ifPay.setBounds(0, 0, 1030, 690);
        BasicInternalFrameUI ui3 = (BasicInternalFrameUI) ifPay.getUI();
        ui3.setNorthPane(null);
        ifPay.setBorder(null);
        ifPay.setVisible(false);
        ifPay.setLayout(null);
        
        dp.add(ifPay);
        dp.add(ifProduct);
        dp.add(ifList);
        
        dp.setLayout(null);
        dp.setBounds(220, 60, 1030, 690);
        dp.setVisible(false);
    }
    public void sendStr(String str) {
        pm.setTPaid(str);
    }
    public void claerStr() {
        pm.clearTPaid();
    }
    public void delStr() {
        pm.delLastStr();
    }
    public ArrayList getpmalb () {
        return pm.getalb();
    }
    public Object getSelect() {
        return cartList.getSelectedValue();
    }
    public JButton getB() {
        return this.bDel;
    }
    public JButton getbPay() {
        return this.bPay;
    }
    public void setStandard() {
        ifList.setVisible(true);
        ifProduct.setVisible(true);
        ifPay.setVisible(false);
        cartListModel.clear();
        pm.setStandard();
        gde.setStandard(cartListModel);
    }
    public JDesktopPane getDP() {
        return this.dp;
    }
    public ArrayList getAL() {
        return this.ap;
    }
    public void selPColor(int i, java.awt.Color c) {
        ((JLabel)ap.get(i)).setBackground(c);
    }
    public void addCart(int i) {
        cartListModel.addElement(((JLabel)ap.get(i)).getText());
    }
    public void removeSelect(Object o) {
        cartListModel.removeElement(o);
    }
    public void payCash() {
        ifList.setVisible(false);
        ifProduct.setVisible(false);
        ifPay.setVisible(true);
        gde.setCartListModel(cartListModel);
        gde.getData();
        pm.setTotal(gde.gettotolP()+"");
    }
    public JLabel getpmLPay() {
        return pm.getlPay();
    }
    public JLabel getpmLQuit() {
        return pm.getlQuit();
    }
    public void setColorpmLPay(java.awt.Color c) {
        pm.getlPay().setBackground(c);
    }
    public void setColorpmLQuit(java.awt.Color c) {
        pm.getlQuit().setBackground(c);
    }
    public void returnAmount() {
        double left = Double.parseDouble(pm.gettfPaid().getText()) - Double.parseDouble(pm.gettfTotal().getText());
        if (left<0) {
            JOptionPane.showMessageDialog(null, "Not Enough Money", "Failed Paid Money", JOptionPane.ERROR_MESSAGE);
            pm.gettfPaid().setText("");
        }
        else {
            pm.gettfReturn().setText(left+"");
            pm.getlPay().setVisible(false);
            pm.getlQuit().setVisible(true);
            gde.decreaseAmount();
        }
    }
}
