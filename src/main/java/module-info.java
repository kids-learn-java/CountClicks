module com.clickcounter.countclicks {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.clickcounter.countclicks to javafx.fxml;
    exports com.clickcounter.countclicks;
}