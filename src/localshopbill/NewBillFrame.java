package localshopbill;

import java.awt.*;
import javax.swing.*;
import java.util.HashMap;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NewBillFrame extends JFrame {

    String[] items;
    int[] prices;
    int[] qty;
    int[] stock;

    JCheckBox[] checkBoxes;
    JTextField[] qtyFields;
    JLabel[] lblAvailability;

    JLabel lblTotal, lblDiscount, lblFinal, lblStatus;
    JTextField txtMobile;

    boolean isRegular = false;

    static HashMap<String, Integer> billHistory = new HashMap<>();
    static HashMap<String, Double> totalSpent = new HashMap<>();

    public NewBillFrame(String[] items, int[] prices, int[] qty, int[] stock) {

        this.items = items;
        this.prices = prices;
        this.qty = qty;
        this.stock = stock;

        checkBoxes = new JCheckBox[items.length];
        qtyFields = new JTextField[items.length];
        lblAvailability = new JLabel[items.length];

        setTitle("New Bill");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        Font headFont = new Font("Arial", Font.BOLD, 18);
        Font itemFont = new Font("Arial", Font.PLAIN, 18);
        Font normalFont = new Font("Arial", Font.PLAIN, 14);

        JLabel lblMobile = new JLabel("Customer Mobile");
        lblMobile.setBounds(50, 20, 150, 30);
        lblMobile.setFont(normalFont);
        add(lblMobile);

        txtMobile = new JTextField();
        txtMobile.setBounds(200, 15, 250, 35);
        txtMobile.setFont(headFont);
        add(txtMobile);
        ((AbstractDocument) txtMobile.getDocument())
                .setDocumentFilter(new MobileNumberFilter());

        JButton btnCheck = new JButton("Check Customer");
        btnCheck.setBounds(470, 15, 180, 35);
        add(btnCheck);

        lblStatus = new JLabel("");
        lblStatus.setBounds(670, 20, 250, 30);
        lblStatus.setFont(headFont);
        add(lblStatus);

        btnCheck.addActionListener(e -> {
            String mobile = txtMobile.getText().trim();
            int count = billHistory.getOrDefault(mobile, 0) + 1;

            if (mobile.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter customer mobile number");
                return;
            }

            if (count >= 3) {
                isRegular = true;
                lblStatus.setText("Regular Customer (5%)");
                lblStatus.setForeground(new Color(0, 128, 0));
            } else {
                isRegular = false;
                lblStatus.setText("New Customer");
                lblStatus.setForeground(Color.BLACK);
            }
            calculate();
        });

        JLabel hItem = new JLabel("ITEM");
        hItem.setBounds(80, 80, 100, 30);
        hItem.setFont(headFont);
        add(hItem);

        JLabel hPrice = new JLabel("PRICE (₹)");
        hPrice.setBounds(250, 80, 120, 30);
        hPrice.setFont(headFont);
        add(hPrice);

        JLabel hQty = new JLabel("QTY");
        hQty.setBounds(520, 80, 80, 30);
        hQty.setFont(headFont);
        add(hQty);

        JLabel hStatus = new JLabel("STATUS");
        hStatus.setBounds(700, 80, 120, 30);
        hStatus.setFont(headFont);
        add(hStatus);

        int y = 120;

        for (int i = 0; i < items.length; i++) {

            int index = i;

            checkBoxes[i] = new JCheckBox(items[i]);
            checkBoxes[i].setBounds(80, y, 150, 30);
            checkBoxes[i].setFont(itemFont);
            add(checkBoxes[i]);

            JLabel lblPrice = new JLabel("₹" + prices[i]);
            lblPrice.setBounds(260, y, 80, 30);
            lblPrice.setFont(itemFont);
            add(lblPrice);

            JButton btnPlus = new JButton("+");
            btnPlus.setBounds(470, y, 45, 30);
            add(btnPlus);

            qtyFields[i] = new JTextField("0");
            qtyFields[i].setBounds(520, y, 50, 30);
            qtyFields[i].setHorizontalAlignment(JTextField.CENTER);
            add(qtyFields[i]);

            JButton btnMinus = new JButton("-");
            btnMinus.setBounds(575, y, 45, 30);
            add(btnMinus);

            lblAvailability[i] = new JLabel();
            lblAvailability[i].setBounds(700, y, 150, 30);
            lblAvailability[i].setFont(normalFont);
            add(lblAvailability[i]);

            updateAvailability(i);

            btnPlus.addActionListener(e -> {
                if (stock[index] > 0) {
                    qty[index]++;
                    stock[index]--;
                    qtyFields[index].setText(String.valueOf(qty[index]));
                    checkBoxes[index].setSelected(true);
                    updateAvailability(index);
                    calculate();
                }
            });

            btnMinus.addActionListener(e -> {
                if (qty[index] > 0) {
                    qty[index]--;
                    stock[index]++;
                    qtyFields[index].setText(String.valueOf(qty[index]));
                    updateAvailability(index);
                    calculate();
                }
            });

            y += 45;
        }

        JButton btnAddToCart = new JButton("ADD TO CART");
        btnAddToCart.setBounds(470, y + 10, 200, 40);
        btnAddToCart.setFont(new Font("Arial", Font.BOLD, 18));
        btnAddToCart.setBackground(new Color(0, 153, 76));
        btnAddToCart.setForeground(Color.WHITE);
        add(btnAddToCart);

        btnAddToCart.addActionListener(e -> {
            if (!txtMobile.getText().isEmpty()) {
                setVisible(false);
            }
            String mobile = txtMobile.getText().trim();
            if (mobile.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter customer mobile number");
                return;
            }

            billHistory.put(mobile, billHistory.getOrDefault(mobile, 0) + 1);

            double billTotal = 0;
            for (int i = 0; i < items.length; i++) {
                billTotal += qty[i] * prices[i];
            }

            totalSpent.put(
                    mobile,
                    totalSpent.getOrDefault(mobile, 0.0) + billTotal
            );

            new AddToCartFrame(items, prices, qty);
        });

        lblTotal = new JLabel("Total: ₹0");
        lblTotal.setBounds(470, y + 60, 300, 30);
        lblTotal.setFont(headFont);
        add(lblTotal);

        lblDiscount = new JLabel("Discount: ₹0");
        lblDiscount.setBounds(470, y + 90, 300, 30);
        lblDiscount.setFont(headFont);
        add(lblDiscount);

        lblFinal = new JLabel("Final Amount: ₹0");
        lblFinal.setBounds(470, y + 120, 350, 30);
        lblFinal.setFont(headFont);
        add(lblFinal);

        setVisible(true);
    }

    private void updateAvailability(int i) {
        if (stock[i] > 0) {
            lblAvailability[i].setText("Available");
            lblAvailability[i].setForeground(new Color(0, 128, 0));
        } else {
            lblAvailability[i].setText("Out Of Stock");
            lblAvailability[i].setForeground(Color.RED);
            qty[i] = 0;
            qtyFields[i].setText("0");
            checkBoxes[i].setSelected(false);
        }
    }

    private void calculate() {
        double total = 0;
        for (int i = 0; i < items.length; i++) {
            total += qty[i] * prices[i];
        }

        double discount = isRegular ? total * 0.05 : 0;
        double finalAmt = total - discount;

        lblTotal.setText("Total: ₹" + total);
        lblDiscount.setText("Discount: ₹" + discount);
        lblFinal.setText("Final Amount: ₹" + finalAmt);
    }

    class MobileNumberFilter extends DocumentFilter {

        private final int maxLength = 10;

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {

            if (string == null) {
                return;
            }

            if (string.matches("\\d+")
                    && fb.getDocument().getLength() + string.length() <= maxLength) {

                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {

            if (text == null) {
                return;
            }

            if (text.matches("\\d*")
                    && fb.getDocument().getLength() - length + text.length() <= maxLength) {

                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
}
