package sample;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;


public class Player extends BorderPane{

    //declare objects for media player
    Media media;
    MediaPlayer player;
    MediaView view;
    Pane mpane;
    MediaBar bar;

    //class constructor
    public Player(String file){

        //instantiate declared objects as new instances
        media = new Media(file);
        player = new MediaPlayer(media);
        view = new MediaView(player);
        mpane = new Pane();

        //add view to the media pane
        mpane.getChildren().add(view);

        //set the center of the pane
        setCenter(mpane);

        //add the media bar to the player
        bar = new MediaBar(player);

        //set the style properties of the bar which is really just the alignment and the color
        setBottom(bar);
        setStyle("-fx-background-color: #bfc2c7");

        player.play();
    }
}
