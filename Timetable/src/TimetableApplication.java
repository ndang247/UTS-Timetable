import au.edu.uts.ap.javafx.*;
import model.*;
import javafx.application.*;
import javafx.stage.*;

public class TimetableApplication extends Application {
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage stage) throws Exception {
        University university = new University();
        // These were created for testing.
        // university.addStudent("12345678", "Bianca Sladen", "ft", true);
        // university.addStudent("49287512", "Hugo Aitken", "ft", false);
        // university.addStudent("23232323", "Jessica Sneddon", "pt", false);
        // university.addStudent("11111111", "Dakota Cavill", "ft", true);
        ViewLoader.showStage(university, "/view/university.fxml", "University", stage);
    }
}
