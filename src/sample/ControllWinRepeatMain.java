package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

/*
 * Created by radio on 07.03.16.
 */
public class ControllWinRepeatMain {

    public static int lang;
    public static Integer numbWord;

    @FXML
    private ComboBox<Integer> repeatValue;
    @FXML
    private Button butBack;
    @FXML
    private Button butRUEN;
    @FXML
    private Button butENRU;

    @FXML
    private void initialize(){

        butBack.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        ButBack();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        butRUEN.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        ButRUEN();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        butENRU.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        ButENRU();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        repeatValue.getItems().addAll(5,10,20,50);
        repeatValue.setValue(5);
    }

    public void ButBack() throws IOException {
        Main.par = FXMLLoader.load(getClass().getResource("winMain.fxml"));
        Main.getWin();
    }

    public void ButRUEN() throws IOException {
        lang = 12;
        numbWord = repeatValue.getValue();
        Main.par = FXMLLoader.load(getClass().getResource("winRepeating.fxml"));
        Main.getWin();
    }
    public void ButENRU() throws IOException {
        lang = 21;
        numbWord = repeatValue.getValue();
        Main.par = FXMLLoader.load(getClass().getResource("winRepeating.fxml"));
        Main.getWin();
    }
}
