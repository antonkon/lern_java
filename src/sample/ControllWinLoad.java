package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.*;

/*
 * Created by radio on 07.03.16.
 */
public class ControllWinLoad {

    @FXML
    private TextField textRu;
    @FXML
    private TextField textEn;
    @FXML
    private Button butBack;

    @FXML
    public void initialize(){

        butBack.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        Main.par = FXMLLoader.load(getClass().getResource("winMain.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Main.getWin();
                }
            }
        });
    }

    public void ButBack() throws IOException {
        Main.par = FXMLLoader.load(getClass().getResource("winMain.fxml"));
        Main.getWin();
    }

    public void writeWord() {
        if ( textRu.getText().equals("") || textEn.getText().equals("") ) return;
        write(textRu.getText(),textEn.getText());
        textRu.clear();
        textEn.clear();
        //System.out.println("записалось");
        // переместить фокус на textfild ru

    }
    public static void write(String ru,String en) {
        try {
            String url = ControllWinLoad.class.getResource("").toString();
            if (url.contains("jar:file:/"))
                url = url.substring(url.indexOf("jar:file:") + 9, url.indexOf("lernEngl_java.1.0.jar!")) + "Словарь.txt";
            else url = "Словарь.txt";
//----------------------------------------------------------------------------------------------------------------------
            FileOutputStream fout = new FileOutputStream(url, true);

            String s ="[" + ru + "]{" + en + "}0\n";
            fout.write(s.getBytes("UTF8"));

            fout.close();           // после чего мы должны закрыть файл Иначе файл не запишется
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
