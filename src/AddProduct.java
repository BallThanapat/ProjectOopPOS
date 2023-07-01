import java.awt.*;
import javax.swing.*;
import javax.swing.text.NumberFormatter;
public class AddProduct {
    private JPanel pfr, p1;
    private JLabel lbName, lbPrice, lbCost, lbValue, lPic;
    private JTextField tfName, tfPrice, tfCost;
    private JButton btPic, btSave, btCancel;
    private JSpinner value;

    public AddProduct(){
        pfr = new JPanel();
        p1 = new JPanel();
        lbName = new JLabel("Name:");
        lbCost = new JLabel("Cost:");
        lbPrice = new JLabel("Price:");
        tfName = new JTextField();
        tfCost = new JTextField();
        tfPrice = new JTextField();
        btPic = new JButton("Add Picture");
        btSave = new JButton("Save");
        btCancel = new JButton("Cancel");
        SpinnerModel num = new SpinnerNumberModel(0, 0, 1000, 1);
        value = new JSpinner(num);
        lbValue = new JLabel("Amount: ");
        
        lPic = new JLabel();
        lPic.setOpaque(true);
        lPic.setBackground(new Color(220,220,220));
        
        Font fbt = new Font("Arial",Font.PLAIN,26);
        btSave.setFont(fbt);
        btCancel.setFont(fbt);
        btPic.setFont(new Font("Arial",Font.PLAIN,20));
        
        Font f = new Font("Arial",Font.PLAIN,32);
        lbName.setFont(f);
        tfName.setFont(f);
        lbCost.setFont(f);
        tfCost.setFont(f);
        lbPrice.setFont(f);
        tfPrice.setFont(f);
        lbValue.setFont(f);
        value.setFont(f);
        
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) value.getEditor();
        JFormattedTextField textField = editor.getTextField();
        NumberFormatter formatter = (NumberFormatter) textField.getFormatter();
        formatter.setAllowsInvalid(false);

        p1.setLayout(null);
        lbName.setBounds(75, 75, 100, 40);
        tfName.setBounds(200, 75, 200, 40);
        lbCost.setBounds(75, 150, 100, 40);
        tfCost.setBounds(200, 150, 200, 40);
        lbPrice.setBounds(75, 225, 100, 40);
        tfPrice.setBounds(200, 225, 200, 40);
        lbValue.setBounds(75, 300, 100, 40);
        value.setBounds(200, 300, 200, 40);
        
        lPic.setBounds(480, 30, 500, 350);
        btPic.setBounds(665, 400, 150, 50);

        btSave.setBounds(350, 500, 120, 50);
        btCancel.setBounds(550, 500, 120, 50);
//        1030 690
        p1.add(lbName);
        p1.add(tfName);
        p1.add(lbCost);
        p1.add(tfCost);
        p1.add(lbPrice);
        p1.add(tfPrice);
        p1.add(lbValue);
        p1.add(value);
        p1.add(lPic);
        p1.add(btPic);
        p1.add(btSave);
        p1.add(btCancel);

        pfr.setLayout(new BorderLayout());
        pfr.add(p1, BorderLayout.CENTER);
        
    }
    public JTextField gettfName() {
        return this.tfName;
    }
    public JTextField gettfCost() {
        return this.tfCost;
    }
    public JTextField gettfPrice() {
        return this.tfPrice;
    }
    public JButton getbtPic() {
        return this.btPic;
    }
    public JButton getbtSave() {
        return this.btSave;
    }
    public JButton getbtCancel() {
        return this.btCancel;
    }
    public JLabel getLPic() {
        return this.lPic;
    }
    public void setLPic(ImageIcon icon) {
        lPic.setIcon(icon);
    }
    public int getAmount() {
        return (int)this.value.getValue();
    }
    public void setForCancel() {
        this.tfName.setText("");
        this.tfCost.setText("");
        this.tfPrice.setText("");
        this.value.setValue(0);
        this.lPic.setIcon(null);
    }
    public JPanel getpfr() {
        return this.pfr;
    }
}