package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.io.*;
import java.util.Random;


/*
 * Created by radio on 07.03.16.
 */
public class ControllWinRepeating {

    private static String listRu[] = new String[ControllWinRepeatMain.numbWord];
    private static String listEn[] = new String[ControllWinRepeatMain.numbWord];
    public static Boolean listTF[] = new Boolean[ControllWinRepeatMain.numbWord];
    private int countWord = -1;

    boolean t = false; // для прохода AnimationTimer и отрисовывания изменений, флаг ...

    @FXML
    private Label word;
    @FXML
    private Label youWord;
    @FXML
    private TextField translWord;
    @FXML
    private Button butProv;
    @FXML
    private Button butNKnow;


    @FXML
    public void initialize(){

        butProv.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    Proverka();
                }
            }
        });

        butNKnow.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    nKnow();
                }
            }
        });

        translWord.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (translWord.getText().equals("")) {
                    butProv.setDisable(true);
                    translWord.setOnKeyPressed(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent event) {
                        }
                    });
                } else {
                    butProv.setDisable(false);
                    translWord.setOnKeyPressed(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent event) {
                            if (event.getCode() == KeyCode.ENTER){
                                if (translWord.getStyle().equals("-fx-background-color: #e40f00"))
                                    nextWord();
                                else Proverka();
                            }
                        }
                    });
                }
            }
        });

        butProv.setDisable(true);

        read(ControllWinRepeatMain.numbWord);

        nextWord();
    }

    private void read(int numbWord){            // numbWord - количество загружаемых слов
        try {
            String url =this.getClass().getResource("").toString();
            System.out.println(url);


            if (url.contains("jar:file:/")){
                url = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
                System.out.println(url);
                int i;
                for (i = 0; i < url.length(); i++) {
                    if (url.substring(url.length()-1-i,url.length()-i).equals("/")) break;
                }
                url = url.substring(url.indexOf("file:") + 5, url.length()-i) + "Словарь.txt";
            }
            else url = "Словарь.txt";

            System.out.println(url);

            File f = new File(url);
            if (!f.exists()) {
                System.out.println("Ошибка открытия словаря !");
                //translWord.setText("Ошибка открытия словаря !");
                return;
            }

            RandomAccessFile fout = new RandomAccessFile(f, "rw");          // открытие файла на запись
            BufferedReader fin = new BufferedReader(new FileReader(f));
                    // открытие файла на чтение
            do{
                fout.readLine();
            } while (!fin.readLine().contains("*"));            // поиск * (флага) и перемещение указателя "на запись"

            fout.seek(fout.getFilePointer() - 2);               // для любой строчке, перемещение указателя для замены (* -> 0)
            fout.write("0".getBytes("UTF8"));           // преобразование символа в UTF-8, и вывод его в файл

            int i=0;            // i - индекс для заполнения массивов по порядку

            do {
                String s;
                while (true){                               // формирование строки
                    s = fin.readLine();
                    fout.readLine();            // тянем за сабой указатель куда выводить

                    if ((s==null) || s.equals("")) {              // проверяем на конец файла, если да то переходим в начало файла
                        fin.close();
                        fin = new BufferedReader(new FileReader(f));
                        fout.seek(0);
                    } else break;
                }

                listRu[i] = s.substring(s.indexOf('[')+1,s.indexOf(']'));         // считывание слов и заполнение массива
                listEn[i] = s.substring(s.indexOf('{') + 1,s.indexOf('}'));
                i++;
            } while (i < ControllWinRepeatMain.numbWord);


            fout.seek(fout.getFilePointer() - 2);
            fout.write("*".getBytes("UTF8"));

            fout.close();               // закрываем файлы
            fin.close();

            // рандомно раскидать слова

            String s;
            Random rand = new Random(System.currentTimeMillis());
            for (i = 0; i < ControllWinRepeatMain.numbWord; i++) {
                int z1 = rand.nextInt(ControllWinRepeatMain.numbWord);
                int z2 = rand.nextInt(ControllWinRepeatMain.numbWord);
                while (z1 == z2) z2 = rand.nextInt(ControllWinRepeatMain.numbWord);
                s = listRu[z1];
                listRu[z1] = listRu[z2];
                listRu[z2] = s;
                s = listEn[z1];
                listEn[z1] = listEn[z2];
                listEn[z2] = s;
            }

        } catch (FileNotFoundException e) {             // обработка ошибок
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void nextWord() {
        countWord++;

        if (countWord >= ControllWinRepeatMain.numbWord) {
            try {
                Main.par = FXMLLoader.load(getClass().getResource("winFinish.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Main.getWin();
            return;
        }

        butProv.setText("Проверить");
        youWord.setText("");
        if (ControllWinRepeatMain.lang == 12)
            word.setText(listRu[countWord]);
        else word.setText(listEn[countWord]);

        butProv.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    Proverka();
                }
            }
        });


        translWord.setStyle("");
        translWord.setText("");
        butNKnow.setDisable(false);
        butProv.setDisable(true);
    }


    public void prov() {

        if (ControllWinRepeatMain.lang == 12) {
            if (translWord.getText().equals(listEn[countWord])) {
                listTF[countWord] = true;
                translWord.setStyle("-fx-background-color: #24e400");
            }
            else { listTF[countWord] = false;
                translWord.setStyle("-fx-background-color: #e40f00");
                youWord.setText(listEn[countWord]);
                translWord.setText(translWord.getText());
            }
        }
        else {
            if (translWord.getText().equals(listRu[countWord])) {
                listTF[countWord] = true;
                translWord.setStyle("-fx-background-color: #24e400");
            } else {
                listTF[countWord] = false;
                translWord.setStyle("-fx-background-color: #e40f00");
                youWord.setText(listRu[countWord]);
                translWord.setText(translWord.getText());
            }
        }
    }



    protected AnimationTimer at = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if (t){

                if (listTF[countWord]){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    nextWord();
                }
                else{
                    butNKnow.setDisable(true);
                    butProv.setText("Продолжить");
                    butProv.setDisable(false);
                    butProv.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            nextWord();
                        }
                    });
                    butProv.setOnKeyPressed(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent event) {
                            if (event.getCode() == KeyCode.ENTER) {
                                nextWord();
                            }
                        }
                    });
                    translWord.setOnKeyPressed(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent event) {
                            if (event.getCode() == KeyCode.ENTER) {
                                nextWord();
                            }
                        }
                    });

                }

                at.stop();
                t = false;
            } else t = true;
        }
    };

    public void Proverka() {

        // -fx-background-color: - цвет текста в TextField


        prov();

        at.start();
    }

    public void nKnow() {
        if(ControllWinRepeatMain.lang == 12)            // выводим правильное слово (определяя из какого языка)
            translWord.setText(listEn[countWord]);
        else translWord.setText(listRu[countWord]);

        listTF[countWord] = false;          // помечаем как незнакомое слово
        at.start();
        butNKnow.setDisable(true);          // делаем кнопку не активной
    }
}
