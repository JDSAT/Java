package com.jdsat.jdsatfinance.utils;

import java.io.Serializable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.TextField;

public class CalcHelper implements Serializable {    
    final private DoubleProperty thirty=new SimpleDoubleProperty(0);
    final private DoubleProperty sixty=new SimpleDoubleProperty(0);
    final private DoubleProperty ninety=new SimpleDoubleProperty(0);
    final private DoubleProperty badDebt=new SimpleDoubleProperty(0);
    final private NumberBinding total=Bindings.add(thirty,sixty.add(ninety).add(badDebt));
    private TextField sumField=new TextField();
    private TextField thirtyField=new TextField();
    private TextField sixtyField=new TextField();
    private TextField ninetyField=new TextField();
    private TextField badDebtField=new TextField();
    
    public CalcHelper(){}
    
    public CalcHelper(TextField sumField,TextField thirtyField,TextField sixtyField,
            TextField ninetyField,TextField badDebtField){
        setSumField(sumField);
        setThirtyField(thirtyField);
        setSixtyField(sixtyField);
        setNinetyField(ninetyField);
        setBadDebtField(badDebtField);
        addListener();
    }
    
    public CalcHelper(TextField sumField,TextField thirtyField,TextField sixtyField,
            TextField ninetyField){
        setSumField(sumField);
        setThirtyField(thirtyField);
        setSixtyField(sixtyField);
        setNinetyField(ninetyField);
        setBadDebtField(badDebtField);
        addListener();
    }
    
    private void addListener(){
        this.getTotal().addListener((observable,oldValue,newValue)->{
            this.getSumField().setText(Double.toString((double)this.getTotal().getValue()));
        });
    }
    
    private void setSumField(TextField sf){
        sumField=sf;
    }
    
    private void setThirtyField(TextField thirtyField){
        this.thirtyField=thirtyField;
        this.thirtyField.textProperty().addListener((observable,oldValue,newValue)->{
            thirty.set(Double.parseDouble(newValue));
        });
    }
    
    private void setSixtyField(TextField sixtyField){
        this.sixtyField=sixtyField;
        this.sixtyField.textProperty().addListener((observable,oldValue,newValue)->{
            sixty.set(Double.parseDouble(newValue));
        });
    }
    
    private void setNinetyField(TextField ninetyField){
        this.ninetyField=ninetyField;
        this.ninetyField.textProperty().addListener((observable,oldValue,newValue)->{
            ninety.set(Double.parseDouble(newValue));
        });
    }
    
    private void setBadDebtField(TextField badDebtField){
        this.badDebtField=badDebtField;
        this.badDebtField.textProperty().addListener((observable,oldValue,newValue)->{
            badDebt.set(Double.parseDouble(newValue));
        });
    }
    
    public TextField getSumField(){
        return sumField;
    }
    
    public NumberBinding getTotal(){
        return total;
    }
}
