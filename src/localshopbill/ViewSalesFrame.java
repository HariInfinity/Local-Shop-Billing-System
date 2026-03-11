package localshopbill;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ViewSalesFrame extends JFrame {

    public ViewSalesFrame(
            HashMap<String, Integer> purchaseCount,
            HashMap<String, Double> totalAmount
    ) {

        setTitle("View Sales");
        setSize(850, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        Font headFont = new Font("Arial", Font.BOLD, 18);
        Font rowFont = new Font("Arial", Font.PLAIN, 16);

        JLabel lblTitle = new JLabel("Sales Summary");
        lblTitle.setBounds(60, 25, 300, 35);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblTitle);

        JSeparator sep = new JSeparator();
        sep.setBounds(60, 70, 720, 2);
        add(sep);

        JLabel hMobile = new JLabel("CUSTOMER MOBILE");
        hMobile.setBounds(80, 95, 200, 30);
        hMobile.setFont(headFont);
        add(hMobile);

        JLabel hTimes = new JLabel("TOTAL PURCHASES");
        hTimes.setBounds(350, 95, 180, 30);
        hTimes.setFont(headFont);
        add(hTimes);

        JLabel hAmount = new JLabel("TOTAL AMOUNT (₹)");
        hAmount.setBounds(580, 95, 200, 30);
        hAmount.setFont(headFont);
        add(hAmount);

        int y = 140;
        double grandSales = 0;

        for (Map.Entry<String, Integer> entry : purchaseCount.entrySet()) {

            String mobile = entry.getKey();
            int times = entry.getValue();
            double amount = totalAmount.getOrDefault(mobile, 0.0);

            JLabel lblMobile = new JLabel(mobile);
            lblMobile.setBounds(80, y, 200, 30);
            lblMobile.setFont(rowFont);
            add(lblMobile);

            JLabel lblTimes = new JLabel(String.valueOf(times));
            lblTimes.setBounds(390, y, 120, 30);
            lblTimes.setFont(rowFont);
            add(lblTimes);

            JLabel lblAmount = new JLabel("₹" + amount);
            lblAmount.setBounds(610, y, 150, 30);
            lblAmount.setFont(rowFont);
            add(lblAmount);

            grandSales += amount;
            y += 40;
        }

        JPanel totalPanel = new JPanel(null);
        totalPanel.setBounds(480, y + 30, 300, 60);
        totalPanel.setBackground(new Color(232, 245, 233));
        totalPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 150, 0), 2));
        add(totalPanel);

        JLabel lblTotalSales = new JLabel("TOTAL SALES : ₹" + grandSales);
        lblTotalSales.setBounds(20, 15, 260, 30);
        lblTotalSales.setFont(new Font("Arial", Font.BOLD, 18));
        lblTotalSales.setForeground(new Color(0, 128, 0));
        totalPanel.add(lblTotalSales);

        setVisible(true);
    }
}
