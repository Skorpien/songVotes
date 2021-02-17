package com.coreServices.Songs.controller;

import com.coreServices.Songs.service.DbService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;

@Controller
public class JavaFxController {

    private Stage stage;
    @Autowired
    private DbService service;

    @FXML
    private Button button;

    @FXML
    public void initialize() {
        button.setText("Load");
    }


    public void myAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV files", "*.csv"),
                new FileChooser.ExtensionFilter("XML Files", "*.xml")
        );
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            if (file.getAbsolutePath().endsWith(".csv")) {
                try {
                    service.csvReader(file.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (file.getAbsolutePath().endsWith(".xml")){
                try {
                    service.xmlReader(file);
                }catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("something wrong");
            }
        }
    }

    public void open() {
        System.out.println(service.getAllSongs().size());
        try {
            System.out.println(service.getSongById(1L).getAuthor());
        }catch (Exception e) {
            System.out.println("empty");
        }

    }
}
