package main.java.controllers.DBManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;
import main.java.Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class TitledPaneLoader {

    public static TitledPane getTitledPane(String fileName) {
        TitledPane titledPane = null;
        try {
            URL fileUrl = Main.class.getResource("../fxml/DBManager/" + fileName + ".fxml");
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException("Can't find file " + fileName);
            }
            titledPane = FXMLLoader.load(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return titledPane;
    }

}
