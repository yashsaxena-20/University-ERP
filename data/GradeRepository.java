package edu.univ.erp.data;

import edu.univ.erp.domain.Grade;
import java.sql.*;
import java.util.*;

public class GradeRepository
{
    public List<Grade> getByEnr(long eid)
    {
        List<Grade> list=new ArrayList<>();
        try
        {
            Connection c=DbConnection.getErp();
            PreparedStatement s=c.prepareStatement("SELECT * FROM grades WHERE enrollment_id=?");
            s.setLong(1,eid);
            ResultSet rs=s.executeQuery();
            while(rs.next())
            {
                list.add(new Grade(rs.getLong("grade_id"),eid,rs.getString("component"),rs.getDouble("score")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }
    public void save(Grade g)
    {
        try
        {
            Connection c=DbConnection.getErp();
            PreparedStatement s=c.prepareStatement("INSERT INTO grades (enrollment_id,component,score) VALUES (?,?,?) ON DUPLICATE KEY UPDATE score=?");
            s.setLong(1,g.getEid());
            s.setString(2,g.getComp());
            s.setDouble(3,g.getScore());
            s.setDouble(4,g.getScore());
            s.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void updateFinalGrade(long eid,String finalGrade)
    {
        try
        {
            Connection c=DbConnection.getErp();
            PreparedStatement s=c.prepareStatement("UPDATE grades SET final_grade=? WHERE enrollment_id=?");
            s.setString(1,finalGrade);
            s.setLong(2,eid);
            s.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public String getFinalGrade(long eid)
    {
        try
        {
            Connection c=DbConnection.getErp();
            PreparedStatement s=c.prepareStatement("SELECT final_grade FROM grades WHERE enrollment_id=?");
            s.setLong(1,eid);
            ResultSet rs=s.executeQuery();
            if(rs.next())
            {
                String grade=rs.getString("final_grade");
                return (grade!=null)?grade:"N/A";
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "N/A";
    }
}