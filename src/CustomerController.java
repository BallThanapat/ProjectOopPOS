import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CustomerController implements ActionListener, MouseListener{
    private CustomerView cv;
    private Cart c;
    public CustomerController(CustomerView cv){
        this.cv = cv;
        //cv = new CustomerView();
        init();
    }
    public void init(){
        cv.getPurchase().addMouseListener(this);
        cv.getMyaccount().addActionListener(this);
        cv.getLogo().addMouseListener(this);
        cv.getAccount().addMouseListener(this);
        cv.getLogout().addActionListener(this);
        for (int i=0; i<cv.getArrayList().size(); i++) {
            ((JLabel)cv.getArrayList().get(i)).addMouseListener(this);
        }
        cv.getC().getbPay().addActionListener(this);
        cv.getC().getbDel().addActionListener(this);
        cv.getC().getbClear().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(cv.getLogout())){
            cv.getFr().dispose();
            //new LoginAdminView();
        }else if(e.getSource().equals(cv.getC().getbPay())){
            cv.getC().getPanelPay().setVisible(true);
            cv.getC().setTotal();
            cv.getC().getbDel().setEnabled(false);
            cv.getC().getbPay().setEnabled(false);
            cv.getC().getbClear().setVisible(true);
        }else if(e.getSource().equals(cv.getC().getbDel())){
            cv.getC().removeSelect(cv.getC().getSelect());
        }else if(e.getSource().equals(cv.getC().getbClear())){
            cv.getC().getbPay().setEnabled(true);
            cv.getC().getbDel().setEnabled(true);
            cv.getC().getbClear().setVisible(false);
            cv.getC().getPanelPay().setVisible(false);
            cv.getC().setClear();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(cv.getPurchase())){
            cv.getC().getPanel().setVisible(true);
            cv.getPanelItem().setVisible(false);
            cv.getspProduct().setVisible(false);
        }
        else if(e.getSource().equals(cv.getLogo())){
            cv.getC().getPanel().setVisible(false);
            cv.getPanelItem().setVisible(true);
            cv.getspProduct().setVisible(true);
        }
        else if(cv.getArrayList().indexOf(e.getSource())!=-1){
            cv.getC().addCart((JLabel)cv.getArrayList().get(cv.getArrayList().indexOf(e.getSource())));
        }else if(e.getSource().equals(cv.getAccount())){
            cv.clickPopup();
        }
            
    }
    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource().equals(cv.getLogo())){
            cv.setlbLogo(Color.GRAY);
        }else if(e.getSource().equals(cv.getAccount())){
            cv.setlbAccount(Color.GRAY);
        }else if (cv.getArrayList().indexOf(e.getSource())!=-1){
            cv.setpPanelItem(cv.getArrayList().indexOf(e.getSource()), Color.GRAY);
        }else if(e.getSource().equals(cv.getPurchase())){
            cv.setlbPurchase(Color.GRAY);
        }else if(e.getSource().equals(cv.getLogo())){
            cv.setlbLogo(Color.GRAY);
        }
            
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource().equals(cv.getPurchase())){
            cv.setlbPurchase(new Color(102,178,255));
        }else if(e.getSource().equals(cv.getAccount())){
            cv.setlbAccount(new Color(102,178,255));
        }
        else if(e.getSource().equals(cv.getLogo())){
            cv.setlbLogo(new Color(102, 178, 255));
        }else if(cv.getArrayList().indexOf(e.getSource())!=-1){
            cv.setpPanelItem(cv.getArrayList().indexOf(e.getSource()), Color.white);
        }
    }
}