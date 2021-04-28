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
public class StudentController extends Controller<Student>{
    @FXML private Text attendanceTxt;
    @FXML private Text scholarshipTxt;
    @FXML private ComboBox<Subject> subjectsCmb;
    @FXML private TableView<Activity> subjectActivitiesTv;
    @FXML private TableView<Activity> studentActivitiesTv;
    @FXML private Button withdrawBtn;
    @FXML private Button enrolBtn;
    @FXML private Button logoutBtn;
    @FXML private TableColumn<Activity, String> enrolClm;
    @FXML private TableColumn<Activity, String> sbjNumberClm;
    
    public final Student getStudent(){
        return model;
    }
    
    @FXML
    private void initialize(){
        setAttendance(getAttendance());
        setScholarship(getScholarship());
        
        enrolClm.setCellValueFactory(cellData -> cellData.getValue().enrolledProperty().asString());
        sbjNumberClm.setCellValueFactory(cellData -> cellData.getValue().sbjNumberProperty().asString());
        
        subjectsCmb.getSelectionModel().selectedItemProperty().addListener((o, oldSubject, newSubject) -> 
                subjectActivitiesTv.setItems(getSelectedSbj().getActivities()));
        
        subjectActivitiesTv.getSelectionModel().selectedItemProperty().addListener((o, oldSubject, newSubject) ->
                enrolBtn.setDisable(newSubject == null || !getSbjSelectedActivity().canEnrol() || hasEnrolled()));
        
        studentActivitiesTv.getSelectionModel().selectedItemProperty().addListener((o, oldSubject, newSubject) -> 
                withdrawBtn.setDisable(getStuSelectedActivity() == null));
    }
    
    @FXML
    private void handleLogout(ActionEvent event){
        stage.close();
    }
    
    @FXML
    private void handleEnrol(ActionEvent event){
        getStudent().enrol(getSbjSelectedActivity());
        // set the enrol disable to true while the activity in 2nd table is still selected
        // after enrolling into the selected activity in subject activity table.
        updateEnrolBtn();
    }
    
    @FXML
    private void handleWithdraw(ActionEvent event){
        getStudent().withdraw(getStuSelectedActivity());
        // set enrol disable to false after withdraw the activity from "My activities" 
        // while the subject activity (in 2nd table) is still selected.
        updateEnrolBtn();
    }
    
    private boolean hasEnrolled(){
        return getStudent().isEnrolledIn(getSbjSelectedActivity());
    }
    
    private String getAttendance(){
        return getStudent().getAttendance();
    }
    private void setAttendance(String attendance){
        if(attendance.equals("ft")){
            attendanceTxt.setText("Full Time");
        }
        else attendanceTxt.setText("Part Time");
    }
    private boolean getScholarship(){
        return getStudent().getScholarship();
    }
    private void setScholarship(boolean scholarship){
        if(scholarship){
            scholarshipTxt.setText("Yes");
        }
        else scholarshipTxt.setText("No");
    }
    
    private void updateEnrolBtn(){
        enrolBtn.setDisable(getSbjSelectedActivity() == null || hasEnrolled());
    }
    
    private Subject getSelectedSbj(){ // get the selected subject from the combo box.
        return subjectsCmb.getSelectionModel().getSelectedItem();
    }
    
    private Activity getSbjSelectedActivity(){ // get the selected activity from the 2nd table view.
        return subjectActivitiesTv.getSelectionModel().getSelectedItem();
    }
    
    private Activity getStuSelectedActivity(){ // get the selected activity from the student "My activities".
        return studentActivitiesTv.getSelectionModel().getSelectedItem();
    }
    
}
