package com.coreServices.Songs.controller;

import com.coreServices.Songs.domain.Song;
import com.coreServices.Songs.service.DbService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;

@Controller
public class JavaFxController {

    @FXML
    private TableView<Song> table;
    @FXML
    private TableColumn<Song, Long> id;
    @FXML
    private TableColumn<Song, String> title;
    @FXML
    private TableColumn<Song, String> author;
    @FXML
    private TableColumn<Song, Long> votes;
    private Stage stage;
    @Autowired
    private DbService service;


    @FXML
    public void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<Song, Long>("id"));
        title.setCellValueFactory(new PropertyValueFactory<Song, String>("title"));
        author.setCellValueFactory(new PropertyValueFactory<Song, String>("author"));
        votes.setCellValueFactory(new PropertyValueFactory<Song, Long>("votes"));
    }


    public void check() {
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("something wrong");
            }
        }
    }


    public void load(ActionEvent actionEvent) {
        table.refresh();
        table.setItems(FXCollections.observableArrayList(service.getAllSongs()));
    }
}
