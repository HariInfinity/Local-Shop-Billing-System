package localshopbill;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AddProductFrame extends JFrame {

    String[] items;
    int[] prices;
    int[] stock;
    int[] tempStock;
    int[] tempPrices;

    public AddProductFrame(String[] items, int[] prices, int[] stock) {

        this.items = items;
        this.prices = prices;
        this.stock = stock;

        tempStock = stock.clone();
        tempPrices = prices.clone();

        setTitle("Manage Stock");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        Font titleFont = new Font("Arial", Font.BOLD, 22);
        Font headFont = new Font("Arial", Font.BOLD, 16);
        Font rowFont = new Font("Arial", Font.PLAIN, 16);

        JLabel lblTitle = new JLabel("Manage Stock");
        lblTitle.setBounds(300, 20, 300, 35);
        lblTitle.setFont(titleFont);
        add(lblTitle);

        JLabel hItem = new JLabel("ITEM");
        hItem.setBounds(100, 80, 150, 30);
        hItem.setFont(headFont);
        add(hItem);

        JLabel hQty = new JLabel("CURRENT QTY");
        hQty.setBounds(300, 80, 150, 30);
        hQty.setFont(headFont);
        add(hQty);

        JLabel hPrice = new JLabel("PRICE (₹)");
        hPrice.setBounds(470, 80, 120, 30);
        hPrice.setFont(headFont);
        add(hPrice);

        int y = 120;

        for (int i = 0; i < items.length; i++) {

            int index = i;

            JLabel lblItem = new JLabel(items[i]);
            lblItem.setBounds(100, y, 150, 30);
            lblItem.setFont(rowFont);
            add(lblItem);

            JTextField txtStock = new JTextField(String.valueOf(stock[i]));
            txtStock.setBounds(320, y, 80, 30);
            txtStock.setFont(rowFont);
            txtStock.setHorizontalAlignment(JTextField.CENTER);
            add(txtStock);

            JTextField txtPrice = new JTextField(String.valueOf(prices[i]));
            txtPrice.setBounds(480, y, 80, 30);
            txtPrice.setFont(rowFont);
            txtPrice.setHorizontalAlignment(JTextField.CENTER);
            add(txtPrice);

            DocumentListener listener = new DocumentListener() {

                void update() {
                    try {
                        int s = Integer.parseInt(txtStock.getText());
                        int p = Integer.parseInt(txtPrice.getText());

                        if (s < 0) {
                            s = 0;
                        }
                        if (p < 0) {
                            p = 0;
                        }

                        tempStock[index] = s;
                        tempPrices[index] = p;

                    } catch (Exception ignored) {
                    }
                }

                public void insertUpdate(DocumentEvent e) {
                    update();
                }

                public void removeUpdate(DocumentEvent e) {
                    update();
                }

                public void changedUpdate(DocumentEvent e) {
                    update();
                }
            };

            txtStock.getDocument().addDocumentListener(listener);
            txtPrice.getDocument().addDocumentListener(listener);

            y += 45;
        }

        JButton btnSave = new JButton("SAVE");
        btnSave.setBounds(330, 400, 140, 45);
        btnSave.setFont(new Font("Arial", Font.BOLD, 18));
        btnSave.setBackground(new Color(0, 153, 76));
        btnSave.setForeground(Color.WHITE);
        add(btnSave);

        btnSave.addActionListener(e -> {

            for (int i = 0; i < stock.length; i++) {
                stock[i] = tempStock[i];
                prices[i] = tempPrices[i];
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Stock & Prices saved successfully!",
                    "Saved",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();
        });

        setVisible(true);
    }
}
