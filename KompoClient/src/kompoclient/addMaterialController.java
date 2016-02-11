/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kompoclient;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
