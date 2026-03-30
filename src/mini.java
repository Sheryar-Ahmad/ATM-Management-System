package bank.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class mini extends JFrame implements ActionListener {

    String pin;
    JButton button;

    public mini(String pin) {
        this.pin = pin;

        getContentPane().setBackground(new Color(255, 204, 204));
        setSize(400, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel label1 = new JLabel();
        label1.setBounds(20, 140, 400, 250);
        add(label1);

        JLabel label2 = new JLabel("-------Transaction History-------");
        label2.setFont(new Font("System", Font.BOLD, 15));
        label2.setBounds(85, 20, 400, 20);
        add(label2);

        JLabel label3 = new JLabel();
        label3.setBounds(20, 80, 300, 20);
        add(label3);

        JLabel label4 = new JLabel();
        label4.setBounds(20, 400, 300, 20);
        add(label4);

        // Load data safely
        loadStatement(label1, label3, label4);

        button = new JButton("Exit");
        button.setBounds(20, 500, 100, 25);
        button.addActionListener(this);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        add(button);

        setVisible(true);
    }

    private void loadStatement(JLabel label1, JLabel label3, JLabel label4) {
        dbConnection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            con = new dbConnection();

            // Get card number safely using PreparedStatement
            String cardQuery = "SELECT card_no FROM login WHERE pin = ?";
            pstmt = con.connection.prepareStatement(cardQuery);
            pstmt.setString(1, pin);
            resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                String cardNo = resultSet.getString("card_no");
                if (cardNo != null && cardNo.length() >= 16) {
                    String maskedCard = cardNo.substring(0, 4) + "XXXXXXXX" + cardNo.substring(12);
                    label3.setText("Card Number :  " + maskedCard);
                }
            }

            // Close resources before next query
            resultSet.close();
            pstmt.close();

            // Get transactions safely
            int balance = 0;
            String transQuery = "SELECT date, type, amount FROM bank WHERE pin = ?";
            pstmt = con.connection.prepareStatement(transQuery);
            pstmt.setString(1, pin);
            resultSet = pstmt.executeQuery();

            StringBuilder transactions = new StringBuilder();
            transactions.append("<html>");

            while (resultSet.next()) {
                String date = resultSet.getString("date");
                String type = resultSet.getString("type");
                String amount = resultSet.getString("amount");

                // Escape HTML special characters to prevent XSS
                date = escapeHtml(date);
                type = escapeHtml(type);
                amount = escapeHtml(amount);

                transactions.append(date)
                        .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                        .append(type)
                        .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rs.")
                        .append(amount)
                        .append("<br><br>");

                try {
                    int amt = Integer.parseInt(amount);
                    if ("Deposit".equals(type)) {
                        balance += amt;
                    } else {
                        balance -= amt;
                    }
                } catch (NumberFormatException e) {
                    // Handle invalid amount
                }
            }

            transactions.append("</html>");
            label1.setText(transactions.toString());
            label4.setText("Your Total Balance is Rs : " + balance);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading statement", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Close all resources
            try {
                if (resultSet != null) resultSet.close();
                if (pstmt != null) pstmt.close();
                // Note: dbConnection connection is managed by dbConnection class
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Helper method to escape HTML characters
    private String escapeHtml(String input) {
        if (input == null) return "";
        return input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose(); // Proper way to close the window
    }

    public static void main(String[] args) {
        // For testing with a valid PIN
        if (args.length > 0) {
            new mini(args[0]);
        } else {
            System.out.println("Please provide a PIN");
        }
    }
}