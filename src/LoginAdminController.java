import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class LoginAdminController implements ActionListener, MouseListener {
    private LoginAdminView lav;
    public LoginAdminController(LoginAdminView lav) {
        this.lav = lav;
        init();
    }
    public void init() {
        lav.getbLogin().addActionListener(this);
        lav.getpExit().addMouseListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(lav.getbLogin())) {
            String username = lav.gettfUserName().getText();
            String password = new String(lav.getpfPassword().getPassword());
            if (username.equals("Admin1234")) {
                if (password.equals("admin1234")) {
                    lav.getfr().dispose();
                    new AdminView();
                }
                else {
                JOptionPane.showMessageDialog(null, "Wrong Password", "Failed Login", JOptionPane.ERROR_MESSAGE);
                lav.setVoidTextField();
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Wrong Username", "Failed Login", JOptionPane.ERROR_MESSAGE);
                lav.setVoidTextField();
            }
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        lav.getpExit().setBackground(Color.red);
        lav.getlExit().setForeground(Color.WHITE);
    }
    @Override
    public void mouseExited(MouseEvent e) {
        lav.getlExit().setForeground(Color.BLACK);
        lav.getpExit().setBackground(new Color(125, 232, 232));
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.exit(0);
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
}
