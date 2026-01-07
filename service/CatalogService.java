package edu.univ.erp.service;

import edu.univ.erp.data.*;
import edu.univ.erp.domain.*;
import java.util.List;

public class CatalogService
{
    private CourseRepository crepo;
    private SectionRepository srepo;

    public CatalogService()
    {
        crepo=new CourseRepository();
        srepo=new SectionRepository();
    }

    public List<Course> getCourses()
    {
        return crepo.getAll();
    }

    public List<Section> getSections()
    {
        return srepo.getAll();
    }
}