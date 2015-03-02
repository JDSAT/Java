package com.jdsat.tabgui.view;

import com.jdsat.tabgui.MainApp;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.inject.Inject;
import org.controlsfx.dialog.Dialogs;

public class MainController extends AbstractController {
    MainApp mainApp;
    @FXML private Tab pfTab;
    @FXML private Tab persTab;
    @Inject private Tab0Controller tab0Controller;
    @Inject private Tab1Controller tab1Controller;
//    @Inject private EmployeeService employeeService;
    
    public void setMainApp(MainApp mainApp){
        this.mainApp=mainApp;
    }
    
    public Tab1Controller getTab1Controller(){
        return tab1Controller;
    }
    
    public void showTab0(){
        AnchorPane ap=(AnchorPane)tab0Controller.getView();
        ap.prefWidthProperty().bind(mainApp.getPrimaryStage().widthProperty());
        ap.prefHeightProperty().bind(mainApp.getPrimaryStage().heightProperty());
        pfTab.setContent(tab0Controller.getView());
    }
    
    public void showTab1(){
        AnchorPane ap=(AnchorPane)tab1Controller.getView();
        ap.prefWidthProperty().bind(mainApp.getPrimaryStage().widthProperty());
        ap.prefHeightProperty().bind(mainApp.getPrimaryStage().heightProperty());
        tab1Controller.setup();
        persTab.setContent(tab1Controller.getView());
    }
    
     @FXML
    private void handleNew(){
        //todo
    }
    
    @FXML
    private void handleOpen(){
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
//            mainApp.loadPersonDataFromFile(file);
        }
    }
    
    @FXML
    private void handleSave(){
        //to do
    }
    
    @FXML
    private void handleSaveAs(){
        //todo
    }
    
    @FXML
    private void handleExit(){
        mainApp.getDataManager().shutDown();
        System.exit(0);
    }
    
    @FXML
    private void handleAbout() {
        Dialogs.create()
            .title("Financial Projector")
            .masthead("About")
            .message("Author: Andy Garrick www.jdsat.com")
            .showInformation();
    }
}
