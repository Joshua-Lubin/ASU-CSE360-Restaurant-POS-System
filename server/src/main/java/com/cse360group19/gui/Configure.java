package com.cse360group19.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.cse360group19.data_structures.Item;
import com.cse360group19.data_structures.Order;
import com.cse360group19.data_structures.OrderStorage;
import com.cse360group19.data_structures.PasswordStorage;
import com.cse360group19.server.Server;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Configure extends GridPane {
    private FileChooser fileChooser;

    private Stage stage;
    //private TextField portField;
    private TextField passwordField;
    private Text fileText;
    private Text errorText;

    private File file;
    private Server server;
    private HostServices hostServices;

    public Configure(Stage stage, Server server, HostServices hostServices) {
        this.hostServices = hostServices;
        this.server = server;
        this.stage = stage;
        fileChooser = new FileChooser();
        ExtensionFilter fileExtensions = new ExtensionFilter("TEXT files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(fileExtensions);
        file = null;

        this.setPadding(new Insets(20));

        Label passwordLabel = new Label("Set password:");
        passwordField = new TextField("admin");
        HBox passwordBox = new HBox(10, passwordLabel, passwordField);

        //Label portLabel = new Label("Set server port:");
        //portField = new TextField("3000");
        //HBox portBox = new HBox(10, portLabel, portField);

        Label fileLabel = new Label("Load order storage file:");
        Button addFileButton = new Button("Add a file");
        Button removeFileButton = new Button("Remove file");
        fileText = new Text();
        HBox fileBox = new HBox(10, fileLabel, addFileButton, removeFileButton, this.fileText);
        addFileButton.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    File file = fileChooser.showOpenDialog(stage);
                    
                    if(file != null) {
                        setOrderStorageFile(file);
                    }
                }
            }
        );
        removeFileButton.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    removeOrderStorageFile();
                }
            }
        );

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    submit();
                }
            }
        );

        errorText = new Text();

        VBox formBox = new VBox(10, passwordBox, /*portBox,*/ fileBox, submitButton, errorText);
        this.add(formBox, 0, 0);
    }

    public void setOrderStorageFile(File file) {
        try {
            errorText.setText("");
            this.file = file;
            fileText.setText(file.getCanonicalPath());
        }
        catch(Exception e) {
            errorText.setText("File error! Choose another file.");
        }
    }

    public void removeOrderStorageFile() {
        this.file = null;
        fileText.setText("");
    }

    public void submit() {
        try {
            errorText.setText("");

            OrderStorage orderStorage = new OrderStorage();

            if(file != null) {
                InputStream inputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                JSONObject json = (JSONObject) new JSONParser().parse(inputStreamReader);

                JSONArray orders = ((JSONArray) json.get("orders"));

                for(int k = 0; k < orders.size(); k++) {
                    Order newOrder = new Order();

                    JSONObject order = (JSONObject) orders.get(k);

                    JSONArray items = (JSONArray) order.get("items");

                    newOrder.id = (int) (long) order.get("id");

                    for (int i = 0; i < items.size(); i++) {
                        JSONObject item = (JSONObject) items.get(i);

                        int quantity;
                        String id;
                        String[] options;

                        quantity = Integer.parseInt(item.get("quantity").toString());

                        id = item.get("id").toString();

                        Object[] rawOptions = ((JSONArray) item.get("options")).toArray();
                        options = new String[rawOptions.length];

                        for (int j = 0; j < rawOptions.length; j++) {
                            options[j] = rawOptions[j].toString();
                        }

                        newOrder.items.add(new Item(options, id, quantity));
                    }

                    orderStorage.orders.add(newOrder);
                }
            }            

            PasswordStorage passwordStorage = new PasswordStorage(passwordField.getText());

            int port = /*Integer.parseInt(portField.getText())*/ 3000;

            server.createServer(port, orderStorage, passwordStorage);
            
            Dashboard dashboardPane = new Dashboard(stage, server, orderStorage, passwordStorage, port, hostServices);
            Scene dashboardScene = new Scene(dashboardPane);

            stage.setScene(dashboardScene);
        }
        catch(IOException e) {
            errorText.setText("Server failed to launch! Your port is most likely restricted. Avoid restricted port values like 80 and 443 and make sure your port is an integer from 1-65535.");
        }
        catch(IllegalArgumentException e) {
            errorText.setText("Port invalid! Please enter an integer from 1-65535. Port 3000 is recomended.");
        }
        catch(Exception e) {
            System.out.println(e);
            errorText.setText("Unknown error! Please try again.");
        }
    }
}
