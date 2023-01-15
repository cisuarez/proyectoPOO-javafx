/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danlevil.proyctpoofxmlgluon;

import Modelo.Residente;
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
public class ConsultarPermisoController {
    @FXML private TextField numeroManzana;
    @FXML private TextField numeroVilla;
    @FXML private TextArea infoPermisoConsultado;
    @FXML private Button consultarPermiso;
    @FXML private Button salirConsultarPermiso;
    
    private ArrayList <String> errores=new ArrayList<>();
    
    @FXML
    private void consultar(){
        verificarCamposLlenos();
        if(errores.size()>0){
            Alert espaciosVacios= new Alert(AlertType.WARNING);
            espaciosVacios.setTitle("ERROR. Espacios sin completar");
            espaciosVacios.setContentText("TIENE QUE LLENAR TODOS LOS CAMPOS");
            espaciosVacios.initOwner(App.ventanaPrincipal);
            espaciosVacios.show();
            return;
        }
        try{
        int mz= Integer.parseInt(numeroManzana.getText());
        int vll= Integer.parseInt(numeroVilla.getText());
        ArrayList <String> permisosResidencia=Residente.ubicarMzVilla(mz, vll);
        if(permisosResidencia!=null){
            if(permisosResidencia.size()!=0){
                infoPermisoConsultado.setText(permisosResidencia.toString());
                numeroManzana.clear();
                numeroVilla.clear();
            }else if(permisosResidencia.isEmpty()){
            numeroManzana.clear();
            numeroVilla.clear();
            Alert arrayVacio= new Alert(AlertType.INFORMATION);
            arrayVacio.setTitle("ERROR");
            arrayVacio.setContentText("No hay permisos aún para Mostrar");
            arrayVacio.initOwner(App.ventanaPrincipal);
            arrayVacio.show();
            }
        }else{
            infoPermisoConsultado.setText("INFORMACION PERMISO CONSULTADO.");
            numeroManzana.clear();
            numeroVilla.clear();
            Alert numerosIncorrectos= new Alert(AlertType.ERROR);
            numerosIncorrectos.setTitle("ERROR");
            numerosIncorrectos.setContentText("Numero de Villa o Numero de manzana Incorrectos.Verificar");
            numerosIncorrectos.initOwner(App.ventanaPrincipal);
            numerosIncorrectos.show();
        }
        }catch(NumberFormatException nbe){
            numeroManzana.clear();
            numeroVilla.clear();
            Alert numerosIncorrectos= new Alert(AlertType.ERROR);
            numerosIncorrectos.setTitle("ERROR");
            numerosIncorrectos.setContentText("Ingresar SOLO valores númericos");
            numerosIncorrectos.initOwner(App.ventanaPrincipal);
            numerosIncorrectos.show();
        }
    }
    @FXML
    private void regresarPermisosMenu() throws IOException{
 
        App.setRoot("PermisosMenu");
    }
    private void verificarCamposLlenos(){
        errores.clear();
        if(numeroManzana.getText().isEmpty()){
            errores.add("Cedula del residente no ingresada");
        }
        if(numeroVilla.getText().isEmpty()){
            errores.add("Cedula del Visitante no ingresada");
        }
    }
}
