package edu.univ.erp.data;

import edu.univ.erp.domain.Section;
import java.sql.*;
import java.util.*;

public class SectionRepository
{
    public List<Section> getAll()
    {
        List<Section> list=new ArrayList<>();
        try
        {
            Connection c=DbConnection.getErp();
            ResultSet rs=c.createStatement().executeQuery("SELECT * FROM sections");
            while(rs.next())
            {
                list.add(new Section(rs.getLong("section_id"),rs.getLong("course_id"),rs.getLong("instructor_id"),rs.getString("day_time"),rs.getString("room"),rs.getInt("capacity")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

    public void add(Section o)
    {
        try
        {
            Connection c=DbConnection.getErp();
            PreparedStatement s=c.prepareStatement("INSERT INTO sections (course_id,instructor_id,day_time,room,capacity,semester,year) VALUES (?,?,?,?,?,?,?)");
            s.setLong(1,o.getCid());
            s.setLong(2,o.getIid());
            s.setString(3,o.getTime());
            s.setString(4,o.getRoom());
            s.setInt(5,o.getCap());
            s.setInt(6,1);
            s.setInt(7,2025);
            s.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public Section getOne(long id)
    {
        try
        {
            Connection c=DbConnection.getErp();
            PreparedStatement s=c.prepareStatement("SELECT * FROM sections WHERE section_id=?");
            s.setLong(1,id);
            ResultSet rs=s.executeQuery();
            if(rs.next())
            {
                return new Section(rs.getLong("section_id"),rs.getLong("course_id"),rs.getLong("instructor_id"),rs.getString("day_time"),rs.getString("room"),rs.getInt("capacity"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}