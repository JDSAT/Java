package com.jdsat.jdsatfinance;

import com.jdsat.jdsatfinance.view.MainController;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        String current = new java.io.File( "." ).getCanonicalPath();
        System.out.println("Current dir:"+current);
//        Path p1=Paths.get("views");
//        System.out.println(p1.toAbsolutePath());
        System.exit(0);
        
        this.primaryStage=stage;
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppFactory.class);
        MainController mainController = context.getBean(MainController.class);
        mainController.setMainApp(this);
        mainController.showTab0();
        mainController.showTab1();
        Scene scene = new Scene((Parent)mainController.getView());
        primaryStage.setScene(scene);
        primaryStage.setTitle("JDSAT Financial Projector 1.0");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
    
    public Stage getPrimaryStage(){
        return primaryStage;
    }

    public static void main(String[] args) {        
        launch(args);
    }
}
