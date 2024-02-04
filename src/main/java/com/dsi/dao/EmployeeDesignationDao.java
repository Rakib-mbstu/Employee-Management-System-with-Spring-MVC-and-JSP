package com.dsi.dao;

import com.dsi.entity.DesignationDetails;

import java.util.List;

public interface EmployeeDesignationDao {
    public void save(DesignationDetails designation);
    public void getAllDetails();
    public List<DesignationDetails> getDetailsByDesignation(String designationName);
    public void editDesignation(DesignationDetails designationDetails);
    public void deleteDesignation(String designationName);
}
