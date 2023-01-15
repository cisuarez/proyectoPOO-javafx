/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danlevil.proyctpoofxmlgluon;

import Excepciones.PermisoNoEncontrado;
import Modelo.Permiso;
import Modelo.Persona;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author DANIEL
 */
public class RevisionEntradaController {
    @FXML private Button revisarPermiso;
    @FXML private Button salirRevision;
    @FXML private Label infoRevision;
    @FXML private TextField cedulaRevision;
    @FXML private TextField codigoRevision;
    @FXML private TextArea observaciones;
    private ArrayList <String> errores=new ArrayList<>();
    private Permiso verificado;
    @FXML
    private void revisarEntrada(){
        verificarCamposLlenos();
        if(errores.size()>0){
            Alert espaciosVacios= new Alert(AlertType.WARNING);
            observaciones.clear();
            espaciosVacios.setTitle("ADVERTENCIA. Espacios sin completar");
            espaciosVacios.setContentText("CÓDIGO DEL PERMISO Y CÉDULA VISITANTE. SIN COMPLETAR ");
            espaciosVacios.initOwner(App.ventanaPrincipal);
            espaciosVacios.show();
            return;
        }
        try{
            int codigo=Integer.parseInt(codigoRevision.getText());


            verificado =App.urb.entradaUrbanizacion( codigo,cedulaRevision.getText());
            System.out.println(verificado);
            if(verificado==null){
                throw new PermisoNoEncontrado();
            }
            if(observaciones.getText().length()!=0){
                verificado.setObservacion(observaciones.getText());
            }
            verificado.usado();
            infoRevision.setText(verificado.toString());

        }catch(NumberFormatException nfe){
            Alert codigoInvalido= new Alert(AlertType.ERROR);
            codigoRevision.clear();
            codigoInvalido.setTitle("ERROR");
            codigoInvalido.setContentText("Ingresar SOLO valores númericos.");
            codigoInvalido.initOwner(App.ventanaPrincipal);
            codigoInvalido.show();
        }catch(PermisoNoEncontrado pn){
            Alert codigoInvalido= new Alert(AlertType.ERROR);
            codigoRevision.clear();
            cedulaRevision.clear();
            codigoInvalido.setTitle("ERROR");
            codigoInvalido.setContentText("Permiso Inexistente.");
            codigoInvalido.initOwner(App.ventanaPrincipal);
            codigoInvalido.show();
        }
    }
    
    
      private void verificarCamposLlenos(){
        errores.clear();
        if(cedulaRevision.getText().isEmpty()){
            errores.add("Cedula faltante");
        }
        if(codigoRevision.getText().isEmpty()){
            errores.add("Codigo faltante");
        }
        if((observaciones.getText().length()!=0) &&(codigoRevision.getText().isEmpty())&& (cedulaRevision.getText().isEmpty())){
            errores.add("No se puede hacer observaciones a un permiso inexistente");
            observaciones.clear();
        }
    }
    
    
    
    
    
    
     @FXML
    private void regresarMenuPrincipal() throws IOException{
        App.setRoot("MenuPrincipalUrbanizacion");
    }
    
    
}
