/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danlevil.proyctpoofxmlgluon;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author DANIEL
 */
public class MenuPermisosController implements Initializable {
    
    @FXML
    private Button crearMenu;
    @FXML
    private Button consultarMenu;
    @FXML
    private Button eliminarMenu;
    @FXML
    private Button salirPermisos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }
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
