package uam.edu.ni.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class gueguenseApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(gueguenseApplication.class.getResource("gueguense-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Distribuidora El Gueguense");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
