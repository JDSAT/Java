package com.jdsat.h2test;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="EMPLOYEE_ID")
    private int employeeId;
    
    @Column(name="EMPLOYEE_NAME")
    private String employeeLastName;
    
    public Employee(){}
    
    public Employee(String employeeLastName){
        this.employeeLastName=employeeLastName;
    }
    
    public int getEmployeeId(){
        return employeeId;
    }
    
    public void getEmployeeId(int employeeId){
        this.employeeId=employeeId;
    }
    
    public String getEmployeeLastName(){
        return employeeLastName;
    }
    
    public void setEmployeeLastName(String employeeLastName){
        this.employeeLastName=employeeLastName;
    }
}

