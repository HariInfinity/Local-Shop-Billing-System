package localshopbill;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {

    String[] items = {"Rice", "Sugar", "Oil", "Wheat", "Milk"};
    int[] prices = {50, 40, 120, 60, 30};
    int[] qty = {0, 0, 0, 0, 0};
    int[] stock = {20, 10, 0, 15, 5};

    JButton btnNewBill, btnAddProduct, btnViewSales, btnExit;

    public static void main(String[] args) {
        new MainFrame();
    }

    public MainFrame() {
        Color blueHover = new Color(33, 150, 243);
        Color redHover = new Color(211, 47, 47);

        setTitle("Local Shop Billing System");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        int btnWidth = 150;
        int btnHeight = 150;
        int gap = 5;

        Font btnFont = new Font("Arial", Font.BOLD, 16);

        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/newBill.png"));
        Image img = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(img);

        btnNewBill = new JButton("New Bill");
        btnNewBill.setIcon(scaledIcon);
        btnNewBill.setHorizontalTextPosition(SwingConstants.CENTER);
        btnNewBill.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnNewBill.setIconTextGap(15);
        btnNewBill.setBounds(75, 40, btnWidth, btnHeight);
        btnNewBill.setFont(btnFont);
        btnNewBill.addActionListener(e -> {
            int[] qty = {0, 0, 0, 0, 0};
            new NewBillFrame(items, prices, qty, stock);
        });
        add(btnNewBill);

        ImageIcon iconAdd = new ImageIcon(getClass().getResource("/resources/addProduct.png"));

        Image imgAdd = iconAdd.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon scaledIconAdd = new ImageIcon(imgAdd);

        btnAddProduct = new JButton("Add Product");
        btnAddProduct.setIcon(scaledIconAdd);

        btnAddProduct.setHorizontalTextPosition(SwingConstants.CENTER);
        btnAddProduct.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnAddProduct.setIconTextGap(15);
        btnAddProduct.setMargin(new java.awt.Insets(10, 10, 10, 10));
        btnAddProduct.setBounds(240, 40, btnWidth, btnHeight);
        btnAddProduct.setFont(btnFont);
        btnAddProduct.addActionListener(e -> {
            new AddProductFrame(items, prices, stock);
        });

        add(btnAddProduct);

        ImageIcon iconView = new ImageIcon(getClass().getResource("/resources/viewSales.png"));
        Image imgView = iconView.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon scaledIconView = new ImageIcon(imgView);

        btnViewSales = new JButton("View Sales");
        btnViewSales.setIcon(scaledIconView);

        btnViewSales.setHorizontalTextPosition(SwingConstants.CENTER);
        btnViewSales.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnViewSales.setIconTextGap(15);
        btnViewSales.setMargin(new java.awt.Insets(10, 10, 10, 10));
        btnViewSales.setBounds(75, 200, btnWidth, btnHeight);
        btnViewSales.setFont(btnFont);
        btnViewSales.addActionListener(e -> {
            new ViewSalesFrame(NewBillFrame.billHistory, NewBillFrame.totalSpent);
        });
        add(btnViewSales);

        ImageIcon iconExit = new ImageIcon(getClass().getResource("/resources/exit.png"));
        Image imgExit = iconExit.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon scaledIconExit = new ImageIcon(imgExit);

        btnExit = new JButton("Exit");
        btnExit.setIcon(scaledIconExit);
        btnExit.setHorizontalTextPosition(SwingConstants.CENTER);
        btnExit.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnExit.setIconTextGap(15);
        btnExit.setMargin(new java.awt.Insets(10, 10, 10, 10));
        btnExit.setBounds(240, 200, btnWidth, btnHeight);
        btnExit.setFont(btnFont);
        btnExit.addActionListener(e -> {

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to exit?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        add(btnExit);

        addHoverEffect(btnNewBill, blueHover);
        addHoverEffect(btnAddProduct, blueHover);
        addHoverEffect(btnViewSales, blueHover);
        addHoverEffect(btnExit, redHover);

        setVisible(true);
    }

    private void addHoverEffect(JButton btn, Color hoverColor) {

        Color normalColor = btn.getBackground();

        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);

        btn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(hoverColor);
                btn.setForeground(Color.WHITE);
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(normalColor);
                btn.setForeground(Color.BLACK);
                btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

}
