package com.jdsat.jpahib;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
    
    public static void main(String[] args){
          
        DataManager personDataManager=new DataManager<>("pu","Person",Person.class);
        
        /**add single entity**/
        Person person=new Person("andyg");       
        personDataManager.insertEntity(person);
        
        
        /**delete single entity**/
//        personDataManager.removeEntity(1000488,Person.class);
        
        /**add multiple entities - standard insert**/
//        for(int i=0;i<5000;i++){
//            personDataManager.insertEntity(new Person("test"+i));
//        }
        
        /**add multiple entities - List insert**/
//        List<Person> personsToAdd=new ArrayList<>();
//        for(int i=0;i<10000;i++){
//            personsToAdd.add(new Person("test"+i));
//        }
//        personDataManager.insertEntities(personsToAdd);
        
        /**Delete all entities**/
//        personDataManager.removeAllEntities();
        
        /**print all entities**/
        List<Object> personList=personDataManager.getAllEntities();
        personList.stream().map((object) -> (Person)object).forEach((p) -> {
            System.out.println(p.getPersonId()+""+'\t'+p.getPersonName());
        });
        
        /**shut down EntityManagerFactor and close transaction**/
        personDataManager.shutDown();
        
        
        /**tester for Employee class**/
        
//        DataManager employeeDataManager=new DataManager("pu","Employee",Employee.class);
//        Employee employee=new Employee("garrick");
//        
//        employeeDataManager.insertEntity(employee);
//        
//        /**print all entities**/
//        List<Object> employeeList=employeeDataManager.getAllEntities();
//        employeeList.stream().map((object) -> (Employee)object).forEach((p) -> {
//            System.out.println(p.getEmployeeId()+""+'\t'+p.getEmployeeLastName());
//        });
//        employeeDataManager.shutDown();
    }
}
