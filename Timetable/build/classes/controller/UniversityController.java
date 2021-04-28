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

public class UniversityController extends Controller<University> {
    
    @FXML private Button addStudentBtn;
    @FXML private Button removeStudentBtn;
    @FXML private Button loginBtn;
    @FXML private ListView<Student> studentsLv;
    
    public final University getUniversity(){ return model; }
    
    private Student getSelectedStudent(){
        return studentsLv.getSelectionModel().getSelectedItem();
    }
    
    @FXML
    private void initialize(){
        studentsLv.getSelectionModel().selectedItemProperty().addListener((o, oldSubject, newSubject) -> 
                removeStudentBtn.setDisable(getSelectedStudent() == null));
        studentsLv.getSelectionModel().selectedItemProperty().addListener((o, oldSubject, newSubject) -> 
                loginBtn.setDisable(getSelectedStudent() == null));
    }
    
    @FXML
    private void handleAddStudent(ActionEvent event) throws Exception{
        University university = getUniversity();
        ViewLoader.showStage(university, "/view/add_student.fxml", "Add Student", new Stage());
    }
    
    @FXML
    private void handleRemoveStudent(ActionEvent event){
        getUniversity().remove(getSelectedStudent());
    }
    
    @FXML
    private void handleLogin(ActionEvent event) throws Exception{
        ViewLoader.showStage(getSelectedStudent(), "/view/student.fxml", "Student", new Stage());
    }
    
}
