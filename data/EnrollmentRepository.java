package edu.univ.erp.data;

import edu.univ.erp.domain.Enrollment;
import java.sql.*;
import java.util.*;

public class EnrollmentRepository
{
    public List<Enrollment> getByStu(long uid)
    {
        List<Enrollment> list=new ArrayList<>();
        try
        {
            Connection c=DbConnection.getErp();
            PreparedStatement s=c.prepareStatement("SELECT * FROM enrollments WHERE student_id=?");
            s.setLong(1,uid);
            ResultSet rs=s.executeQuery();
            while(rs.next())
            {
                list.add(new Enrollment(rs.getLong("enrollment_id"),rs.getLong("student_id"),rs.getLong("section_id"),rs.getString("status")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

    public List<Enrollment> getBySec(long sid)
    {
        List<Enrollment> list=new ArrayList<>();
        try
        {
            Connection c=DbConnection.getErp();
            PreparedStatement s=c.prepareStatement("SELECT * FROM enrollments WHERE section_id=?");
            s.setLong(1,sid);
            ResultSet rs=s.executeQuery();
            while(rs.next())
            {
                list.add(new Enrollment(rs.getLong("enrollment_id"),rs.getLong("student_id"),rs.getLong("section_id"),rs.getString("status")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

    public void add(long stu,long sec)
    {
        try
        {
            Connection c=DbConnection.getErp();
            PreparedStatement s=c.prepareStatement("INSERT INTO enrollments (student_id,section_id,status) VALUES (?,?,'Enrolled')");
            s.setLong(1,stu);
            s.setLong(2,sec);
            s.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void del(long eid)
    {
        try
        {
            Connection c=DbConnection.getErp();
            PreparedStatement s=c.prepareStatement("DELETE FROM enrollments WHERE enrollment_id=?");
            s.setLong(1,eid);
            s.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public int count(long sec)
    {
        try
        {
            Connection c=DbConnection.getErp();
            PreparedStatement s=c.prepareStatement("SELECT COUNT(*) FROM enrollments WHERE section_id=?");
            s.setLong(1,sec);
            ResultSet rs=s.executeQuery();
            if(rs.next())
            {
                return rs.getInt(1);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }
}