/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danlevil.proyctpoofxmlgluon;

import Modelo.Estado;
import java.io.IOException;
import Modelo.Permiso;
import Modelo.Residente;
import Modelo.Urbanizacion;
import Modelo.Visitante;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author DANIEL
 */
public class CrearPermisoController implements Initializable{
    @FXML
    private TextField cedulaResidente;
    @FXML private Label fechaActual;
    @FXML
    private DatePicker fechaIngreso;
    @FXML
    private TextField horaEntrada ;
    
    @FXML
    private TextField cedulaVisitante;
    @FXML
    private TextField duracionVisita;
    
    @FXML
    private Button crearPermiso;
    @FXML
    private Button salirCrearPermiso;
    @FXML
    private Label infoPermisoCreado;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LocalDate lD= LocalDate.now();
        fechaActual.setText(lD.toString());
    }
    
    @FXML
    private void crear(){
        Residente res=new Residente();
        Visitante vis=new Visitante();
        
        LocalDateTime fechaCreacion=LocalDateTime.now();
        if(!cedulaResidente.getText().isEmpty() ){
            if(App.urb.encontrarPersona(cedulaResidente.getText())instanceof Residente) {
                res=(Residente)App.urb.encontrarPersona(cedulaResidente.getText());
            }
            else{res=null;}
        }
        if(!cedulaVisitante.getText().isEmpty() ){
            if(App.urb.encontrarPersona(cedulaVisitante.getText())instanceof Visitante) {
                vis=(Visitante)App.urb.encontrarPersona(cedulaVisitante.getText());
            }else{vis=null;}
        }
        Permiso permisoNuevo=new Permiso(Estado.ACTIVO,
                fechaCreacion,
                fechaIngreso.getValue(),
                Permiso.definirHora(horaEntrada.getText()),
                res,
                vis,
                Permiso.definirHora(duracionVisita.getText())     
        
        );
        System.out.println("se creo el permiso");
        infoPermisoCreado.setText(permisoNuevo.toString());
        res.getPermisos().add(permisoNuevo);
        horaEntrada.clear();
        cedulaResidente.clear();
        cedulaVisitante.clear();
        duracionVisita.clear();
        System.out.println(res.getPermisos());
        
        
        
        
        /*LocalDate mes=mesActual.getValue();
        System.out.println(mes.getDayOfMonth());*/
    }
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    private void regresarPermisosMenu() throws IOException{
        crearPermiso.disabledProperty();
        App.setRoot("PermisosMenu");
    }
    
   
            
}
