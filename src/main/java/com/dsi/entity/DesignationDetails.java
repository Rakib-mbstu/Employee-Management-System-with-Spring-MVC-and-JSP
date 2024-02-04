package com.dsi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class DesignationDetails {
    @Id
    private String designationName;
    private double salary;
    private String details;

    public String getDesignationName() {
        return designationName;
    }

    public void setDesignationName(String designation) {
        this.designationName = designation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "designation{" +
                "designation='" + designationName + '\'' +
                ", salary=" + salary +
                ", details='" + details + '\'' +
                '}';
    }
}
