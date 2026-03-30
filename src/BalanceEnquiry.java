package bank.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener {

    JLabel label2;
    JButton balance_Enquiry,back;
    String pin ;

   public BalanceEnquiry(String pin){
         this.pin=pin;



       ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
       Image i2= i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
       ImageIcon i3 = new ImageIcon(i2);
       JLabel l3= new JLabel(i3);
       l3.setBounds(0,0,1550,830 );
       add(l3);

       JLabel label1=new JLabel("YOUR CURRENT BALANCE IS RS:  ");
       label1.setFont(new Font("System ",Font.BOLD,16));
       label1.setForeground(Color.WHITE);
       label1.setBounds(430,180,700,35);
       l3.add(label1);


       label2=new JLabel();
       label2.setFont(new Font("System ",Font.BOLD,16));
       label2.setForeground(Color.WHITE);
       label2.setBounds(430,220,400,35);
       l3.add(label2);


       back=new JButton("BACK");
       back.setBounds(700,406,150,35);
       back.setBackground(new Color(65,125,128));
       back.setForeground(Color.WHITE);
       back.addActionListener(this);
       l3.add(back);

        int balance = 0;
        try {
            dbConnection c = new dbConnection();
            ResultSet rs = c.statement.executeQuery(
    "SELECT balance FROM account WHERE pin_hash = '" + pin + "'"
);
if (rs.next()) {
    balance = rs.getInt("balance");
}

    } catch (Exception e) {
    e.printStackTrace();
}

       label2.setText("" + balance);
        


       setSize(1550,1080);
       setLocation(0,0);
       setLayout(null);
       setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       new main_Class(pin);
        setVisible(false);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("");
    }
}
