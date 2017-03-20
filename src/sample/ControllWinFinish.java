package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

/*
 * Created by radio on 23.03.16.
 */
public class ControllWinFinish {

    @FXML
    private Label labT;
    @FXML
    private Label labF;
    @FXML
    private Button butFinish;
    @FXML
    private Button butContinue;

    @FXML
    public void initialize(){

        butFinish.setOnKeyPressed(new EventHandler<KeyEvent>() {
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

        butContinue.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        ButContinue();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        int p = 0,m = 0;

        for (int i = 0; i < ControllWinRepeatMain.numbWord; i++) {
            if (ControllWinRepeating.listTF[i])
                p++;
            else m++;
        }

        labT.setText(String.valueOf(p));
        labF.setText(String.valueOf(m));

    }

    public void ButExit() throws IOException {
        Main.par = FXMLLoader.load(getClass().getResource("winMain.fxml"));
        Main.getWin();
    }

    public void ButContinue() throws IOException {
        Main.par = FXMLLoader.load(getClass().getResource("winRepeatMain.fxml"));
        Main.getWin();
    }
}
