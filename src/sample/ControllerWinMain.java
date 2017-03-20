package sample;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;



public class ControllerWinMain {

    @FXML
    private Button butMainRepet;
    @FXML
    private Button butMainLoad;
    @FXML
    private Button butMainExit;

    @FXML
    public void initialize(){

        butMainRepet.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        ButRepeat();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        butMainLoad.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        ButLoad();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        butMainExit.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        ButExit();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void ButRepeat() throws IOException {
        Main.par = FXMLLoader.load(getClass().getResource("winRepeatMain.fxml"));
        Main.getWin();
    }

    public void ButLoad() throws IOException {
        Main.par = FXMLLoader.load(getClass().getResource("winLoad.fxml"));
        Main.getWin();
        //textRu.getInputMethodRequests(locale);
    }

    public void ButExit() throws IOException {
        Main.win.close();
        Platform.exit();
        System.exit(0);
    }
}