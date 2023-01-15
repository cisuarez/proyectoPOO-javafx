/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danlevil.proyctpoofxmlgluon;

import Excepciones.PermisoNoEncontrado;
import Excepciones.PermisosVacios;
import Modelo.Permiso;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author DANIEL
 */
public class ReportesController {
    @FXML private TextArea infoReportes;
    @FXML private TextField cedulaReportes;
    @FXML private Button generarReporte;
    @FXML private Button salirReportes;
    private ArrayList <String> errores=new ArrayList<>();
    @FXML 
    private void generar(){
        verificarCamposLlenos();
        if(errores.size()>0){
            Alert espaciosVacios= new Alert(Alert.AlertType.WARNING);
            cedulaReportes.clear();
            espaciosVacios.setTitle("ADVERTENCIA. Espacios sin completar");
            espaciosVacios.setContentText("CÉDULA RESIDENTE SIN COMPLETAR ");
            espaciosVacios.initOwner(App.ventanaPrincipal);
            espaciosVacios.show();
            return;
        }
        try{
            ArrayList <Permiso> reportes=App.urb.reportes(cedulaReportes.getText());
            if(reportes==null){
                throw new PermisoNoEncontrado();
            }
            if(reportes.isEmpty()){
                throw new PermisosVacios();
            }
            infoReportes.setText(reportes.toString());
        
        }catch(NumberFormatException nfe){
            Alert formatoError= new Alert(Alert.AlertType.ERROR);
            cedulaReportes.clear();
            infoReportes.setText("INFORMACION DEL REPORTE");
            formatoError.setTitle("ERROR");
            formatoError.setContentText("Ingresar SOLO valores númericos.");
            formatoError.initOwner(App.ventanaPrincipal);
            formatoError.show();
        }catch(PermisoNoEncontrado p){
            Alert codigoInvalido= new Alert(Alert.AlertType.ERROR);
            cedulaReportes.clear();
            infoReportes.setText("INFORMACION DEL REPORTE");
            codigoInvalido.setTitle("ERROR");
            codigoInvalido.setContentText("No hay Reportes que generar.\nEsa cedula no está dentro el listado de Residentes");
            codigoInvalido.initOwner(App.ventanaPrincipal);
            codigoInvalido.show();
        }catch(PermisosVacios pv){
            Alert codigoInvalido= new Alert(Alert.AlertType.WARNING);
            cedulaReportes.clear();
            infoReportes.setText("INFORMACION DEL REPORTE");
            codigoInvalido.setTitle("ALERTA");
            codigoInvalido.setContentText("Aún no hay permisos asociados al residente.");
            codigoInvalido.initOwner(App.ventanaPrincipal);
            codigoInvalido.show();
        }
    }
    
    
    
    
    
    
    private void verificarCamposLlenos(){
        errores.clear();
        if(cedulaReportes.getText().isEmpty()){
            errores.add("Cedula faltante");
        }

    }
    
    
    @FXML
    private void regresarMenuPrincipal() throws IOException{
        App.setRoot("MenuPrincipalUrbanizacion");
    }
    
}
