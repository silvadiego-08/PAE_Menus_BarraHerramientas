module uam.edu.ni.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    opens uam.edu.ni.demo to javafx.fxml;
    opens controladores to javafx.fxml;
    opens modelos to javafx.base;

    exports uam.edu.ni.demo;
}