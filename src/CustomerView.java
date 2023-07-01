import java.awt.*;
import java.net.URL;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class CustomerView {
    private Cart c;
    private JFrame fr;
    private JPanel panelup, panelitem;
    private JMenuItem logout, myaccount;
    private JPopupMenu popup;
    private JLabel purchase, account, logo;
    private ArrayList ap;
    private String str;
    private ImageIcon icon;
    private JScrollPane spProduct;
    public CustomerView(){
        ImageIcon icon = null;
        fr = new JFrame();
        panelup = new JPanel();
        myaccount = new JMenuItem("My account");
        logout = new JMenuItem("Exit Program");
        popup = new JPopupMenu();
        panelitem = new JPanel();
        ap = new ArrayList();
        c = new Cart();
        spProduct = new JScrollPane();
        
        try (FileInputStream file = new FileInputStream(new File("data.xlsx"));
                Workbook bookData = new XSSFWorkbook(file);){

            Sheet sheet = bookData.getSheet("Stock");

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row dataRow = sheet.getRow(rowIndex);
                for (int columnIndex = 0; columnIndex < 2; columnIndex++) {
                    Cell cell = dataRow.getCell(columnIndex);
                    switch (columnIndex) {
                        case 0:
                            System.out.println(cell.getStringCellValue());
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
                panelitem.add((JLabel)ap.get(i));
            }
            panelitem.setLayout(new GridLayout((sheet.getLastRowNum()+1)/3,3));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        URL imageURL = CustomerView .class.getResource("Image/logomarket.png");
        if (imageURL != null) {
            icon = new ImageIcon(imageURL);
        }
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        logo = new JLabel("CatchCoin", icon, JLabel.CENTER);
        
        ImageIcon icon2 = null;
        URL imageURL2 = CustomerView.class.getResource("Image/human.png");
        if (imageURL != null) {
            icon2 = new ImageIcon(imageURL2);
        }
        Image img2 = icon2.getImage();
        Image newImg2 = img2.getScaledInstance(40,38,Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg2);
        account = new JLabel("My account", icon, JLabel.CENTER);
        
        ImageIcon icon3 = null;
        URL imageURL3 = CustomerView.class.getResource("Image/basket.png");
        if (imageURL != null) {
            icon3 = new ImageIcon(imageURL3);
        }
        Image img3 = icon3.getImage();
        Image newImg3 = img3.getScaledInstance(48,48,Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg3);
        purchase = new JLabel("My purchase", icon, JLabel.CENTER);
        
        java.awt.Font font = new java.awt.Font("Arial", java.awt.Font.PLAIN, 18);
        purchase.setFont(font);
        account.setFont(font);
        logo.setFont(font);
        account.setFont(font);
        logout.setFont(font);
        myaccount.setFont(font);
        
        myaccount.setForeground(java.awt.Color.WHITE);
        logout.setForeground(java.awt.Color.RED);
        popup.setPreferredSize(new Dimension(220, 60));
        //popup.add(myaccount);
        popup.add(logout);
        
        logo.setBackground(new java.awt.Color(102, 178, 255));
        logo.setForeground(java.awt.Color.WHITE);
        logo.setBounds(-30, 0, 220, 70);
        logo.setOpaque(true);
        
        account.setBackground(new java.awt.Color(102, 178, 255));
        account.setForeground(java.awt.Color.WHITE);
        account.setBounds(1025, 0, 220, 70);
        account.setOpaque(true);
        
        purchase.setBackground(new java.awt.Color(102, 178, 255));
        purchase.setForeground(java.awt.Color.WHITE);
        purchase.setBounds(820, 0, 210, 70);
        purchase.setOpaque(true);
        
        panelitem.setBounds(0, 0, 500, 500);
        spProduct = new JScrollPane(panelitem);
        spProduct.setBounds(0, 60, 1235, 640);
        
        panelup.setBackground(new java.awt.Color(102,178,255));
        panelup.setBounds(-10, -10, 1250, 60);
        panelup.add(account);
        panelup.setLayout(null);
        panelup.add(logo);
        panelup.add(purchase);
        
        //fr.setUndecorated(true);
        //fr.add(popup);
        fr.add(c.getPanel());
        fr.add(spProduct);
        fr.add(panelup);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(1250, 700);
        fr.setLocation(100, 100);
        fr.getContentPane().setBackground(java.awt.Color.WHITE);
        fr.setLayout(null);
        fr.setResizable(false);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
        new CustomerController(this);
    }
    public JFrame getFr(){
        return this.fr;
    }
    public JLabel getPurchase(){
        return this.purchase;
    }
    public JScrollPane getspProduct(){
        return this.spProduct;
    }
    public JLabel getAccount(){
        return this.account;
    }
    public JLabel getLogo(){
        return this.logo;
    }
    public JMenuItem getLogout(){
        return this.logout;
    }
    public JMenuItem getMyaccount(){
        return this.myaccount;
    }
    public JPanel getPanelItem(){
        return this.panelitem;
    }
    public void setlbPurchase(java.awt.Color c){
        this.purchase.setBackground(c);
    }
    public void setlbLogo(java.awt.Color c){
        this.logo.setBackground(c);
    }
    public void setlbLogout(java.awt.Color c){
        this.logout.setBackground(c);
    }
    public void setlbMyaccount(java.awt.Color c){
        this.myaccount.setBackground(c);
    }
    public void setpPanelItem(int i, java.awt.Color c){
        ((JLabel)ap.get(i)).setBackground(c);
    }
    public void setlbAccount(java.awt.Color c){
        this.account.setBackground(c);
    }
    public void clickPopup() {
        popup.show(account, 0, account.getHeight());
    }
    public ArrayList getArrayList(){
        return this.ap;
    }
    public Cart getC(){
        return this.c;
    }
}