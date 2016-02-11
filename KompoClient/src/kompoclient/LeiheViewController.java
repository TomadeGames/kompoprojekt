/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kompoclient;

import entity.Leihe;
import entity.Material;
import java.net.URL;
import java.util.Date;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author woors
 */
public class LeiheViewController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private ChoiceBox materialName0;
    @FXML
    private TextField anzahl0;
    @FXML
    private DatePicker von0;
    @FXML
    private DatePicker bis0;
    @FXML
    private Label statusLabel;
    @FXML
    private TableView<Leihe> leiheTable;
    @FXML
    private TableColumn<Leihe, String> idColumn;
    @FXML
    private TableColumn<Leihe, String> materialColumn;
    @FXML
    private TableColumn<Leihe, String> anzahlColumn;
    @FXML
    private TableColumn<Leihe, String> vonColumn;
    @FXML
    private TableColumn<Leihe, String> bisColumn;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Material> mats = Persistence.getMaterial();
        for(Material m: mats){
            materialName0.getItems().add(m.getName());            
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idProperty"));
        materialColumn.setCellValueFactory(new PropertyValueFactory<>("materialProperty"));
        anzahlColumn.setCellValueFactory(new PropertyValueFactory<>("anzahlProperty"));
        vonColumn.setCellValueFactory(new PropertyValueFactory<>("vonProperty"));
        bisColumn.setCellValueFactory(new PropertyValueFactory<>("bisProperty"));
        leiheTable.getItems().setAll(Persistence.getLeihen());
    }    
    
        
    @FXML
    private void handleLeiheAction(ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addMaterialView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Leihe");
            stage.setScene(new Scene(root1));  
            stage.show();
        }
        catch(Exception e){
            System.out.println("Fehler beim öffnen der addMaterialView: " + e);
        }
    }
    
    @FXML
    public void handleBestellenAction(ActionEvent event){
        String nameString = name.getText();
        List<Leihe> leihen = new ArrayList<>();
        Leihe l = new Leihe();
        l.setId(Persistence.getFreeLeiheId());
        Material m = Persistence.getMaterial((String)materialName0.getValue());
        l.setMaterialId(m.getId());
        l.setVon(Date.from(von0.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        l.setBis(Date.from(bis0.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        l.setAnzahl(Integer.parseInt(anzahl0.getText()));
        leihen.add(l);
        String status = Persistence.insertGesamtLeihe(leihen, nameString);
        this.statusLabel.setText(status);
        
    }
    
}
