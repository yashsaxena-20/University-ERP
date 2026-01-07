package edu.univ.erp.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import edu.univ.erp.auth.*;
import edu.univ.erp.service.*;

public class ChangePasswordDialog extends JDialog implements ActionListener
{
    JPasswordField oldP,newP,confP;
    JButton btn;

    public ChangePasswordDialog(JFrame parent)
    {
        super(parent,"Change Password",true);
        setSize(300,200);
        setLayout(new GridLayout(4,2,10,10));
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        add(new JLabel("Old Password:"));
        oldP=new JPasswordField();
        add(oldP);
        
        add(new JLabel("New Password:"));
        newP=new JPasswordField();
        add(newP);

        add(new JLabel("Confirm New:"));
        confP=new JPasswordField();
        add(confP);
        
        add(new JLabel(""));
        btn=new JButton("Change");
        btn.addActionListener(this);
        add(btn);
        
        setLocationRelativeTo(parent);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String newPass=new String(newP.getPassword());
        String confPass=new String(confP.getPassword());

        if(!newPass.equals(confPass))
        {
            JOptionPane.showMessageDialog(this,"New passwords do not match.");
            return;
        }

        if(newPass.length()<6)
        {
            JOptionPane.showMessageDialog(this,"New password is too short.");
            return;
        }

        long uid=UserSession.get().getUser().getUid();
        AuthService as=new AuthService();
        String result=as.changePassword(uid,new String(oldP.getPassword()),newPass);

        if(result.equals("SUCCESS"))
        {
            JOptionPane.showMessageDialog(this,"Password successfully updated.");
            dispose();
        }
        else if(result.equals("OLD_PASS_INCORRECT"))
        {
            JOptionPane.showMessageDialog(this,"Error: Old password was incorrect.");
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Error: Could not update password due to a server error.");
        }
    }
}