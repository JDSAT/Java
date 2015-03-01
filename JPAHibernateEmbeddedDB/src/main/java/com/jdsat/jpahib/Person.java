package com.jdsat.jpahib;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PERSON")
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="PERSON_ID")
    private int personId;
    
    @Column(name="PERSON_NAME")
    private String personName;
    
    public Person(){}
    
    public Person(String personName){
        this.personName=personName;
    }
    
    public int getPersonId(){
        return personId;
    }
    
    public void setPersonId(int personId){
        this.personId=personId;
    }
    
    public String getPersonName(){
        return personName;
    }
    
    public void setPersonName(String personName){
        this.personName=personName;
    }
}
