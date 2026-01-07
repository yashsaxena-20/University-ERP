package edu.univ.erp.data;

import java.sql.*;

public class SettingsRepository
{
    public String getVal(String k)
    {
        try
        {
            Connection c=DbConnection.getErp();
            PreparedStatement s=c.prepareStatement("SELECT setting_value FROM settings WHERE setting_key=?");
            s.setString(1,k);
            ResultSet rs=s.executeQuery();
            if(rs.next())
            {
                return rs.getString(1);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "false";
    }

    public void setVal(String k,String v)
    {
        try
        {
            Connection c=DbConnection.getErp();
            PreparedStatement s=c.prepareStatement("UPDATE settings SET setting_value=? WHERE setting_key=?");
            s.setString(1,v);
            s.setString(2,k);
            s.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}