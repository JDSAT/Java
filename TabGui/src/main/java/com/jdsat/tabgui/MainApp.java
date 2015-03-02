package com.jdsat.tabgui;

import com.jdsat.tabgui.data.DataManager;
import com.jdsat.tabgui.model.Employee;
import com.jdsat.tabgui.view.MainController;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp extends Application {
    private Stage primaryStage;
    private DataManager<Employee> dataManager;

    @Override
    public void start(Stage stage) throws Exception {        
        this.primaryStage=stage;
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppFactory.class);
        MainController mainController = context.getBean(MainController.class);
        mainController.setMainApp(this);
        
        /**load data**/
        dataManager=new DataManager("pu","Employee",Employee.class);
        
        List<Employee> employees=dataManager.getAllEntities();
        for(Employee employee:employees){
            mainController.getTab1Controller().getEmployeeService().getEmployees().add(employee);
        }
        
        mainController.showTab0();
        mainController.showTab1();
        Scene scene = new Scene((Parent)mainController.getView());
        primaryStage.setScene(scene);
        primaryStage.setTitle("JDSAT Financial Projector 1.0");
        primaryStage.setMaximized(true);
        primaryStage.show();
        
//        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
//            @Override
//            public void handle(WindowEvent we){
//                getDataManager().shutDown();
//                System.exit(0);
//            }
//        });
//        stage.close();
        
        /**shut down the database**/
        
        
    }
    
    public DataManager<Employee> getDataManager(){
        return this.dataManager;
    }
    
    public Stage getPrimaryStage(){
        return primaryStage;
    }
    
    public void closeStage(){
        
    }

    public static void main(String[] args) {        
        launch(args);
    }

    
}