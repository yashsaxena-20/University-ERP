package edu.univ.erp.data;
import edu.univ.erp.domain.Student;
import java.sql.*;
public class StudentRepository
{
    public void add(Student o)
    {
        try
        {
            Connection c=DbConnection.getErp();
            PreparedStatement s=c.prepareStatement("INSERT INTO students (user_id,roll_no,program,year) VALUES (?,?,?,?)");
            s.setLong(1,o.getUid());
            s.setString(2,o.getRoll());
            s.setString(3,o.getProg());
            s.setInt(4,o.getYear());
            s.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}