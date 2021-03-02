package com.coreServices.Songs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SongsApplication extends Application {
	private ConfigurableApplicationContext springContext;
	private FXMLLoader fxmlLoader;

	public static void main(String[] args) {
		launch(args);
		//SpringApplication.run(SongsApplication.class, args);
	}

	@Override
	public void init() {
		springContext = SpringApplication.run(SongsApplication.class);
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(springContext::getBean);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		fxmlLoader.setLocation(getClass().getResource("/fxml/sample.fxml"));
		Parent rootNode = fxmlLoader.load();

		primaryStage.setTitle("Songs");
		Scene scene = new Scene(rootNode, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void stop() {
		springContext.stop();
	}
}
