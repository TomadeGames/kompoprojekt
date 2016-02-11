/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kompoclient;

import entity.Material;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import persistence.Persistence;

/**
 *
 * @author woors
 */
public class addMaterialController implements Initializable {
    
    
    @FXML
    private TextField materialName;
    
    @FXML
    private TextField anzahl;
    
    @FXML
    private void handleBackAction(ActionEvent event){
        Stage stage = (Stage) materialName.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event){
        try{
            int intAnzahl = Integer.parseInt(anzahl.getText());
            Persistence.insertMaterial(materialName.getText(), intAnzahl);
            Stage stage = (Stage) materialName.getScene().getWindow();
            stage.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
