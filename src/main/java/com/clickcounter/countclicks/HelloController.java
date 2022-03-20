package com.clickcounter.countclicks;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleFloatProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

class StoreItem {
    JSONObject info;
    int numOwned;
    float cookieGenerated;
}

public class HelloController {
    @FXML
    private ImageView cookie;

    @FXML
    private Label welcomeText;

    @FXML
    private ListView<String> store;

    ArrayList<StoreItem> items = new ArrayList<>();

    JSONArray jsonStore;

    ScaleTransition st;

    SimpleFloatProperty numCookies = new SimpleFloatProperty(0);

    float cookiePerSec = (float)0.5;
    Timer cookieTimer = new Timer();

    @FXML
    protected void onCookieClicked() {
        addCookie(1);
    }

    void addCookie(int n) {
        numCookies.set(numCookies.getValue() + n);
        //welcomeText.setText(String.valueOf(numCookies));
        st.play();
    }

    @FXML
    protected void onHelloButtonClick()
    {
        addCookie(10);
    }

    public void setStores(JSONArray theStore) {
        jsonStore = theStore;
        for (int i = 0; i < jsonStore.length(); ++i) {
            StoreItem item = new StoreItem();
            item.info = jsonStore.getJSONObject(i);

            String itemName = item.info.getString("name");
            store.getItems().add(itemName);
        }
    }

    @FXML
    void initialize() {
        st = new ScaleTransition(Duration.millis(100), cookie);
        st.setByX(-0.1f);
        st.setByY(-0.1f);
        st.setCycleCount(2);
        st.setAutoReverse(true);
        welcomeText.textProperty().bind(numCookies.asString("%.0f"));
        cookieTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    numCookies.set(numCookies.getValue() + cookiePerSec / 2);
                });
            }
        }, 0, 500);
    }
}