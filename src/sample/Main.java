package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Player player = new Player("file:///C:/ducktales.mp4");
        Scene scene = new Scene(player,480, 720, Color.BLACK);
    }


    public static void main(String[] args) {
        launch(args);
    }
}