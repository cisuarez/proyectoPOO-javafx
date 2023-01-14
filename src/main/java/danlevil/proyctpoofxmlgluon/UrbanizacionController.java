/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danlevil.proyctpoofxmlgluon;

import Modelo.Sistema;
import Modelo.Urbanizacion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author DANIEL
 */
public class UrbanizacionController implements Initializable{
    
    @FXML
    private TextField nombreUrbanizacion;
    @FXML
    private TextField cedulaAdmin;
    @FXML
    private TextField emailU;
    @FXML
    private TextField etapaU;
    @FXML
    private Label infoUrbanizacion;
    @FXML
    private Button actualizarUrbanizacion;
    @FXML
    private Button salirUrbanizacion;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        infoUrbanizacion.setText(App.urb.toString());
    }
    
    @FXML
    private void actualizarInformacion(){
        String nombreU= nombreUrbanizacion.getText();
        String etapa= etapaU.getText();
        String email=emailU.getText();
        String cedula=cedulaAdmin.getText();
        
        
        if(nombreU.length()==0 ){
            App.urb.setNombre(App.urb.getNombre());
            
        }else{
            App.urb.setNombre(nombreU.toUpperCase());
            nombreUrbanizacion.clear();
        }
        if(etapa.length()==0){
           App.urb.setEtapa(App.urb.getEtapa());
        }else{
            App.urb.setEtapa(etapa);
            etapaU.clear();
        }
        if(cedula.length()==0){
            App.urb.setAdministrador(App.urb.getAdministrador());
        }else{
            App.urb.cambiarAdminUrbanizacion(cedula);
            cedulaAdmin.clear();
        }
        if(email.length()==0){
            App.urb.setEmail(App.urb.getEmail());
        }else{
            App.urb.setEmail(email);
            emailU.clear();
        }
        infoUrbanizacion.setText(App.urb.toString());
    }
    @FXML
    private void regresarPrincipal() throws IOException{
        App.setRoot("MenuPrincipalUrbanizacion");
    }

    
    
}
