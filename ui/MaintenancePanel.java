package edu.univ.erp.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import edu.univ.erp.service.MaintenanceService;
public class MaintenancePanel extends JPanel implements ActionListener
{
    JLabel status;
    JButton toggle;
    MaintenanceService ms;
    public MaintenancePanel()
    {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        ms=new MaintenanceService();
        status=new JLabel("Status: "+(ms.isOp()?"ON":"OFF"));
        status.setFont(new Font("SansSerif",Font.BOLD,24));
        add(status);
        toggle=new JButton("Toggle Mode");
        toggle.setBackground(new Color(33,115,70));
        toggle.setForeground(Color.WHITE);
        toggle.setFont(new Font("SansSerif",Font.BOLD,18));
        toggle.addActionListener(this);
        add(toggle);
    }
    public void actionPerformed(ActionEvent e)
    {
        boolean curr=ms.isOp();
        ms.setMode(!curr);
        status.setText("Status: "+(!curr?"ON":"OFF"));
        JOptionPane.showMessageDialog(this,"Maintenance is now "+(!curr?"ON":"OFF"));
    }
}