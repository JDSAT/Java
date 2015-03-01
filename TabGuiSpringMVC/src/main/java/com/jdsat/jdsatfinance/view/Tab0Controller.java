package com.jdsat.jdsatfinance.view;

import com.jdsat.jdsatfinance.utils.CalcHelper;
import com.jdsat.jdsatfinance.model.EmployeeService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javax.inject.Inject;

public class Tab0Controller extends AbstractController implements Initializable {
    @Inject MainController mainController;
    @Inject EmployeeService employeeService;
    
    @FXML private TextField ficaYear1;
    @FXML private TextField ficaYear2;
    @FXML private TextField ficaYear3;
    @FXML private TextField fuiYear1;
    @FXML private TextField fuiYear2;
    @FXML private TextField fuiYear3;
    @FXML private TextField suiYear1;
    @FXML private TextField suiYear2;
    @FXML private TextField suiYear3;
    @FXML private TextField wcYear1;
    @FXML private TextField wcYear2;
    @FXML private TextField wcYear3;
    @FXML private TextField ehipYear1;
    @FXML private TextField ehipYear2;
    @FXML private TextField ehipYear3;
    @FXML private TextField erpYear1;
    @FXML private TextField erpYear2;
    @FXML private TextField erpYear3;
    @FXML private TextField ptoYear1;
    @FXML private TextField ptoYear2;
    @FXML private TextField ptoYear3;
    @FXML private TextField ohYear1;
    @FXML private TextField ohYear2;
    @FXML private TextField ohYear3;
    @FXML private TextField gaYear1;
    @FXML private TextField gaYear2;
    @FXML private TextField gaYear3;
    @FXML private TextField feeYear1;
    @FXML private TextField feeYear2;
    @FXML private TextField feeYear3;
    @FXML private TextField taxYear1;
    @FXML private TextField taxYear2;
    @FXML private TextField taxYear3;
    @FXML private TextField ar30Year1;
    @FXML private TextField ar30Year2;
    @FXML private TextField ar30Year3;
    @FXML private TextField ar60Year1;
    @FXML private TextField ar60Year2;
    @FXML private TextField ar60Year3;
    @FXML private TextField ar90Year1;
    @FXML private TextField ar90Year2;
    @FXML private TextField ar90Year3;
    @FXML private TextField arBadDebtYear1;
    @FXML private TextField arBadDebtYear2;
    @FXML private TextField arBadDebtYear3;    
    @FXML private TextField ap30Year1;
    @FXML private TextField ap30Year2;
    @FXML private TextField ap30Year3;
    @FXML private TextField ap60Year1;
    @FXML private TextField ap60Year2;
    @FXML private TextField ap60Year3;
    @FXML private TextField ap90Year1;
    @FXML private TextField ap90Year2;
    @FXML private TextField ap90Year3;    
    @FXML private TextField arTextTotalYear1;
    @FXML private TextField arTextTotalYear2;
    @FXML private TextField arTextTotalYear3;    
    @FXML private TextField apTextTotalYear1;
    @FXML private TextField apTextTotalYear2;
    @FXML private TextField apTextTotalYear3;    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new CalcHelper(arTextTotalYear1,ar30Year1,ar60Year1,ar90Year1,arBadDebtYear1);
        new CalcHelper(arTextTotalYear2,ar30Year2,ar60Year2,ar90Year2,arBadDebtYear2);
        new CalcHelper(arTextTotalYear3,ar30Year3,ar60Year3,ar90Year3,arBadDebtYear3);
        new CalcHelper(apTextTotalYear1,ap30Year1,ap60Year1,ap90Year1);
        new CalcHelper(apTextTotalYear2,ap30Year2,ap60Year2,ap90Year2);
        new CalcHelper(apTextTotalYear3,ap30Year3,ap60Year3,ap90Year3);
    }
}