package com.jdsat.tabgui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**Manages access to Employees and their data
 *
 * @author Ronald
 */
public class SimpleEmployeeService implements EmployeeService {    
    private final ObservableList<Employee> employeeData;
    
    public SimpleEmployeeService(){
        employeeData=FXCollections.observableArrayList();
    }

    @Override
    public ObservableList<Employee> getEmployees() {
        return employeeData;
    }
}
