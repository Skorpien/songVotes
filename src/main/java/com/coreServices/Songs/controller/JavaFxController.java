package com.coreServices.Songs.controller;

import com.coreServices.Songs.domain.Category;
import com.coreServices.Songs.domain.Song;
import com.coreServices.Songs.service.DbService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    private Button clearVotes;
    @FXML
    private Button clearAllVotes;
    @FXML
    private ChoiceBox<Category> categoryBox;
    @FXML
    private Button top3;
    @FXML
    private Button top10;
    @FXML
    private Button saveToFile;
    @FXML
    private Button addVote;
    @FXML
    private Button load;
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
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        album.setCellValueFactory(new PropertyValueFactory<>("album"));
        genre.setCellValueFactory(new PropertyValueFactory<>("category"));
        votes.setCellValueFactory(new PropertyValueFactory<>("votes"));
        top3.setDisable(true);
        top10.setDisable(true);
        saveToFile.setDisable(true);
        addVote.setDisable(true);
        load.setDisable(true);
        clearVotes.setDisable(true);
        clearAllVotes.setDisable(true);
        categoryBox.setDisable(true);
        categoryBox.getItems().addAll(Category.values());
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
            } else if (file.getAbsolutePath().endsWith(".xml")) {
                try {
                    service.readXmlFile(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("something wrong");
            }
        }

        if (!service.getAllSongs().isEmpty()) {
            top3.setDisable(false);
            top10.setDisable(false);
            saveToFile.setDisable(false);
            addVote.setDisable(false);
            load.setDisable(false);
            clearVotes.setDisable(false);
            clearAllVotes.setDisable(false);
            categoryBox.setDisable(false);
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
        try {
            table.getSelectionModel().getSelectedItem().addVote();
        } catch (NullPointerException e) {
            System.out.println("List is empty");
        }
        table.refresh();
    }

    public void setCategoryBox(ActionEvent actionEvent) {
        Category category = categoryBox.getValue();
        table.refresh();
        table.setItems(FXCollections.observableArrayList(service.getByCategory(category)));
    }

    public void saveToFile(ActionEvent actionEvent) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter xml = new FileChooser.ExtensionFilter("XML Files", "*.xml");
        FileChooser.ExtensionFilter csv = new FileChooser.ExtensionFilter("CSV files", "*.csv");
        fileChooser.getExtensionFilters().addAll(csv, xml);


        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            if (file.getAbsolutePath().endsWith(".csv")) {
                service.writeToCsvFile(table.getItems(), file.getAbsolutePath());
            } else if (file.getAbsolutePath().endsWith(".xml")) {
                service.writeToXmlFile(table.getItems(), file.getAbsolutePath());
            } else {
                System.out.println("something wrong");
            }

        }
    }

    public void clearVotes(ActionEvent actionEvent) {
        try {
            table.getSelectionModel().getSelectedItem().clearVotes();
            table.refresh();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void clearAllVotes(ActionEvent actionEvent) {
        try {
            for (Song song : service.getAllSongs()) {
                song.clearVotes();
                table.refresh();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}