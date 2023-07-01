
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Payment {
    private JPanel p2, pPay, pBig;
    private JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnc, btnDel;
    private ArrayList alb;
    private JLabel lTotal, lPaid, lReturn, lPay, lQuit;
    private JTextField tfTotal, tfPaid, tfReturn;
    public Payment(){
        pBig = new JPanel();
        p2 = new JPanel();
        pPay = new JPanel();
        alb = new ArrayList();
        btn1 = new JButton("1");
        btn2 = new JButton("2");
        btn3 = new JButton("3");
        btn4 = new JButton("4");
        btn5 = new JButton("5");
        btn6 = new JButton("6");
        btn7 = new JButton("7");
        btn8 = new JButton("8");
        btn9 = new JButton("9");
        btn0 = new JButton("0");
        btnc = new JButton("C");
        btnDel = new JButton("Del");
        lQuit = new JLabel("  Quit");
        alb.add(btn1);
        alb.add(btn2);
        alb.add(btn3);
        alb.add(btn4);
        alb.add(btn5);
        alb.add(btn6);
        alb.add(btn7);
        alb.add(btn8);
        alb.add(btn9);
        alb.add(btn0);
        alb.add(btnc);
        alb.add(btnDel);
        
        lTotal = new JLabel("Total                 :");
        lPaid = new JLabel("Paid Amount     :");
        lReturn = new JLabel("Return Amount :");
        lPay = new JLabel("   Pay");
        tfTotal = new JTextField();
        tfPaid = new JTextField();
        tfReturn = new JTextField();
        
        Font font = new Font("Arial",Font.PLAIN,32);
        lTotal.setFont(font);
        lTotal.setBounds(500, 250, 300, 40);
        tfTotal.setFont(font);
        tfTotal.setBounds(800, 250, 150, 40);
        lPaid.setFont(font);
        lPaid.setBounds(500, 300, 300, 40);
        tfPaid.setFont(font);
        tfPaid.setText("0");
        tfPaid.setBounds(800, 300, 150, 40);
        lReturn.setFont(font);
        lReturn.setBounds(500, 440, 300, 40);
        tfReturn.setFont(font);
        tfReturn.setBounds(800, 440, 150, 40);
        
        pBig.setLayout(null);
        pBig.setBounds(0, 0, 1030, 690);
        
        p2.setLayout(new GridLayout(4,3));
        p2.add(btn7);p2.add(btn8);p2.add(btn9);
        p2.add(btn4);p2.add(btn5);p2.add(btn6);
        p2.add(btn1);p2.add(btn2);p2.add(btn3);
        p2.add(btnc);p2.add(btn0);p2.add(btnDel);
        p2.setBounds(150,200,300,300);
        
        tfTotal.setEditable(false);
        tfPaid.setEditable(false);
        tfReturn.setEditable(false);
        
        lPay.setBounds(650, 360, 100, 50);
        lPay.setBackground(new Color(176,224,230));
        lPay.setFont(font);
        lPay.setOpaque(true);
        
        lQuit.setBounds(650, 360, 100, 50);
        lQuit.setBackground(new Color(176,224,230));
        lQuit.setFont(font);
        lQuit.setOpaque(true);
        lQuit.setVisible(false);
        
        pBig.add(p2);
        pBig.add(lTotal);
        pBig.add(tfTotal);
        pBig.add(lPaid);
        pBig.add(tfPaid);
        pBig.add(lReturn);
        pBig.add(tfReturn);
        pBig.add(lPay);
        pBig.add(lQuit);
    }
    public JLabel getlQuit() {
        return this.lQuit;
    }
    public void setStandard() {
        tfTotal.setText("0");
        tfPaid.setText("0");
        tfReturn.setText("0");
        lPay.setVisible(true);
        lQuit.setVisible(false);
    }
    public JPanel getpBig() {
        return this.pBig;
    }
    public void setTotal(String str) {
        this.tfTotal.setText(str);
    }
    public void setTPaid(String str) {
        if (tfPaid.getText().equals("0")) {
            tfPaid.setText(str);
        } else {
            tfPaid.setText(tfPaid.getText()+str);
        }
    }
    public ArrayList getalb() {
        return this.alb;
    }
    public void clearTPaid() {
        tfPaid.setText("");
    }
    public void delLastStr() {
        tfPaid.setText(tfPaid.getText().substring(0, tfPaid.getText().length() - 1));
    }
    public JLabel getlPay() {
        return this.lPay;
    }
    public JTextField gettfPaid() {
        return this.tfPaid;
    }
    public JTextField gettfTotal() {
        return this.tfTotal;
    }
    public JTextField gettfReturn() {
        return this.tfReturn;
    }
}