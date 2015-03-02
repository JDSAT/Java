package com.jdsat.jdsatfinance.model;

import com.jdsat.jdsatfinance.utils.LocalDateAdapter;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@Access(AccessType.PROPERTY)
@Table(name="EMPLOYEE")
public class Employee implements Externalizable {
    private IntegerProperty employeeId;
    private StringProperty lastName;
    private StringProperty firstName;
    private StringProperty mi;
    private ObjectProperty<LocalDate> hireDate;
    private StringProperty positionTitle;
    
//    public Employee(){
//        this(0,null,null,null,null,null);
//    }
//    
//    public Employee(int employeeId,String lastName,String firstName,String mi,
//            LocalDate hireDate,String positionTitle){
//        this.employeeId=new SimpleIntegerProperty(employeeId);
//        this.lastName=new SimpleStringProperty(lastName);
//        this.firstName=new SimpleStringProperty(firstName);
//        this.mi=new SimpleStringProperty(mi);
//        this.hireDate=new SimpleObjectProperty(hireDate);
//        this.positionTitle=new SimpleStringProperty(positionTitle);
//    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="EMPLOYEE_ID")
    public int getEmployeeId(){
        return employeeId.get();
    }
    
    public void setEmployeeId(int employeeId){
        this.employeeId.set(employeeId);
    }
    
    public IntegerProperty employeeIdProperty(){
        return employeeId;
    }
    
    @Column(name="EMPLOYEE_LNAME")
    public String getLastName(){
        return lastName.get();
    }
    
    public void setLastName(String lastName){
        this.lastName.set(lastName);
    }
    
    public StringProperty lastNameProperty(){
        return lastName;
    }
    
    @Column(name="EMPLOYEE_FNAME")
    public String getFirstName(){
        return firstName.get();
    }
    
    public void setFirstName(String firstName){
        this.firstName.set(firstName);
    }
    
    public StringProperty firstNameProperty(){
        return firstName;
    }
    
    @Column(name="EMPLOYEE_MI")
    public String getMi(){
        return mi.get();
    }
    
    public void setMi(String mi){
        this.mi.set(mi);
    }
    
    public StringProperty miProperty(){
        return mi;
    }
    
    @Column(name="EMPLOYEE_HIREDATE")
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
    
    @Column(name="EMPLOYEE_TITLE")
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(getEmployeeId());
        out.writeObject(getLastName());
        out.writeObject(getFirstName());
        out.writeObject(getMi());
        out.writeObject(getHireDate());
        out.writeObject(getPositionTitle());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setEmployeeId(in.readInt());
        setLastName((String)in.readObject());
        setFirstName((String)in.readObject());
        setMi((String) in.readObject());
        setHireDate((LocalDate)in.readObject());
        setPositionTitle((String)in.readObject());
    }
}
