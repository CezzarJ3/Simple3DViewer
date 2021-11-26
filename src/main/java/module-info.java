module com.vsu.cgcourse {
    requires javafx.controls;
    requires javafx.fxml;
    requires vecmath;
    requires java.desktop;
    requires javafx.graphics;


    opens com.vsu.cgcourse to javafx.fxml;
    exports com.vsu.cgcourse;
}