package com.clickcounter.countclicks;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONArray;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class HelloApplication extends Application {
    HelloController controller;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();

        LoadStoreInfo(controller);

        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private void LoadStoreInfo(HelloController controller) throws Exception {
        URL url = HelloApplication.class.getResource("store.json");
        Path jsonPath = Path.of(url.getPath());
        byte[] storeBytes = Files.readAllBytes(jsonPath);
        String storeString = new String(storeBytes, StandardCharsets.UTF_8);
        JSONArray jsonStore = new JSONArray(storeString);
        controller.setStores(jsonStore);
    }

    public static void main(String[] args) {
        launch();
    }
}