package localshopbill;

import javax.swing.*;
import java.awt.*;

public class AddToCartFrame extends JFrame {

    String[] items;
    int[] prices;
    int[] selectedQty;

    public AddToCartFrame(String[] items, int[] prices, int[] selectedQty) {

        this.items = items;
        this.prices = prices;
        this.selectedQty = selectedQty;

        setTitle("Add To Cart");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(new Color(245, 245, 245));
        add(mainPanel);

        Font titleFont = new Font("Arial", Font.BOLD, 24);
        Font headFont = new Font("Arial", Font.BOLD, 16);
        Font rowFont = new Font("Arial", Font.PLAIN, 16);
        Font priceFont = new Font("Arial", Font.BOLD, 16);

        JLabel lblTitle = new JLabel("Order Summary");
        lblTitle.setBounds(60, 25, 300, 35);
        lblTitle.setFont(titleFont);
        mainPanel.add(lblTitle);

        JSeparator sep = new JSeparator();
        sep.setBounds(60, 70, 760, 2);
        mainPanel.add(sep);

        JLabel hItem = new JLabel("ITEM");
        hItem.setBounds(80, 90, 150, 30);
        hItem.setFont(headFont);
        mainPanel.add(hItem);

        JLabel hQty = new JLabel("QTY");
        hQty.setBounds(300, 90, 80, 30);
        hQty.setFont(headFont);
        mainPanel.add(hQty);

        JLabel hPrice = new JLabel("PRICE (₹)");
        hPrice.setBounds(430, 90, 120, 30);
        hPrice.setFont(headFont);
        mainPanel.add(hPrice);

        JLabel hTotal = new JLabel("TOTAL (₹)");
        hTotal.setBounds(620, 90, 150, 30);
        hTotal.setFont(headFont);
        mainPanel.add(hTotal);

        int y = 140;
        double grandTotal = 0;

        for (int i = 0; i < items.length; i++) {
            if (selectedQty[i] > 0) {

                JLabel lblItem = new JLabel(items[i]);
                lblItem.setBounds(80, y, 150, 30);
                lblItem.setFont(rowFont);
                mainPanel.add(lblItem);

                JLabel lblQty = new JLabel(String.valueOf(selectedQty[i]));
                lblQty.setBounds(300, y, 80, 30);
                lblQty.setFont(rowFont);
                mainPanel.add(lblQty);

                JLabel lblPrice = new JLabel("₹" + prices[i]);
                lblPrice.setBounds(430, y, 120, 30);
                lblPrice.setFont(priceFont);
                mainPanel.add(lblPrice);

                int itemTotal = selectedQty[i] * prices[i];
                grandTotal += itemTotal;

                JLabel lblItemTotal = new JLabel("₹" + itemTotal);
                lblItemTotal.setBounds(620, y, 150, 30);
                lblItemTotal.setFont(priceFont);
                mainPanel.add(lblItemTotal);

                y += 45;
            }
        }

        JPanel totalPanel = new JPanel(null);
        totalPanel.setBounds(480, y + 30, 300, 60);
        totalPanel.setBackground(new Color(232, 245, 233));
        totalPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 150, 0), 2));
        mainPanel.add(totalPanel);

        JLabel lblTotal = new JLabel("TOTAL AMOUNT : ₹" + grandTotal);
        lblTotal.setBounds(20, 15, 260, 30);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 18));
        lblTotal.setForeground(new Color(0, 128, 0));
        totalPanel.add(lblTotal);

        JButton btnHome = new JButton("HOME PAGE");
        btnHome.setBounds(80, y + 30, 180, 45);
        btnHome.setFont(new Font("Arial", Font.BOLD, 16));
        btnHome.setBackground(new Color(33, 150, 243));
        btnHome.setForeground(Color.WHITE);
        btnHome.setFocusPainted(false);
        btnHome.addActionListener(e -> {
            dispose();
        });

        mainPanel.add(btnHome);

        setVisible(true);
    }
}
