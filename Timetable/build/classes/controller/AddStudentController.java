package controller;

import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.beans.binding.*;
import javafx.beans.property.*;
import java.io.*;
import java.text.*;
import model.*;
import au.edu.uts.ap.javafx.*;

public class AddStudentController extends Controller<University>{
    @FXML private TextField studentNumberTf;
    @FXML private TextField studentNameTf;
    @FXML private ToggleGroup attendanceTg;
    @FXML private CheckBox scholarshipCb;
    @FXML private Text errorTxt;
    @FXML private Button cancelBtn;
    @FXML private Button addBtn;
    
    public final University getUniversity(){ return model; }
    
    private String getStudentNumber(){ return studentNumberTf.getText(); }
    private void setStudentNumber(String number){ studentNumberTf.setText(number); }
    
    private String getStudentName(){ return studentNameTf.getText(); }
    private void setStudentName(String name){ studentNameTf.setText(name); }
    
    private boolean hasScholarship(){ return scholarshipCb.isSelected(); }
    
    @FXML private void initialize(){
        studentNumberTf.textProperty().addListener((o, oldText, newText) -> updateButtons());
        studentNameTf.textProperty().addListener((o, oldText, newText) -> updateButtons());
        attendanceTg.selectedToggleProperty().addListener((o, old, now) -> updateButtons());
    }
    
    private void updateButtons(){
        addBtn.setDisable(isStuNumberBlank() || isStuNameBlank() || !isSelected());
    }
    
    private boolean isStuNumberBlank(){
        return getStudentNumber().isEmpty();
    }
    
    private boolean isStuNameBlank(){
        return getStudentName().isEmpty();
    }
    
    private boolean isSelected(){
        return attendanceTg.getSelectedToggle() != null;
    }
    
    private String getAttendance() {
    if (isSelected())
        return attendanceTg.getSelectedToggle().getUserData().toString();
    else
        return null;
    }
        
    @FXML
    private void handleCancel(ActionEvent event){
        stage.close();
    }
    
    @FXML
    private void handleAdd(ActionEvent event){
        try{
            University university = getUniversity();
            university.addStudent(getStudentNumber(), getStudentName(), getAttendance(), hasScholarship());
            stage.close();
        }
        catch(Exception e){
            errorTxt.setText(e.getMessage());
        }
    }
}
