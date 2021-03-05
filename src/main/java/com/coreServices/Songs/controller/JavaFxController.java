package com.coreServices.Songs.controller;

import com.coreServices.Songs.domain.Category;
import com.coreServices.Songs.domain.Song;
import com.coreServices.Songs.service.DbService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    public MenuItem loadFile;
    @FXML
    public MenuItem saveFile;
    @FXML
    public Button clearVotes;
    @FXML
    public MenuItem clearAllVotes;
    @FXML
    public ChoiceBox<Category> categoryBox;
    @FXML
    public Button showAllSongs;
    @FXML
    public Button top3;
    @FXML
    public Button top10;
    @FXML
    public Button addVote;
    @FXML
    public TableView<Song> table;
    @FXML
    public TableColumn<Song, Long> id;
    @FXML
    public TableColumn<Song, String> title;
    @FXML
    public TableColumn<Song, String> author;
    @FXML
    public TableColumn<Song, String> album;
    @FXML
    public TableColumn<Song, Category> genre;
    @FXML
    public TableColumn<Song, Long> votes;
    public Stage stage;
    @Autowired
    public DbService service;

    /**
     * initializing table and switch off buttons until the database is loaded.
     */
    @FXML
    public void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        album.setCellValueFactory(new PropertyValueFactory<>("album"));
        genre.setCellValueFactory(new PropertyValueFactory<>("category"));
        votes.setCellValueFactory(new PropertyValueFactory<>("votes"));
        showAllSongs.setDisable(true);
        top3.setDisable(true);
        top10.setDisable(true);
        addVote.setDisable(true);
        clearVotes.setDisable(true);
        clearAllVotes.setDisable(true);
        categoryBox.setDisable(true);
        saveFile.setDisable(true);
        categoryBox.getItems().addAll(Category.values());
    }

    /**
     * gets songs from the database.
     */
    public void showAllSongs() {
        table.refresh();
        table.setItems(FXCollections
                .observableArrayList(service.getAllSongs()));

    }

    /**
     * shows three songs with the most votes.
     */
    public void top3() {
        table.refresh();
        table.setItems(FXCollections.observableArrayList(service.getTop3()));
    }

    /**
     * shows ten songs with the most votes.
     */
    public void top10() {
        table.refresh();
        table.setItems(FXCollections.observableArrayList(service.getTop10()));
    }

    /**
     * adds the vote to the selected song.
     */
    public void addVote() {
        try {
            table.getSelectionModel().getSelectedItem().addVote();
        } catch (NullPointerException e) {
            System.out.println("List is empty");
        }
        table.refresh();
    }

    /**
     * shows songs from the selected category.
     */
    public void setCategoryBox() {
        Category category = categoryBox.getValue();
        table.refresh();
        table.setItems(FXCollections
                .observableArrayList(service.getByCategory(category)));
    }

    /**
     * removes votes from the selected song.
     */
    public void clearVotes() {
        try {
            table.getSelectionModel().getSelectedItem().clearVotes();
            table.refresh();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * removes votes from all songs.
     */
    public void clearAllVotes() {
        try {
            for (Song song : service.getAllSongs()) {
                song.clearVotes();
                table.refresh();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * loads the file from the localization specified by the user
     * activates the buttons (only when file adds songs to database).
     */
    public void loadFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV files", "*.csv"),
                new FileChooser.ExtensionFilter("XML Files", "*.xml")
        );
        File file = fileChooser.showOpenDialog(stage);

        service.parserChecker(file);

        if (!service.getAllSongs().isEmpty()) {
            top3.setDisable(false);
            top10.setDisable(false);
            addVote.setDisable(false);
            clearVotes.setDisable(false);
            clearAllVotes.setDisable(false);
            saveFile.setDisable(false);
            showAllSongs.setDisable(false);
            categoryBox.setDisable(false);
        }
    }

    /**
     * saves the file to the localization specified by the user.
     * @throws CsvRequiredFieldEmptyException - no data to write to the specified field
     * @throws IOException - file was not written correctly
     * @throws CsvDataTypeMismatchException - incorrect data in .csv file
     */
    public void saveFile() throws CsvRequiredFieldEmptyException,
            IOException, CsvDataTypeMismatchException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter xml = new FileChooser
                .ExtensionFilter("XML Files", "*.xml");
        FileChooser.ExtensionFilter csv = new FileChooser
                .ExtensionFilter("CSV files", "*.csv");
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

    /**
     * switch off application.
     */
    public void close() {
        Platform.exit();
    }

}