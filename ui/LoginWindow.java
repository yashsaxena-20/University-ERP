package edu.univ.erp.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import edu.univ.erp.auth.*;
import edu.univ.erp.domain.User;
public class LoginWindow extends JFrame implements ActionListener
{
    JTextField uField;
    JPasswordField pField;
    JButton btn;
    public LoginWindow()
    {
        setTitle("ERP Login");
        setSize(400,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4,1,10,10));
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        add(new JLabel("Username:"));
        uField=new JTextField();
        add(uField);
        add(new JLabel("Password:"));
        pField=new JPasswordField();
        add(pField);
        btn=new JButton("Login");
        btn.setBackground(new Color(33,115,70));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif",Font.BOLD,14));
        // Force color visibility
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(true);
        btn.addActionListener(this);
        add(btn);
    }
    public void actionPerformed(ActionEvent e)
    {
        AuthService as=new AuthService();
        User u=as.login(uField.getText(),new String(pField.getPassword()));
        if(u==null)
        {
            JOptionPane.showMessageDialog(this,"Invalid Login");
        }
        else
        {
            UserSession.get().setUser(u);
            if(u.getRole().equals("Student"))
            {
                new StudentDashboard().setVisible(true);
            }
            else if(u.getRole().equals("Instructor"))
            {
                new InstructorDashboard().setVisible(true);
            }
            else
            {
                new AdminDashboard().setVisible(true);
            }
            dispose();
        }
    }
}