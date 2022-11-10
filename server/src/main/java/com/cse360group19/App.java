package com.cse360group19;

import java.io.IOException;

import com.cse360group19.server.Authenticate;
import com.cse360group19.server.OrderStorage;
import com.cse360group19.server.Server;

import javafx.application.Application;
import javafx.stage.Stage;

public final class App extends Application {
    private App() {
    }

    public static void main(String[] args) {
        Server server = new Server();
        OrderStorage orderStorage = new OrderStorage();
        launch();
        try {
            server.listen(orderStorage);
        }
        catch(IOException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Authenticate.launch();
        while(Authenticate.delay) {
            /* Infinite loop waiting for Authenticate to finish */
        }
    }
}
