package com.jdsat.jdsatfinance.model;

import com.jdsat.jdsatfinance.utils.LocalDateAdapter;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class Employee {
    private final IntegerProperty employeeId;
    private final StringProperty lastName;
    private final StringProperty firstName;
    private final StringProperty mi;
    private final ObjectProperty<LocalDate> hireDate;
    private final StringProperty positionTitle;
    
    public Employee(){
        this(0,null,null,null,null,null);
    }
    
    public Employee(Integer employeeId,String lastName,String firstName,String mi,
            LocalDate hireDate,String positionTitle){
        this.employeeId=new SimpleIntegerProperty(employeeId);
        this.lastName=new SimpleStringProperty(lastName);
        this.firstName=new SimpleStringProperty(firstName);
        this.mi=new SimpleStringProperty(mi);
        this.hireDate=new SimpleObjectProperty(hireDate);
        this.positionTitle=new SimpleStringProperty(positionTitle);
    }
    
    public int getEmployeeId(){
        return employeeId.get();
    }
    
    public void setEmployeeId(int employeeId){
        this.employeeId.set(employeeId);
    }
    
    public IntegerProperty employeeIdProperty(){
        return employeeId;
    }
    
    public String getLastName(){
        return lastName.get();
    }
    
    public void setLastName(String lastName){
        this.lastName.set(lastName);
    }
    
    public StringProperty lastNameProperty(){
        return lastName;
    }
    
    public String getFirstName(){
        return firstName.get();
    }
    
    public void setFirstName(String firstName){
        this.firstName.set(firstName);
    }
    
    public StringProperty firstNameProperty(){
        return firstName;
    }
    
    public String getMi(){
        return mi.get();
    }
    
    public void setMi(String mi){
        this.mi.set(mi);
    }
    
    public StringProperty miProperty(){
        return mi;
    }
    
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getHireDate(){
        return hireDate.get();
    }
    
    public void setHireDate(LocalDate hireDate){
        this.hireDate.set(hireDate);
    }
    
    public ObjectProperty<LocalDate> hireDateProperty(){
        return hireDate;
    }
    
    public String getPositionTitle(){
        return positionTitle.get();
    }
    
    public void setPositionTitle(String positionTitle){
        this.positionTitle.set(positionTitle);
    }
    
    public StringProperty positionTitleProperty(){
        return positionTitle;
    }
    
    @Override
    public int hashCode(){
        return 0;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Employee) {
            Employee other=(Employee) obj;
            return other.getEmployeeId()==this.getEmployeeId();
        } else {
            return false;
        }
    }
}
