package edu.univ.erp.data;

import edu.univ.erp.domain.Instructor;
import java.sql.*;

public class InstructorRepository
{
    public void add(Instructor o)
    {
        try
        {
            Connection c=DbConnection.getErp();
            PreparedStatement s=c.prepareStatement("INSERT INTO instructors (user_id,department) VALUES (?,?)");
            s.setLong(1,o.getUid());
            s.setString(2,o.getDept());
            s.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}