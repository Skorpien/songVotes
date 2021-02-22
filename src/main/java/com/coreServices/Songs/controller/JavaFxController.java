package com.coreServices.Songs.controller;

import com.coreServices.Songs.domain.Category;
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
    public TableColumn<Song, String> album;
    @FXML
    public TableColumn<Song, Category> genre;
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
        album.setCellValueFactory(new PropertyValueFactory<Song, String>("album"));
        genre.setCellValueFactory(new PropertyValueFactory<Song, Category>("category"));
        votes.setCellValueFactory(new PropertyValueFactory<Song, Long>("votes"));
    }


    public void check() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV files", "*.csv"),
                new FileChooser.ExtensionFilter("XML Files", "*.xml")
        );
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) { // go to DbService
            if (file.getAbsolutePath().endsWith(".csv")) {
                try {
                    service.readCsvFile(file.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (file.getAbsolutePath().endsWith(".xml")){
                try {
                    service.readXmlFile(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("something wrong");
            }
        }
    }

    public void top3(ActionEvent actionEvent) {
        table.refresh();
        table.setItems(FXCollections.observableArrayList(service.getTop3()));
    }

    public void top10(ActionEvent actionEvent) {
        table.refresh();
        table.setItems(FXCollections.observableArrayList(service.getTop10()));
    }

    public void load(ActionEvent actionEvent) {
        table.refresh();
        table.setItems(FXCollections.observableArrayList(service.getAllSongs()));

        System.out.println(service.getSongById(1).getCategory()); //for test only
    }

    public void addVote(ActionEvent actionEvent) {
        table.getSelectionModel().getSelectedItem().addVote();
        table.refresh();
    }
}