package bank.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.Date;

public class Withdrawl extends JFrame implements ActionListener {

    String pin;
    JTextField textField;
    JButton Withdrawl,back;

    public Withdrawl(String pin) {
        this.pin=pin;

        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2= i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3= new JLabel(i3);
        l3.setBounds(0,0,1550,830 );
        add(l3);

        JLabel label1=new JLabel("MAXIMUM WITHDRAWL IS RS.10,000 ");
        label1.setFont(new Font("System ",Font.BOLD,16));
        label1.setForeground(Color.WHITE);
        label1.setBounds(460,180,700,35);
        l3.add(label1);


        JLabel label2=new JLabel("PLEASE ENTER YOUR AMOUNT");
        label2.setFont(new Font("System ",Font.BOLD,16));
        label2.setForeground(Color.WHITE);
        label2.setBounds(460,220,400,35);
        l3.add(label2);



        textField=new JTextField();
        textField.setBackground(new Color(65,125,128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460,260,320,25);
        textField.setFont(new Font("Raleway",Font.BOLD,22));
        l3.add(textField);

        Withdrawl =new JButton("WITHDRAW");
        Withdrawl.setBounds(700,362,150,35);
        Withdrawl.setBackground(new Color(65,125,128));
        Withdrawl.setForeground(Color.WHITE);
        Withdrawl.addActionListener(this);
        l3.add(Withdrawl);

        back=new JButton("BACK");
        back.setBounds(700,406,150,35);
        back.setBackground(new Color(65,125,128));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        l3.add(back);

        setSize(1550,1080);
        setLocation(0,0);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{

            String amount=textField.getText();
            Date date=new Date();
            if(e.getSource()==Withdrawl) {
                if (textField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to withdraw");
                } else {
                    dbConnection con = new dbConnection();
                    ResultSet resultSet = con.statement.executeQuery("select * from bank where pin='" + pin + "'");
                    int balance = 0;
                    while (resultSet.next()) {
                        if (resultSet.getString("type").equals("Deposit")) {
                            balance += Integer.parseInt(resultSet.getString("amount"));
                        } else {
                            balance -= Integer.parseInt(resultSet.getString("amount"));
                        }
                    }
                    if (balance < Integer.parseInt(amount)) {
                        JOptionPane.showMessageDialog(null, "Insuffient Balance");
                        return;
                    }

                    CallableStatement cs =
                    con.connection.prepareCall("{CALL sp_withdraw(?, ?)}");
                    cs.setString(1, pin);
                    int amountInt = Integer.parseInt(amount);
                    cs.setInt(2, amountInt);
                    cs.execute();

                    JOptionPane.showMessageDialog(null, "Rs. " + amount + "Debited Successfully");
                    setVisible(false);
                    new main_Class(pin);

                }
            }else if(e.getSource()==back){
                new main_Class(pin);
                setVisible(false);
            }

        }catch (Exception E){
            E.printStackTrace();

        }

    }

    public static void main(String[] args) {
            new Withdrawl("");
    }
}
