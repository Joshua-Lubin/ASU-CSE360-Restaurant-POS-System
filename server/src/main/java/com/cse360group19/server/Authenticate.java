package com.cse360group19.server;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Authenticate extends Application {
    String password = "admin";
    public static boolean admin = false;
    public static boolean delay = true;
    @Override
    public void start(Stage stage) throws IOException {
        Label label = new Label("Enter the password to access restaurant interface.");
        TextField txt_password = new TextField();
        Button btn_login = new Button("Login");
        Button btn_cancel = new Button("Cancel");
        GridPane pane = new GridPane();
        pane.add(label, 0, 0);
        pane.add(txt_password, 0, 1);
        pane.add(btn_login, 0, 2);
        pane.add(btn_cancel, 1, 2);
        btn_cancel.setOnAction(actionEvent -> {
            stage.hide();
            delay = false;
        });
        btn_login.setOnAction(actionEvent -> {
            if(txt_password.getText().equals(password)) {
                admin = true;
                stage.hide();
                delay = false;
            }
        });

        Scene scene = new Scene(pane);
        stage.setTitle("SunDevil Pizza Shop");
        stage.setScene(scene);
        stage.show();
    }
}