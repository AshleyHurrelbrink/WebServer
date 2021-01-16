package webserver.GUI;

import config.Config;
import config.Persist;
import exceptions.config_exceptions.*;
import exceptions.webserver_exceptions.WebServerStateTransitionException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import webserver.WebServer;
import webserver.WebServerState;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class ControllerGUI implements Initializable {

    @FXML
    public Pane startControl, stopControl;
    public CheckBox checkMaintenance;
    public Label info_address, info_status, info_port, portConfig, rootDirConfig, maintenanceDirConfig;
    public TextField setPort, setRootDirectory, setMaintenanceDirectory;
    public Button searchRootDirectory, searchMaintenanceDirectory;

    private Persist persist;
    ServerSocket serverSocket;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stopControl.setVisible(true);
        startControl.setVisible(false);

        try {
            persist = getPersist();
            setRootDirectory.setText(persist.getRootDirectory());
            setMaintenanceDirectory.setText(persist.getMaintenanceDirectory());
            setPort.setText(String.valueOf(persist.getPortNumber()));
        } catch (ConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    public void onStopButtonClick(javafx.event.ActionEvent actionEvent) throws WebServerStateTransitionException, IOException {

        if(serverSocket!=null){
            serverSocket.close();
        }

        // LAYOUT ---
        stopControl.setVisible(true);
        startControl.setVisible(false);
        resetSettings();

        // CONFIGURATIONS ---
        setPort.setDisable(false);
        setMaintenanceDirectory.setDisable(false);
        setRootDirectory.setDisable(false);
        searchRootDirectory.setDisable(false);
        searchMaintenanceDirectory.setDisable(false);

        // WEBSERVER ---
        WebServerState.setStopped();

        //STATUS
        info_address.setText("not running");
        info_status.setText("not running");
        info_port.setText("not running");
    }

    public void onStartButtonClick(javafx.event.ActionEvent actionEvent) throws WebServerStateTransitionException, IOException, ConfigurationException {

        resetSettings();

        // CHECKS CONFIGURATIONS (will not enter running state if checks do not pass) -----

        persist = getPersist();
        if(persist==null){
            return ;
        }

        //saved in boolean in order for all wrong configurations to be checked and colored in gui
        boolean portResult = setPortNumber();
        boolean rootResult = setRootDir();
        boolean maintenanceResult = setMaintenanceDir();
        if(!portResult || !rootResult || !maintenanceResult){
            return;
        }

        serverSocket = WebServer.getServerSocket(persist);
        if(serverSocket==null){
            portConfig.setTextFill(Color.web("#a45b5b"));
            return;
        }

        // LAYOUT ---
        resetSettings();
        stopControl.setVisible(false);
        startControl.setVisible(true);
        checkMaintenance.setSelected(false);
        setPort.setDisable(true);
        setRootDirectory.setDisable(true);
        setMaintenanceDirectory.setDisable(false);
        searchRootDirectory.setDisable(true);
        searchMaintenanceDirectory.setDisable(false);

        // WEBSERVER ----
        WebServerState.setRunning();

        new Thread(() -> {
            try {
                WebServer.performOnMode(persist, serverSocket);
            } catch (ConfigurationException e) {
                e.printStackTrace();
            }
        }).start();

        // STATUS ---
        setStatus();
    }


    public void onCheckBoxMaintenanceClick() throws WebServerStateTransitionException, ConfigurationException, IOException {

        // verify if checked or unchecked

        if(checkMaintenance.isSelected()) {
            //CHECK CONFIGURATIONS (will not change state if checks do not pass)
            if(!setMaintenanceDir()){
                checkMaintenance.setSelected(false);
                return;
            }
            onStartMaintenance();
        }
        else {
            //CHECK CONFIGURATIONS (will not change state if checks do not pass)
            if(!setRootDir()){
                checkMaintenance.setSelected(true);
                return;
            }
            onContinueRunFromMaintenance();
        }
    }

    public void onContinueRunFromMaintenance() throws ConfigurationException, WebServerStateTransitionException {
        WebServerState.setRunning();

        // STATUS
        setStatus();

        // LAYOUT ---
        resetSettings();
        stopControl.setVisible(false);
        startControl.setVisible(true);
        checkMaintenance.setSelected(false);
        setPort.setDisable(true);
        setRootDirectory.setDisable(true);
        setMaintenanceDirectory.setDisable(false);
        searchRootDirectory.setDisable(true);
        searchMaintenanceDirectory.setDisable(false);
    }

    public void onStartMaintenance() throws WebServerStateTransitionException, ConfigurationException {
        WebServerState.setMaintenance();

        // STATUS
        setStatus();

        // LAYOUT ---
        resetSettings();
        stopControl.setVisible(false);
        startControl.setVisible(true);
        checkMaintenance.setSelected(true);
        setPort.setDisable(true);
        setRootDirectory.setDisable(false);
        setMaintenanceDirectory.setDisable(true);
        searchRootDirectory.setDisable(false);
        searchMaintenanceDirectory.setDisable(true);
    }

    // -------------------------------------------------------------------------------------------

    public void setStatus() throws ConfigurationException {
        // STATUS
        info_address.setText("http://localhost:"+persist.getPortNumber());
        info_status.setText(WebServerState.getStatus());
        info_port.setText(String.valueOf(persist.getPortNumber()));
    }

    public Persist getPersist() throws IOException, ConfigurationException {
        String configFilePath = "C:/Users/ahurr/Desktop/College/Year 4 (2020-2021)/sem1 (year 4)/SVV/WebServer/WebServer/WebserverTestingDirectories/config.properties";
        Config config = new Config(configFilePath);
        return new Persist(config);
    }

    public void resetSettings(){
        portConfig.setTextFill(Color.web("#000000"));
        rootDirConfig.setTextFill(Color.web("#000000"));
        maintenanceDirConfig.setTextFill(Color.web("#000000"));
    }

    // SET CONFIGURATIONS ---------------------------------------------------------------------------

    public boolean setPortNumber(){
        try{
            if(setPort.getText().isEmpty()){
                throw new ConfigurationException("");
            }
            persist.setPortNumber(Integer.valueOf(setPort.getText()));
            return true;
        } catch (ConfigurationException | IOException e){
            portConfig.setTextFill(Color.web("#a45b5b"));
            return false;
        }
    }

    public boolean setRootDir(){
        try{
            persist.setRootDirectory(setRootDirectory.getText());
            return true;
        } catch (ConfigurationException | IOException e){
            rootDirConfig.setTextFill(Color.web("#a45b5b"));
            return false;
        }
    }

    public boolean setMaintenanceDir(){
        try{
            persist.setMaintenanceDirectory(setMaintenanceDirectory.getText());
            return true;
        } catch (ConfigurationException | IOException e){
            maintenanceDirConfig.setTextFill(Color.web("#a45b5b"));
            return false;
        }
    }

    public void onSearchRootDirectory() {

    }

    public void onSearchMaintenanceDirectory() {

    }
}
