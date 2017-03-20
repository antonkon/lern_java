package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Scene winScene;
    public static Stage win;
    public static Parent par;

    public static void getWin() {
        win.setScene(winScene = new Scene(par));
        win.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        win = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("winMain.fxml"));
        par = root;
        primaryStage.setTitle("Повторение слов");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}