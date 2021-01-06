package webserver.GUI;

import config.Config;
import config.Persist;
import exceptions.config_exceptions.ConfigurationException;
import exceptions.config_exceptions.LoadConfigurationFailureException;
import exceptions.webserver_exceptions.WebServerStateTransitionException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import webserver.Main;
import webserver.WebServer;
import webserver.WebServerState;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGUI implements Initializable {

    @FXML
    public Pane startControl, stopControl;
    public CheckBox checkMaintenance;
    public Label info_address, info_status, info_port;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stopControl.setVisible(true);
        startControl.setVisible(false);
    }

    public void onStopButtonClick(javafx.event.ActionEvent actionEvent) throws WebServerStateTransitionException {
        stopControl.setVisible(true);
        startControl.setVisible(false);
        info_address.setText("unavailable");
        info_status.setText("unavailable");
        info_port.setText("unavailable");

        WebServerState.setStopped();
    }

    public void onStartButtonClick(javafx.event.ActionEvent actionEvent) throws WebServerStateTransitionException, IOException, ConfigurationException {
        stopControl.setVisible(false);
        startControl.setVisible(true);
        checkMaintenance.setSelected(false);

        WebServerState.setRunning();

        Persist persist = Main.getPersist();
        info_address.setText("http://localhost:"+persist.getPortNumber());
        info_status.setText(WebServerState.getCurrentState());
        info_port.setText(String.valueOf(persist.getPortNumber()));
        new Thread(() -> {
            try {
                WebServer.performOnMode(persist);
            } catch (ConfigurationException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void onMaintenanceClick() throws WebServerStateTransitionException, ConfigurationException, IOException {
        stopControl.setVisible(false);
        startControl.setVisible(true);

        if(checkMaintenance.isSelected())
            WebServerState.setMaintenance();
        else
            WebServerState.setRunning();

        Persist persist = Main.getPersist();
        info_address.setText("http://localhost:"+persist.getPortNumber());
        info_status.setText(WebServerState.getCurrentState());
        info_port.setText(String.valueOf(persist.getPortNumber()));
    }
}
