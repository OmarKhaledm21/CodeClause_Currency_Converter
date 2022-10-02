module com.example.currencyconverterv {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    requires com.almasb.fxgl.all;
    requires java.sql;
    requires java.net.http;
    requires com.google.gson;

    opens Controllers to javafx.fxml;
    exports Controllers;

}