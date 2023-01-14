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
    
    private Urbanizacion u1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        u1= Sistema.inicializarSistema();
        u1.inicializar();
        infoUrbanizacion.setText(u1.toString());
    }
    
    @FXML
    private void actualizarInformacion(){
        String nombreU= nombreUrbanizacion.getText();
        String etapa= etapaU.getText();
        String email=emailU.getText();
        String cedula=cedulaAdmin.getText();
        
        System.out.println("EL POR DEFECTO: "+nombreUrbanizacion.getText());
        if(nombreU.length()==0 ){
            u1.setNombre(u1.getNombre());
            
        }else{
            u1.setNombre(nombreU);
            nombreUrbanizacion.clear();
        }
        if(etapa.length()==0){
           u1.setEtapa(u1.getEtapa());
        }else{
            u1.setEtapa(etapa);
            etapaU.clear();
        }
        if(cedula.length()==0){
            u1.setAdministrador(null);
        }else{
            u1.cambiarAdminUrbanizacion(cedula);
            cedulaAdmin.clear();
        }
        if(email.length()==0){
            u1.setEmail(u1.getEmail());
        }else{
            u1.setEmail(email);
            emailU.clear();
        }
        infoUrbanizacion.setText(u1.toString());
    }
    @FXML
    private void regresarPrincipal() throws IOException{
        App.setRoot("MenuPrincipalUrbanizacion");
    }

    
    
}
