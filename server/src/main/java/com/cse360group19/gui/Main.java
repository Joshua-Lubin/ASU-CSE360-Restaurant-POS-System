package com.cse360group19.gui;

import java.io.IOException;

import com.cse360group19.server.Server;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    private Server server;
    
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        server = new Server();

        HostServices hostServices = getHostServices();

        Configure configurePane = new Configure(stage, server, hostServices);
        Scene configureScene = new Scene(configurePane);

        stage.setTitle("SunDevil Pizza Shop Admin Interface");
        stage.setScene(configureScene);
        stage.setResizable(true);
        stage.setWidth(800);
        stage.setHeight(350);
        stage.show();
    }

    @Override
    public void stop() {
        server.stop();
    }
}