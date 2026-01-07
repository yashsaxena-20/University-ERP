package edu.univ.erp.ui;
import javax.swing.SwingUtilities;
public class Main
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new LoginWindow().setVisible(true);
            }
        });
    }
}