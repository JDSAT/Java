package com.jdsat.tabgui.view;

import com.jdsat.tabgui.model.Employee;
import com.jdsat.tabgui.model.EmployeeService;
import com.jdsat.tabgui.utils.Utils;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.inject.Inject;
import org.controlsfx.dialog.Dialogs;

public class Tab1Controller extends AbstractController implements Initializable {
    @Inject MainController mainController;
    @Inject private EmployeeService employeeService;
    
    @FXML private TableView<Employee> employeeTable;
    @FXML private TableColumn<Employee,Number> employeeIdColumn;
    @FXML private TableColumn<Employee,String> lastNameColumn;
    @FXML private TableColumn<Employee,String> firstNameColumn;
    @FXML private TableColumn<Employee,String> miColumn;
    @FXML private TableColumn<Employee,LocalDate> hireDateColumn;
    @FXML private TableColumn<Employee,String> positionTitleColumn;
    @FXML private TextField employeeIdField;
    @FXML private TextField lastNameField;
    @FXML private TextField firstNameField;
    @FXML private TextField miField;
    @FXML private DatePicker hireDatePicker;
    @FXML private TextField positionTitleField;
    
    private HashSet<Employee> filter;
    
    public EmployeeService getEmployeeService(){
        return employeeService;
    }
    
    public void setup(){
        filter=new HashSet();
        filter.addAll(employeeService.getEmployees());
        employeeTable.setItems(employeeService.getEmployees());
        employeeTable.setPlaceholder(new Label("No employee records exist. Add a new record."));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {        
        employeeIdColumn.setCellValueFactory(cellData->cellData.getValue().idProperty());
        firstNameColumn.setCellValueFactory(cellData->cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData->cellData.getValue().lastNameProperty());
        miColumn.setCellValueFactory(cellData->cellData.getValue().miProperty());
        hireDateColumn.setCellValueFactory(cellData->cellData.getValue().hireDateProperty());
        positionTitleColumn.setCellValueFactory(cellData->cellData.getValue().titleProperty());
        
        //Listen for selection changes and show selected employee's data
        employeeTable.getSelectionModel().selectedItemProperty().addListener(
                (observable,oldValue,newValue)->showEmployeeData(newValue));
    }
    
    private void showEmployeeData(Employee employee){
        try{
            employeeIdField.setText(Integer.toString(employee.getId()));
            lastNameField.setText(employee.getLastName());
            firstNameField.setText(employee.getFirstName());
            miField.setText(employee.getMi());
            hireDatePicker.setValue(employee.getHireDate());
            positionTitleField.setText(employee.getTitle());
        }catch(NullPointerException e){
        }
    }
    
    @FXML
    private void handleAddUpdate() {        
        if (isInputValid()) {
            Employee tempEmployee=new Employee();
            boolean exists=false;            
            for(Employee e:employeeService.getEmployees()){
                if(e.getId()==Integer.parseInt(employeeIdField.getText())){
                    tempEmployee=e;
                    exists=true;
                }
            }
            tempEmployee.setId(Integer.parseInt(employeeIdField.getText()));
            tempEmployee.setLastName(lastNameField.getText());
            tempEmployee.setFirstName(firstNameField.getText());
            tempEmployee.setMi(miField.getText());
            tempEmployee.setHireDate(hireDatePicker.getValue());
            tempEmployee.setTitle(positionTitleField.getText());
            
            if(!exists){
                employeeService.getEmployees().add(tempEmployee);
                mainController.mainApp.getDataManager().insertEntity(tempEmployee);
            }else{
                Dialogs.create()
                    .title("Record Exists")
                    .masthead("Record exists with matching employeed ID")
                    .message("Existing record will be updated.")
                    .showWarning();
                mainController.mainApp.getDataManager().removeEntity(tempEmployee.getId());
                employeeService.getEmployees().remove(tempEmployee);
                employeeService.getEmployees().add(tempEmployee);
                mainController.mainApp.getDataManager().insertEntity(tempEmployee);
            }
        }
    }
    
    @FXML 
    private void handleDeleteEmployee() {
        int selectedIndex = employeeTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            employeeTable.getItems().remove(selectedIndex);
            Employee toRemove=employeeTable.getSelectionModel().getSelectedItem();
            mainController.mainApp.getDataManager().removeEntity(toRemove.getId());
        } else { // Nothing selected.
            Dialogs.create()
                .title("No Selection")
                .masthead("No Employee Selected")
                .message("Please select an employee in the table.")
                .showWarning();
        }
   }
    
    private boolean isInputValid() {
        String errorMessage = "";
        if(employeeIdField.getText()==null || employeeIdField.getText().length()==0 || 
                !Utils.isNumericPattern(employeeIdField.getText())){
            errorMessage+="Invalid Employee ID!\n";
        }        
        if(firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage +="Invalid first name!\n"; 
        }
        if(lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage +="Invalid last name!\n"; 
        }

        if(errorMessage.length()==0) {
            return true;
        }else {
            // Show the error message.
            Dialogs.create()
                .title("Invalid Fields")
                .masthead("Please correct invalid fields")
                .message(errorMessage)
                .showError();
            return false;
        }
    }
}
