/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import buisnesslogic.Model;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author woors
 */
@Named("leiheview")
@RequestScoped
public class LeiheView {
    @Inject
    private Model model;
    
    public List<String> getLeihen(){
        return this.model.getLeihen();
    }
}
