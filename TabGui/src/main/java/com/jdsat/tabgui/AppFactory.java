package com.jdsat.tabgui;

import com.jdsat.tabgui.model.EmployeeService;
import com.jdsat.tabgui.model.SimpleEmployeeService;
import com.jdsat.tabgui.view.Controller;
import com.jdsat.tabgui.view.MainController;
import com.jdsat.tabgui.view.Tab0Controller;
import com.jdsat.tabgui.view.Tab1Controller;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppFactory {
    
    @Bean EmployeeService employeeService(){
        return new SimpleEmployeeService();
    }
    
    @Bean
    public MainController mainController(){
        return loadController("/fxml/Main.fxml");
    }
    
    @Bean
    public Tab0Controller tab0Controller(){
        return loadController("/fxml/Tab0.fxml");
    }
    
    @Bean
    public Tab1Controller tab1Controller(){
        return loadController("/fxml/Tab1.fxml");
    }
    
    protected <T> T loadController(String fxmlFile){
        try{
            FXMLLoader loader=new FXMLLoader();
            Node view=(Node)loader.load(getClass().getResourceAsStream(fxmlFile));
            Controller controller=(Controller)loader.getController();
            controller.setView(view);//set the view (Node) for each loaded controller
            return (T)controller;
        }catch(IOException e){
            throw new RuntimeException(String.format("Unable to load FXML file '%s'", fxmlFile), e);
        }
    }
}
