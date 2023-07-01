import java.awt.*;
import java.net.URL;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Cart {
    private JButton btn;
    private JLabel lb1, lbtotal, lbqrcode;
    private JPanel pList, panelmain, panelpay;
    private JTextField total;
    private JList<String> cartList;
    private DefaultListModel<String> cartListModel;
    private JButton bDel, bPay, bClear;
    private JScrollPane spList;
    private ImageIcon icon;
    private GetDataExcel gde;
    public Cart(){
        btn = new JButton("Pay");
        lb1 = new JLabel("");
        pList = new JPanel();
        bDel = new JButton("Delete");
        bPay = new JButton("Pay");
        panelmain = new JPanel();
        panelpay = new JPanel();
        total = new JTextField(40);
        lbtotal = new JLabel("Total");
        lbqrcode = new JLabel();
        gde = new GetDataExcel();
        bClear = new JButton("Clear");
        
        URL imageURL = Cart.class.getResource("Image/qrcode.jpg");
        if (imageURL != null) {
            icon = new ImageIcon(imageURL);
        }
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(400,400,Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        lbqrcode = new JLabel(icon, JLabel.CENTER);
        
        spList = new JScrollPane();
        cartListModel = new DefaultListModel<>();
        cartList = new JList<>(cartListModel);
        cartList.setFont(new java.awt.Font("Arail", java.awt.Font.PLAIN, 24));
        spList.setViewportView(cartList);
        
        spList.setBackground(java.awt.Color.red);
        spList.setBounds(0, 0, 600, 400);
        //BasicInternalFrameUI ui2 = (BasicInternalFrameUI) ifList.getUI();
        //ui2.setNorthPane(null);
        bDel.setFont(new java.awt.Font("Arail", java.awt.Font.PLAIN, 26));
        bDel.setBounds(250, 450, 120, 75);
        bPay.setFont(new java.awt.Font("Arail", java.awt.Font.PLAIN, 26));
        bPay.setBounds(100, 450, 120, 75);
        total.setFont(new java.awt.Font("Arail", java.awt.Font.PLAIN, 26));
        bClear.setFont(new java.awt.Font("Arail", java.awt.Font.PLAIN, 26));
        bClear.setBounds(400, 450, 120, 75);
        bClear.setVisible(false);
        
        lbtotal.setFont(new java.awt.Font("Arail", java.awt.Font.PLAIN, 26));
        lbtotal.setBounds(100, 350, 200, 50);
        lbqrcode.setBounds(0, 0, 400, 400);
        
        total.setEditable(false);
        total.setBackground(java.awt.Color.WHITE);
        total.setForeground(java.awt.Color.BLACK);
        total.setBounds(200, 350, 200, 50);
        
        panelpay.setVisible(false);
        panelpay.setLayout(null);
        panelpay.setBackground(java.awt.Color.WHITE);
        panelpay.add(lbtotal);
        panelpay.add(total);
        panelpay.add(lbqrcode);
        panelpay.setBounds(600, 0, 500, 400);
        
        panelmain.add(panelpay);
        panelmain.add(bClear);
        panelmain.add(bDel);
        panelmain.add(bPay);
        panelmain.add(spList);
        panelmain.setSize(1250, 700);
        panelmain.setLocation(100, 100);
        panelmain.setLayout(null);
        panelmain.setVisible(false);
    }
    public JPanel getPanel(){
        return this.panelmain;
    }
    public void addCart(JLabel l){
        cartListModel.addElement(l.getText());
    }
    public JScrollPane getScorllPane(){
        return this.spList;
    }
    public JButton getbDel(){
        return this.bDel;
    }
    public JButton getbPay(){
        return this.bPay;
    }
    public JPanel getPanelPay(){
        return this.panelpay;
    }
    public JButton getbClear(){
        return this.bClear;
    }
    public void removeSelect(Object o){
        cartListModel.removeElement(o);
//        gde.setStandard(cartListModel);
//        gde.getData();
//        total.setText(gde.gettotolP()+"");
    }
    public Object getSelect() {
        return cartList.getSelectedValue();
    }
    public void setTotal(){
        gde.setCartListModel(cartListModel);
        gde.getData();
        total.setText(gde.gettotolP()+"");
        gde.decreaseAmount();
        gde.setStandard(cartListModel);
    }
    public void setClear(){
        cartListModel.clear();
        gde.setStandard(cartListModel);
    }
}
