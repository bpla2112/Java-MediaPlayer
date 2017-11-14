package sample;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;

public class MediaBar extends HBox {

    //establish controls and labels
    Slider time = new Slider();
    Slider volume = new Slider();
    Button playBtn = new Button("||");
    Label volumeLbl = new Label("Volume: ");
    MediaPlayer player;

    //constructor
    public MediaBar(MediaPlayer play){
        player = play;

        //set alignment and padding
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5, 10, 5, 10));

        //set preferences for volume slider
        volume.setPrefWidth(70);
        volume.setMinWidth(30);
        volume.setValue(100);

        //set the priority for the Horizontal box
        HBox.setHgrow(time, Priority.ALWAYS);

        //set preferences for the Play Button
        playBtn.setPrefWidth(30);

        //add the controls to the Media Bar object
        getChildren().add(playBtn);
        getChildren().add(time);
        getChildren().add(volumeLbl);
        getChildren().add(volume);

        //play button actions event handler
        playBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                MediaPlayer.Status status = player.getStatus();

                if(status == MediaPlayer.Status.PLAYING){
                    if(player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())){
                        player.seek(player.getStartTime());
                        player.play();
                    }
                    else{
                        player.pause();
                        playBtn.setText(">");
                    }
                }

                if(status == MediaPlayer.Status.PAUSED || status == MediaPlayer.Status.HALTED || status == MediaPlayer.Status.STOPPED){
                    player.play();
                    playBtn.setText("||");
                }
            }
        });

        //set time slider to follow along with the video
        player.currentTimeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable ov) {
                updateValue();
            }
        });

        time.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable ov) {
                if(time.isPressed()){
                    player.seek(player.getMedia().getDuration().multiply(time.getValue()/100));
                }
            }
        });
    }

    protected void updateValue(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                time.setValue(player.getCurrentTime().toMillis()/player.getTotalDuration().toMillis()*100);
            }
        });
    }
}
