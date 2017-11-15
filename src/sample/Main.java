package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.io.File;
import java.net.MalformedURLException;


public class Main extends Application {

    //globals
    Player player;
    FileChooser fileChooser;

    //create menu bar as global to get menu on any document opening
    MenuItem open = new MenuItem("Open");
    Menu file = new Menu("File");
    MenuBar menu = new MenuBar();
    public void start(final Stage primaryStage) {

        file.getItems().add(open);
        menu.getMenus().add(file);

        fileChooser = new FileChooser();

        //open actions
        open.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                player.player.pause();
                File file = fileChooser.showOpenDialog(primaryStage);
                if(file != null){
                    try {
                        player = new Player(file.toURI().toURL().toExternalForm());
                        Scene scene = new Scene(player, 854, 545, Color.BLACK);
                        primaryStage.setScene(scene);
                        player.setTop(menu);
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        });

        //player properties
        player = new Player("file:///C:/ducktales.mp4");
        player.setTop(menu);
        Scene scene = new Scene(player,854, 545, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }
}
