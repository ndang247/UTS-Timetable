package model;

import javafx.beans.property.*;

public class Activity {
    private Subject subject;
    private String group;
    private int number;
    private String day;
    private int start;
    private int duration;
    private String room;
    private int capacity;
    private IntegerProperty enrolled = new SimpleIntegerProperty();

    public Activity(Subject subject, String group, int number, String day, int start, int duration, String room, int capacity) {
        this.subject = subject;
        this.group = group;
        this.number = number;
        this.day = day;
        this.start = start;
        this.duration = duration;
        this.room = room;
        this.capacity = capacity;
        enrolled.set(0);
    }

    public Subject getSubject() { return subject; }
    public int getSubjectNumber(){ return subject.getNumber(); }

    public ReadOnlyIntegerProperty sbjNumberProperty(){
        ReadOnlyIntegerProperty sbjNumber = subject.numberProperty();
        return sbjNumber;
    }
    
    public final String getGroup() { return group; }
    
    public final int getNumber() { return number; }
    
    public final String getDay() { return day; }
    
    public final int getStart() { return start; }
    
    public final int getDuration() { return duration; }
    
    public final String getRoom() { return room; }
    
    public final int getCapacity() { return capacity; }
    
    public final int getEnrolled() { return enrolled.get(); }
    private void setEnrolled(int enrolled) { this.enrolled.set(enrolled); }
    public ReadOnlyIntegerProperty enrolledProperty() { return enrolled; }

    public boolean canEnrol() {
        return getEnrolled() < getCapacity();
    }

    public void enrol() {
        setEnrolled(getEnrolled() + 1);
    }

    public void withdraw() {
        setEnrolled(getEnrolled() - 1);
    }

    public boolean matches(int subjectNumber, String group) {
        return subject.matches(subjectNumber) && group.equals(this.group);
    }

    @Override
    public String toString() {
        return String.format("%d %s %d %s %s %02d:00 %dhrs %d/%d", subject.getNumber(), group, number, day, room, start, duration, enrolled, capacity);
    }
}
