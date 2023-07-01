import java.awt.*;
import java.net.URL;
import javax.swing.*;
public class LoginAdminView {
    private JFrame fr;
    private JLabel lUserName, lPassword, lExit, lLogin, lLogo;
    private JTextField tfUserName;
    private JPasswordField pfPassword;
    private JPanel pLeft, pExit, pUsername, pPassword, pbLogin;
    private JButton bLogin;
    public LoginAdminView() {
        ImageIcon icon = null;
        URL imageURL = LoginAdminView.class.getResource("Image/logo.png");
        if (imageURL != null) {
            icon = new ImageIcon(imageURL);
        }
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(350,300,Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        fr = new JFrame();
        lUserName =  new JLabel("Username:    ");
        tfUserName = new JTextField(15);
        lPassword = new JLabel("Password:     ");
        pfPassword = new JPasswordField(15);
        pLeft = new JPanel();
        pExit = new JPanel();
        pUsername = new JPanel();
        pPassword = new JPanel();
        pbLogin = new JPanel();
        lLogin = new JLabel("Login");
        bLogin = new JButton("Login");
        lExit = new JLabel("X");
        lLogo = new JLabel(icon);
        Font fontExit = new Font("TimesNewRoman", Font.CENTER_BASELINE, 16);
        lExit.setFont(fontExit);
        
        pLeft.setBounds(0, 0, 400, 600);
        pLeft.setBackground(new Color(125, 232, 232));
        
        pExit.add(lExit);
        pExit.setBounds(750, 0, 40, 30);
        pExit.setBackground(new Color(125, 232, 232));
        
        Font fontText = new Font("Arial", Font.PLAIN, 18);
        
        lLogo.setBackground(new Color(125, 232, 232));
        lLogo.setBounds(25, 100, 350, 300);
        
        lLogin.setFont(new Font("Arial", Font.BOLD, 32));
        lLogin.setBounds(550, 125, 90, 40);
        lLogin.setBackground(Color.WHITE);
        lLogin.setOpaque(true);

        lUserName.setFont(fontText);
        tfUserName.setFont(fontText);
        pUsername.setLayout(new FlowLayout());
        pUsername.add(lUserName);
        pUsername.add(tfUserName);
        pUsername.setBackground(Color.WHITE);
        pUsername.setBounds(425, 200, 350, 40);
        
        lPassword.setFont(fontText);
        pfPassword.setFont(fontText);
        pPassword.setLayout(new FlowLayout());
        pPassword.add(lPassword);
        pPassword.add(pfPassword);
        pPassword.setBackground(Color.WHITE);
        pPassword.setBounds(425, 250, 350, 40);
        
        bLogin.setFont(fontText);
        pbLogin.add(bLogin);
        pbLogin.setBackground(Color.WHITE);
        pbLogin.setBounds(550, 300, 100, 40);
        
        fr.add(lLogo);
        fr.add(lLogin);
        fr.add(pLeft);
        fr.add(pExit); 
        fr.add(pUsername);
        fr.add(pPassword);
        fr.add(pbLogin);
        
        fr.setUndecorated(true);
        fr.getContentPane().setBackground(Color.WHITE);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(new Dimension(800,500));
        fr.setLayout(null);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
        new LoginAdminController(this);
    }
    public JLabel getlExit() {
        return this.lExit;
    }
    public JButton getbLogin() {
        return this.bLogin;
    }
    public JFrame getfr () {
        return this.fr;
    }
    public JPanel getpExit() {
        return this.pExit;
    }
    public JTextField gettfUserName() {
        return this.tfUserName;
    }
    public JPasswordField getpfPassword() {
        return this.pfPassword;
    }
    public void setVoidTextField() {
        this.tfUserName.setText("");
        this.pfPassword.setText("");
    }
}
