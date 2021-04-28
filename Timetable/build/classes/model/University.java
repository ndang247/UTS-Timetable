package model;

import java.util.*;
import javafx.collections.*;

public class University {
    private ObservableList<Subject> subjects = FXCollections.observableArrayList();
    private ObservableList<Student> students = FXCollections.observableArrayList();

    public University() {
        Subject ap = new Subject(48024, "Applications Programming");
        ap.addActivity("Lec1", 1, "Wed", 18, 1, "CB11.00.405", 200);
        ap.addActivity("Cmp1", 1, "Wed", 19, 2, "CB11.B1.403", 2);
        ap.addActivity("Cmp1", 2, "Wed", 19, 2, "CB11.B1.401", 2);
        ap.addActivity("Cmp1", 3, "Wed", 19, 2, "CB11.B1.402", 2);
        subjects.add(ap);
        Subject wsd = new Subject(31284, "Web Services Development");
        wsd.addActivity("Lec1", 1, "Tue", 16, 1, "CB02.03.002", 160);
        wsd.addActivity("Cmp1", 1, "Tue", 9, 2, "CB11.B1.102", 30);
        wsd.addActivity("Cmp1", 2, "Tue", 9, 2, "CB11.B1.103", 30);
        wsd.addActivity("Cmp1", 3, "Tue", 14, 2, "CB11.B1.102", 30);
        wsd.addActivity("Cmp1", 4, "Tue", 14, 2, "CB11.B1.103", 30);
        subjects.add(wsd);
    }

    public ObservableList<Subject> getSubjects() {
        return subjects;
    }

    public ObservableList<Student> getStudents() {
        return students;
    }

    public void addStudent(String number, String name, String attendance, boolean scholarship) throws Exception{
        if(student(number) != null){
            throw new Exception("Student already exists");
        }
        else students.add(new Student(this, number, name, attendance, scholarship));
    }

    public void remove(Student student) {
        // Don't forget to also withdraw the student from all activities.
        
        // ObservableList<Activity> studentActivities = student.getActivities();
        for (Activity activity : student.getActivities()){
            activity.withdraw();
        }
        student.getActivities().clear();
        // OR could also do student.getActivities().removeAll(student.getActivities());
        students.remove(student);
    }

    private void view() {
        for (Student student : students)
            System.out.println(student);
    }

    private Student student(String number) {
        for (Student student : students)
            if (student.matches(number))
                return student;
        return null;
    }

    private Subject subject(int number) {
        for (Subject subject : subjects)
            if (subject.matches(number))
                return subject;
        return null;
    }
}
