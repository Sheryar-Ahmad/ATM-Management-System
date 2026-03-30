package bank.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup2 extends JFrame implements ActionListener {
    JComboBox comboBox,comboBox2,comboBox3,comboBox4,comboBox5;
    JRadioButton r1,r2,e1,e2;
    JTextField textCnic;
    JButton next;
    String formno;

   public Signup2(String formno){

       super("APPLICATION FORM-2");

       this.formno=formno;

       ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
       Image i2=i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
       ImageIcon i3=new ImageIcon(i2);
       JLabel image=new JLabel(i3);
       image.setBounds(150,5,100,100);
       add(image);


       JLabel l1=new JLabel("Page 2");
       l1.setFont(new Font("Raleway",Font.BOLD,22));
       l1.setBounds(300,30,600,40);
       add(l1);

       JLabel l2=new JLabel("Additional Details");
       l2.setFont(new Font("Raleway",Font.BOLD,22));
       l2.setBounds(300,60,600,40);
       add(l2);


       JLabel l3=new JLabel("Religion : ");
       l3.setFont(new Font("Raleway",Font.BOLD,18));
       l3.setBounds(100,120,100,30);
       add(l3);

       String religion[]={"Muslim","Christan","Hindu","Other"};
       comboBox=new JComboBox(religion);
       comboBox.setBackground(new Color(252,208,76));
       comboBox.setBounds(350,120,320,30);
       comboBox.setFont(new Font("Raleway",Font.BOLD,14));
       add(comboBox);


       JLabel l4=new JLabel("Category : ");
       l4.setFont(new Font("Raleway",Font.BOLD,18));
       l4.setBounds(100,170,100,30);
       add(l4);

       String Category[]={"General","Minority", "Overseas Pakistani", "Government Employee", "Business","Student","Other"};
       comboBox2=new JComboBox(Category);
       comboBox2.setBackground(new Color(252,208,76));
       comboBox2.setBounds(350,170,320,30);
       comboBox2.setFont(new Font("Raleway",Font.BOLD,14));
       add(comboBox2);


       JLabel l5=new JLabel("Income : ");
       l5.setFont(new Font("Raleway",Font.BOLD,18));
       l5.setBounds(100,220,100,30);
       add(l5);

       String income[]={ "Null","<1,50,000","<2,50,000","5,00,000", "Upto 10,00,000", "Above 1 Million"};
       comboBox3=new JComboBox(income);
       comboBox3.setBackground(new Color(252,208,76));
       comboBox3.setBounds(350,220,320,30);
       comboBox3.setFont(new Font("Raleway",Font.BOLD,14));
       add(comboBox3);



       JLabel l6=new JLabel("Education : ");
       l6.setFont(new Font("Raleway",Font.BOLD,18));
       l6.setBounds(100,270,110,30);
       add(l6);

       String education[]={ "Non-Graduate","Graduate","Post-Graduate","Doctrate", "Other"};
       comboBox4=new JComboBox(education);
       comboBox4.setBackground(new Color(252,208,76));
       comboBox4.setBounds(350,270,320,30);
       comboBox4.setFont(new Font("Raleway",Font.BOLD,14));
       add(comboBox4);


       JLabel l7=new JLabel("Occuption : ");
       l7.setFont(new Font("Raleway",Font.BOLD,18));
       l7.setBounds(100,340,110,30);
       add(l7);

       String Occuptaion[]={ "Salaried","Self-Employed","Business","Student", "Retired","Other"};
       comboBox5=new JComboBox(Occuptaion);
       comboBox5.setBackground(new Color(252,208,76));
       comboBox5.setBounds(350,340,320,30);
       comboBox5.setFont(new Font("Raleway",Font.BOLD,14));
       add(comboBox5);

       JLabel l8=new JLabel("CNIC/B-Form No :  ");
       l8.setFont(new Font("Raleway",Font.BOLD,18));
       l8.setBounds(100,390,200,30);
       add(l8);

       textCnic=new JTextField();
       textCnic.setFont(new Font("Raleway",Font.BOLD,14));
       textCnic.setBackground(new Color(252,208,76));
       textCnic.setBounds(350,390,320,30);
       add(textCnic);


       JLabel l9=new JLabel("Senior Citizen :  ");
       l9.setFont(new Font("Raleway",Font.BOLD,18));
       l9.setBounds(100,440,200,30);
       add(l9);

       r1=new JRadioButton("Yes");
       r1.setFont(new Font("Raleway",Font.BOLD,14));
       r1.setBackground(new Color(252,208,76));
       r1.setBounds(350,440,100,30);
       add(r1);

       r2=new JRadioButton("No");
       r2.setFont(new Font("Raleway",Font.BOLD,14));
       r2.setBackground(new Color(252,208,76));
       r2.setBounds(460,440,100,30);
       add(r2);

       ButtonGroup group =new ButtonGroup();
       group.add(r1);
       group.add(r2);

       JLabel l10=new JLabel("Existing Account :  ");
       l10.setFont(new Font("Raleway",Font.BOLD,18));
       l10.setBounds(100,490,200,30);
       add(l10);

       e1=new JRadioButton("Yes");
       e1.setFont(new Font("Raleway",Font.BOLD,14));
       e1.setBackground(new Color(252,208,76));
       e1.setBounds(350,490,100,30);
       add(e1);

       e2=new JRadioButton("No");
       e2.setFont(new Font("Raleway",Font.BOLD,14));
       e2.setBackground(new Color(252,208,76));
       e2.setBounds(460,490,100,30);
       add(e2);

       ButtonGroup egroup =new ButtonGroup();
       egroup.add(e1);
       egroup.add(e2);


       JLabel l11=new JLabel("Form No"+formno);
       l11.setFont(new Font("Raleway",Font.BOLD,14));
       l11.setBounds(670,10,100,30);
       add(l11);


       next=new JButton("Next");
       next.setFont(new Font("Raleway",Font.BOLD,14));
       next.setBackground(Color.BLACK);
       next.addActionListener(this);
       next.setForeground(Color.WHITE);
       next.setBounds(570,640,100,30);
       add(next);


        setSize(850,750);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(252,208,76));
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String rel=(String)comboBox.getSelectedItem();
        String cate=(String)comboBox2.getSelectedItem();
        String inc=(String)comboBox3.getSelectedItem();
        String edu=(String)comboBox4.getSelectedItem();
        String occ=(String)comboBox5.getSelectedItem();
        String cnic=textCnic.getText();
        String scitizen=null;
        if(r1.isSelected()){
            scitizen="Yes";
        }
        else if(r2.isSelected()){
            scitizen="No";
        }

        String eAccount=null;
        if(e1.isSelected()){
            eAccount="Yes";
        }
        else if(e2.isSelected()){
            eAccount="No";
        }


    try{
            if(textCnic.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Please fill the form correctly");
            }else{
                dbConnection con1=new dbConnection();
                String q="insert into customer_details(religion,category,income,education,occupation,cnic,senior_citizen,existing_account) values('"+rel+"','"+cate+"','"+inc+"','"+edu+"','"+occ+"','"+cnic+"','"+scitizen+"','"+eAccount+"')";
                con1.statement.executeUpdate(q);
                new Signup3(formno);
                setVisible(false);
            }


    }catch (Exception E){
        E.printStackTrace();
    }


   }

    public static void main(String[] args) {
    new Signup2("");
    }
}
