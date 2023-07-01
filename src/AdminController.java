import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class AdminController implements MouseListener, ActionListener {
    private AdminView av;
    private CheckStockView csv;
    private CashierView cv;
    public AddProduct ap;
    public DataTable dt;
    private AddProductModel apm;
    private String pathImg;
    public AdminController(AdminView av) {
        this.av = av;
        cv = new CashierView();
        addMouseLid();
        init();
        initForCSV();
    }
    public void init() {
        av.getlPic().addMouseListener(this);
        av.getmiLogout().addActionListener(this);
        av.getlMain().addMouseListener(this);
        av.getlCheckStock().addMouseListener(this);
        av.getlCashier().addMouseListener(this);
        av.getlChart().addMouseListener(this);
        av.getlPie().addMouseListener(this);
//        av.getlPromotion().addMouseListener(this);
        av.getlLogo().addMouseListener(this);
//        av.getfr().add(csv.getDP());
    }
    public void initForCSV() {
        csv = new CheckStockView();
        apm = new AddProductModel();
        ap = new AddProduct();
        dt = new DataTable();
        csv.getifTable().add(dt.getP());
        csv.getifNewP().add(ap.getpfr());
        
        csv.getLNew().addMouseListener(this);
        csv.getLOld().addMouseListener(this);
        csv.getlSub().addMouseListener(this);
        csv.getlCan().addMouseListener(this);
        csv.getlDel().addMouseListener(this);
        ap.getbtPic().addActionListener(this);
        ap.getbtSave().addActionListener(this);
        ap.getbtCancel().addActionListener(this);
        av.getfr().add(csv.getDP());
    }
    public void addMouseLid() {
        for (int i=0; i<cv.getAL().size(); i++) {
            ((JLabel)cv.getAL().get(i)).addMouseListener(this);
        }
        for (int i=0; i<cv.getpmalb().size(); i++) {
            ((JButton)cv.getpmalb().get(i)).addActionListener(this);
        }
        cv.getB().addActionListener(this);
        cv.getbPay().addActionListener(this);
        cv.getpmLPay().addMouseListener(this);
        cv.getpmLQuit().addMouseListener(this);
        av.getfr().add(cv.getDP());
    }
    public void refreshCSV () {
        av.getfr().remove(csv.getDP());
        csv = new CheckStockView();
        av.getfr().add(csv.getDP());
    }
    public void refreshCV () {
        cv = new CashierView();
        addMouseLid();
        av.getfr().add(cv.getDP());
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(av.getlPic())) {
            av.clickPopup();
        }
        else if (e.getSource().equals(av.getlMain())) {
            av.setMainPage();
            cv.getDP().setVisible(false);
            csv.getDP().setVisible(false);
        }
        else if (e.getSource().equals(av.getlChart())) {
            av.switchPage(true);
        }
        else if (e.getSource().equals(av.getlPie())) {
            av.switchPage(false);
        }
        else if (e.getSource().equals(av.getlCheckStock())) {
            av.setCheckStockPage();
            av.getfr().remove(csv.getDP());
            initForCSV();
            csv.setStandard(false, true, false);
            cv.getDP().setVisible(false);
            csv.getDP().setVisible(true);
        }
        else if (e.getSource().equals(av.getlCashier())) {
            av.setCashierPage();
            cv.setStandard();
            cv.getDP().setVisible(true);
            csv.getDP().setVisible(false);
        }
        else if (e.getSource().equals(av.getlLogo())) {
            av.setMainPage();
            cv.getDP().setVisible(false);
            csv.getDP().setVisible(false);
        } 
        else if (cv.getAL().indexOf(e.getSource())!=-1) {
            cv.addCart(cv.getAL().indexOf(e.getSource()));
        } 
        else if (e.getSource().equals(cv.getpmLPay())) {
            cv.returnAmount();
        }
        else if (e.getSource().equals(cv.getpmLQuit())) {
            cv.setStandard();
        }
        //CSV
        else if (e.getSource().equals(csv.getLNew())) {
            csv.getLOld().setVisible(false);
            csv.setNewBofNewp(true);
            csv.setIPVisible(false, true);
        } else if (e.getSource().equals(csv.getLOld())) {
            dt.gettbData().setEnabled(true);
            csv.showlSubAlCan();
        } else if (e.getSource().equals(csv.getlSub())) {
            dt.gettbData().setEnabled(false);
            try {
                int startRow = 0;
                int startCol = 1;
                int rowCount = dt.gettbData().getRowCount();
                int columnCount = dt.gettbData().getColumnCount();
                for (int row = 0; row < rowCount; row++) {
                    for (int col = startCol; col < columnCount; col++) {
                        double num = Double.parseDouble(((Object)dt.gettbData().getValueAt(row, col)).toString());
                    }
                }
                apm.updateExcle(dt.gettbData());
                refreshCV();
                
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please Input Only Number in Cost, Price, Amount", "Failed Edit", JOptionPane.ERROR_MESSAGE);
                csv.removeDTifTable(dt.getP());
                dt = new DataTable();
                csv.getifTable().add(dt.getP());
            }
            csv.unshowlSubAlCan();  
        } else if (e.getSource().equals(csv.getlCan())) {
            dt.gettbData().setEnabled(false);
            csv.unshowlSubAlCan();
        }  else if (e.getSource().equals(csv.getlDel())) {
            int choice = JOptionPane.showOptionDialog(null, "Are you sure to delete all data?", "Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        
            if (choice == JOptionPane.YES_OPTION) {
                new DelAllStock();
                refreshCV();
                av.setCheckStockPage();
                av.getfr().remove(csv.getDP());
                initForCSV();
                csv.setStandard(false, true, false);
                cv.getDP().setVisible(false);
                csv.getDP().setVisible(true);
                csv.unshowlSubAlCan();
            } else if (choice == JOptionPane.NO_OPTION) {
                csv.unshowlSubAlCan();
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(av.getlPic())) {
            av.setlPic(Color.GRAY);
        }
        else if (e.getSource().equals(av.getlMain())) {
            av.setlMain(Color.GRAY);
        }
        else if (e.getSource().equals(av.getlChart())) {
            av.setClChart(Color.GRAY);
        }
        else if (e.getSource().equals(av.getlPie())) {
            av.setClPie(Color.GRAY);
        }
        else if (e.getSource().equals(av.getlCheckStock())) {
            av.setlCheckStock(Color.GRAY);
        }
        else if (e.getSource().equals(av.getlCashier())) {
            av.setlCashier(Color.GRAY);
        }
        else if (cv.getAL().indexOf(e.getSource())!=-1) {
            cv.selPColor(cv.getAL().indexOf(e.getSource()), Color.GRAY);
        }
        else if (e.getSource().equals(cv.getpmLPay())) {
            cv.setColorpmLPay(Color.GRAY);
        }
        else if (e.getSource().equals(cv.getpmLQuit())) {
            cv.setColorpmLQuit(Color.GRAY);
        }
        //csv
        else if (e.getSource().equals(csv.getLNew())) {
            csv.setLNewColor(Color.GRAY);
        } else if (e.getSource().equals(csv.getLOld())) {
            csv.setLOldColor(Color.GRAY);
        } else if (e.getSource().equals(csv.getlSub())) {
            csv.setLSubColor(Color.GRAY);
        } else if (e.getSource().equals(csv.getlCan())) {
            csv.setLCanColor(Color.GRAY);
        } else if (e.getSource().equals(csv.getlDel())) {
            csv.setclDel(Color.GRAY);
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(av.getlPic())) {
            av.setlPic(new Color(102,178,255));
        }
        else if (e.getSource().equals(av.getlMain())) {
            av.setlMain(Color.WHITE);
        }
        else if (e.getSource().equals(av.getlChart())) {
            av.setClChart(Color.WHITE);
        }
        else if (e.getSource().equals(av.getlPie())) {
            av.setClPie(Color.WHITE);
        }
        else if (e.getSource().equals(av.getlCheckStock())) {
            av.setlCheckStock(Color.WHITE);
        }
        else if (e.getSource().equals(av.getlCashier())) {
            av.setlCashier(Color.WHITE);
        }
        else if (cv.getAL().indexOf(e.getSource())!=-1) {
            cv.selPColor(cv.getAL().indexOf(e.getSource()), Color.WHITE);
        }
        else if (e.getSource().equals(cv.getpmLPay())) {
            cv.setColorpmLPay(new Color(176,224,230));
        }
        else if (e.getSource().equals(cv.getpmLQuit())) {
            cv.setColorpmLQuit(Color.GRAY);
        }
        //csv
        else if (e.getSource().equals(csv.getLNew())) {
            csv.setLNewColor(Color.WHITE);
        } else if (e.getSource().equals(csv.getLOld())) {
            csv.setLOldColor(Color.WHITE);
        } else if (e.getSource().equals(csv.getlSub())) {
            csv.setLSubColor(Color.WHITE);
        } else if (e.getSource().equals(csv.getlCan())) {
            csv.setLCanColor(Color.WHITE);
        } else if (e.getSource().equals(csv.getlDel())) {
            csv.setclDel(Color.WHITE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(av.getmiLogout())) {
            av.getfr().dispose();
            new LoginAdminView();
        } else if (e.getSource().equals(cv.getB())) {
            cv.removeSelect(cv.getSelect());
        } else if (e.getSource().equals(cv.getbPay())) {
            cv.payCash();
        } else if (cv.getpmalb().indexOf(e.getSource())!=-1) {
            if (e.getSource().equals(cv.getpmalb().get(10))) {
                cv.claerStr();
            }
            else if (e.getSource().equals(cv.getpmalb().get(11))) {
                cv.delStr();
            }
            else {
                cv.sendStr(((JButton)e.getSource()).getText()+"");
            }
        }
        //csv
        else if (e.getSource().equals(ap.getbtPic())) {
            JFileChooser fc = new JFileChooser();
            fc.removeChoosableFileFilter(fc.getFileFilter());
            FileNameExtensionFilter f = new FileNameExtensionFilter("Picture File (.png)", "png", "jpg");
            fc.setFileFilter(f);
            fc.showOpenDialog(ap.getpfr());
            File fg = fc.getSelectedFile();
            pathImg = fg.getAbsolutePath();
            BufferedImage image = null;
            try {
                image = ImageIO.read(fg);
            } catch (IOException ex) {}
            Image newImg = image.getScaledInstance(500, 350,Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(newImg);
            ap.setLPic(icon);
        } else if (e.getSource().equals(ap.getbtCancel())) {
            ap.setForCancel();
            csv.removeDTifTable(dt.getP());
            dt = new DataTable();
            csv.getifTable().add(dt.getP());
            csv.setStandard(false, true, false);
            csv.getLOld().setVisible(true);
        } else if (e.getSource().equals(ap.getbtSave())) {
            if (ap.gettfName().getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Input Name", "Failed AddStock", JOptionPane.ERROR_MESSAGE);
            } else if (ap.gettfCost().getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Input Cost", "Failed AddStock", JOptionPane.ERROR_MESSAGE);
            } else if (ap.gettfPrice().getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Input Price", "Failed AddStock", JOptionPane.ERROR_MESSAGE);
            } else if (ap.getLPic().getIcon() == null) {
                JOptionPane.showMessageDialog(null, "Please Input Picture", "Failed AddStock", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    String name = ap.gettfName().getText();
                    double cost = Double.parseDouble(ap.gettfCost().getText());
                    double price = Double.parseDouble(ap.gettfPrice().getText());
                    int amount = ap.getAmount();
                    apm.AddNewData(name, cost, price, amount, pathImg);
                    ap.setForCancel();
                    csv.removeDTifTable(dt.getP());
                    dt = new DataTable();
                    csv.getifTable().add(dt.getP());
                    csv.setStandard(false, true, false);
                    csv.getLOld().setVisible(true);
                    refreshCV();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please Input Only Int/Double", "Failed AddStock", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
