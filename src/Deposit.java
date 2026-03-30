package bank.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {

    String pin;
    JTextField textField;
    JButton deposit,back;

   public Deposit(String pin){
        this.pin=pin;


       ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
       Image i2= i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
       ImageIcon i3 = new ImageIcon(i2);
       JLabel l3= new JLabel(i3);
       l3.setBounds(0,0,1550,830 );
       add(l3);

       JLabel label1=new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT ");
       label1.setFont(new Font("System ",Font.BOLD,16));
       label1.setForeground(Color.WHITE);
       label1.setBounds(460,180,400,35);
       l3.add(label1);


       textField=new JTextField();
       textField.setBackground(new Color(65,125,128));
       textField.setForeground(Color.WHITE);
       textField.setBounds(460,230,320,25);
       textField.setFont(new Font("Raleway",Font.BOLD,22));
       l3.add(textField);


       deposit=new JButton("DEPOSIT");
       deposit.setBounds(700,362,150,35);
       deposit.setBackground(new Color(65,125,128));
       deposit.setForeground(Color.WHITE);
       deposit.addActionListener(this);
       l3.add(deposit);

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
       try {
           String amount = textField.getText();
           Date date = new Date();
           if (e.getSource()==deposit){
               if(amount.equals("")){
                   JOptionPane.showMessageDialog(null,"Please enter the Amount you want to Deposit");
               }else{
                   dbConnection con=new dbConnection();
                   CallableStatement cs =
                   con.connection.prepareCall("{CALL sp_deposit(?, ?)}");
                   cs.setString(1, pin);
                   int amountInt = Integer.parseInt(amount);
                   cs.setInt(2, amountInt);
                   cs.execute();

                   JOptionPane.showMessageDialog(null,"Rs. "+amount+" Deposited Successfully");
                   new main_Class(pin);
                   setVisible(false);
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

       new Deposit("");
    }
}
