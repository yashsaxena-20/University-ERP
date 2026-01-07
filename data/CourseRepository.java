package edu.univ.erp.data;

import edu.univ.erp.domain.Course;
import java.sql.*;
import java.util.*;

public class CourseRepository
{
    public List<Course> getAll()
    {
        List<Course> list=new ArrayList<>();
        try
        {
            Connection c=DbConnection.getErp();
            ResultSet rs=c.createStatement().executeQuery("SELECT * FROM courses");
            while(rs.next())
            {
                list.add(new Course(rs.getString("code"),rs.getString("title"),rs.getInt("credits")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
    public Course getOne(long id)
    {
        try
        {
            Connection c=DbConnection.getErp();
            PreparedStatement s=c.prepareStatement("SELECT * FROM courses WHERE course_id=?");
            s.setLong(1,id);
            ResultSet rs=s.executeQuery();
            if(rs.next())
            {
                return new Course(rs.getString("code"), rs.getString("title"), rs.getInt("credits"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public void add(Course o)
    {
        try
        {
            Connection c=DbConnection.getErp();
            PreparedStatement s=c.prepareStatement("INSERT INTO courses (code,title,credits) VALUES (?,?,?)");
            s.setString(1,o.getCode());
            s.setString(2,o.getTitle());
            s.setInt(3,o.getCredit());
            s.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public String getCode(long id)
    {
        try
        {
            Connection c=DbConnection.getErp();
            PreparedStatement s=c.prepareStatement("SELECT code FROM courses WHERE course_id=?");
            s.setLong(1,id);
            ResultSet rs=s.executeQuery();
            if(rs.next())
            {
                return rs.getString("code");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "Unknown";
    }
}