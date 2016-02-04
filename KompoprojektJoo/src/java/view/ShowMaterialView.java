/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import buisnesslogic.Model;
import entity.Material;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author woors
 */
@Named("showmaterialview")
@RequestScoped
public class ShowMaterialView {
    @Inject
    private Model model;
    
    private String leihename;
    private String startdatum;
    private String enddatum;
    private int anzahl;
    
    public List<String> getMaterialien(){
        List<String> erg =  model.restMaterialien();
        //System.out.println(erg.size());
        return erg;
    }

    public void leihen(String item){
        String itemName = item.split(":")[0];
        Material m = model.getMaterial(itemName);
        DateFormat df = new SimpleDateFormat("yyyy MM dd");
        try {
            Date start = df.parse(startdatum);
            Date end = df.parse(enddatum);
            model.addLeihe(leihename, m, anzahl, start, end);
        } catch (ParseException ex) {
            Logger.getLogger(ShowMaterialView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @return the leihename
     */
    public String getLeihename() {
        return leihename;
    }

    /**
     * @param leihename the leihename to set
     */
    public void setLeihename(String leihename) {
        this.leihename = leihename;
    }

    /**
     * @return the startdatum
     */
    public String getStartdatum() {
        return startdatum;
    }

    /**
     * @param startdatum the startdatum to set
     */
    public void setStartdatum(String startdatum) {
        this.startdatum = startdatum;
    }

    /**
     * @return the enddatum
     */
    public String getEnddatum() {
        return enddatum;
    }

    /**
     * @param enddatum the enddatum to set
     */
    public void setEnddatum(String enddatum) {
        this.enddatum = enddatum;
    }

    /**
     * @return the anzahl
     */
    public int getAnzahl() {
        return anzahl;
    }

    /**
     * @param anzahl the anzahl to set
     */
    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }
}
