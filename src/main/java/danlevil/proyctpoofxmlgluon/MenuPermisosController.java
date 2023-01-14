/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danlevil.proyctpoofxmlgluon;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author DANIEL
 */
public class MenuPermisosController {
    
    @FXML
    private Button crearMenu;
    @FXML
    private Button consultarMenu;
    @FXML
    private Button eliminarMenu;
    @FXML
    private Button salirPermisos;
    
    
    @FXML
    private void irCrearPermiso() throws IOException{
        App.setRoot("CrearPermiso");
    } 
    @FXML
    private void irConsultarPermiso() throws IOException{
        App.setRoot("ConsultarPermiso");
    }
    @FXML
    private void irEliminarPermiso() throws IOException{
        App.setRoot("EliminarPermiso");
    }
    @FXML
    private void regresarPrincipal() throws IOException{
        App.setRoot("MenuPrincipalUrbanizacion");
    
    }
}
