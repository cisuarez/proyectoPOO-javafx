/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danlevil.proyctpoofxmlgluon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author DANIEL
 */
public class MenuPrincipalController implements Initializable {
    
    @FXML
    public static VBox contenedorPortadas;
    @FXML
    private static ImageView portada1;
    @FXML
    private static ImageView portada2;
    @FXML
    private Button urbanizacionMenu;
    
    @FXML
    private Button permisosMenu;
    
    @FXML
    private Button revisionMenu;
    
    @FXML
    private Button reportesMenu;
    
    @FXML
    private Button finalizarSistema;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    
    @FXML
    public void irUrbanizacionMenu() throws IOException{
        System.out.println("Yendo al menu de urbanizacion");
        App.setRoot("UrbanizacionMenu");
    }
    
    @FXML
    public void irPermisos() throws IOException{
        System.out.println("yendo a permisos");
        App.setRoot("PermisosMenu");
    }
    
    
    @FXML
    public void irRevision() throws IOException{
        System.out.println("Yendo a Revision");
        App.setRoot("RevisionEntradaMenu");
    }
    
    @FXML
    public void irReportesMenu() throws IOException{
        System.out.println("Yendo a Revision");
        App.setRoot("ReportesMenu");
    }
    
    @FXML
    public void finalizarSistemas(){
        System.out.println("Saliendo de la app");
        Platform.exit();
        
    }

    
    
    
}
