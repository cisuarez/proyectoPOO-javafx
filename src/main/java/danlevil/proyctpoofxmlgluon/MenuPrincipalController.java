/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danlevil.proyctpoofxmlgluon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author DANIEL
 */
public class MenuPrincipalController {
    
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
        App.setRoot("RevisionEntradaMenu");
    }
    
    @FXML
    public void irReportesMenu(){}
    
    @FXML
    public void finalizarSistemas(){
        System.out.println("Saliendo de la app");
        System.exit(0);
        
    }
    
    
    
}
