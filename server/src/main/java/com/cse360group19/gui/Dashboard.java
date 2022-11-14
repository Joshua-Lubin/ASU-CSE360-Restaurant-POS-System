package com.cse360group19.gui;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

import com.cse360group19.data_structures.Item;
import com.cse360group19.data_structures.Order;
import com.cse360group19.data_structures.OrderStorage;
import com.cse360group19.data_structures.PasswordStorage;
import com.cse360group19.server.Server;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Dashboard extends GridPane {
    private Text currentStatus;
    private Server server;
    private int port;
    private PasswordStorage passwordStorage;
    private OrderStorage orderStorage;
    private Button toggleStatus;
    private HostServices hostServices;

    public Dashboard(Stage stage, Server server, OrderStorage orderStorage, PasswordStorage passwordStorage, int port, HostServices hostServices) {
        this.hostServices = hostServices;
        this.server = server;
        this.port = port;
        this.passwordStorage = passwordStorage;
        this.orderStorage = orderStorage;

        this.setPadding(new Insets(20));

        toggleStatus = new Button("Start server");
        currentStatus = new Text("Stopped");
        HBox statusBox = new HBox(10, toggleStatus, currentStatus);
        toggleStatus.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    toggleStatus();
                }
            }
        );

        Text passwordText = new Text("Password: " + passwordStorage.getPassword());

        Text portText = new Text("Port: " + server.getPort());

        Text linkText = new Text("Client link:");
        Hyperlink link = new Hyperlink("http://localhost:" + server.getPort());
        HBox linkBox = new HBox(5, linkText, link);
        link.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    openClientWebsite();
                }
            }
        );

        Text restaurantLinkText = new Text("Restaurant link:");
        Hyperlink restaurantLink = new Hyperlink("http://localhost:" + port + "/internal");
        HBox restaurantLinkBox = new HBox(5, restaurantLinkText, restaurantLink);
        restaurantLink.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    openRestaurantWebsite();
                }
            }
        );

        Text errorText = new Text();

        Button save = new Button("Save orders");
        save.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    try {
                        errorText.setText("");
                        FileChooser fileChooser = new FileChooser();
                        ExtensionFilter fileExtensions = new ExtensionFilter("TEXT files (*.json)", "*.json");
                        fileChooser.getExtensionFilters().add(fileExtensions);
                        File file = fileChooser.showSaveDialog(stage);

                        List<Order> orders = orderStorage.orders;

                        List<Object> outputArray = new ArrayList<Object>();

                        for(int i = 0; i < orders.size(); i++) {
                            Order order = orders.get(i);

                            List<JSONObject> items = new ArrayList<JSONObject>();

                            for(int k = 0; k < order.items.size(); k++) {
                                Item currentItem = order.items.get(k);

                                HashMap<String, Object> item = new HashMap<String, Object>();

                                item.put("id", currentItem.id);
                                item.put("quantity", currentItem.quantity);

                                List<String> options = new ArrayList<String>();

                                for(int j = 0; j < currentItem.options.length; j++) {
                                    options.add(currentItem.options[j]);
                                }

                                item.put("options", options);

                                items.add(new JSONObject(item));
                            }

                            HashMap<String, Object> outputOrder = new HashMap<String, Object>();
                            outputOrder.put("id", order.id);
                            outputOrder.put("status", order.status);
                            outputOrder.put("items", items);

                            outputArray.add(outputOrder);
                        }

                        HashMap<String, Object> outputObject = new HashMap<String, Object>();
                        outputObject.put("orders", outputArray);

                        JSONObject jsonOutput = new JSONObject(outputObject);

                        file.createNewFile();

                        FileWriter fileWriter = new FileWriter(file);
                        jsonOutput.writeJSONString(fileWriter);
                        fileWriter.close();
                    }
                    catch(Exception e) {
                        errorText.setText("File error! Choose another file.");
                    }
                }
            }
        );

        VBox dashboardBox = new VBox(10, statusBox, passwordText, portText, linkBox, restaurantLinkBox, save, errorText);
        this.add(dashboardBox, 0, 0);

        toggleStatus();
    }

    public void toggleStatus() {
        try {
            if(currentStatus.getText().equals("Stopped")) {
                currentStatus.setText("Running");
                toggleStatus.setText("Stop server");
                server.listen();
            }
            else {
                server.stop();
                server.createServer(port, orderStorage, passwordStorage);
                currentStatus.setText("Stopped");
                toggleStatus.setText("Start server");
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public void openClientWebsite() {
        hostServices.showDocument("http://localhost:" + port);
    }

    public void openRestaurantWebsite() {
        hostServices.showDocument("http://localhost:" + port + "/internal");
    }
}
