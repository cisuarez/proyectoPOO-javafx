/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danlevil.proyctpoofxmlgluon;

import Modelo.Permiso;
import Modelo.Residente;
import Excepciones.ResidenteNoEncontrado;
import Excepciones.PermisoNoEncontrado;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author DANIEL
 */
public class EliminarPermisoController {
    @FXML private Button borrarPermiso;
    @FXML private Button salirEliminarPermiso;
    @FXML private Label infoPermisoEliminado;
    @FXML private TextField cedulaEliminar;
    @FXML private TextField codigoEliminar;
    
    private ArrayList <String> errores=new ArrayList<>();
    
    
    //AQUI SE ESCRIBE EN EL CSV EL NUEVO ESTADO DEL PERMISO.SE BUSCA Y SOBREESCRIBE.
    
    @FXML
    private void eliminar(){
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
        int codigoUnico=Integer.parseInt(codigoEliminar.getText());
        
        Residente res=(Residente)App.urb.encontrarPersona(cedulaEliminar.getText());
        if(res==null){
            throw new ResidenteNoEncontrado();
        }
        Permiso eliminado=res.eliminarPermiso(codigoUnico);
        if(eliminado==null){
            throw new PermisoNoEncontrado();
        }
        infoPermisoEliminado.setText(eliminado.toString());
        cedulaEliminar.clear();
        codigoEliminar.clear();
            
        
        }catch(NumberFormatException nfe){
            cedulaEliminar.clear();
            codigoEliminar.clear();
            Alert errorFormato= new Alert(AlertType.ERROR);
            errorFormato.setTitle("ERROR");
            errorFormato.setContentText("Ingrese SOLO valores númericos");
            errorFormato.initOwner(App.ventanaPrincipal);
            errorFormato.show();
            return;
        }catch(ResidenteNoEncontrado rnE){
            cedulaEliminar.clear();
            
            Alert errorFormato= new Alert(AlertType.ERROR);
            errorFormato.setTitle("ERROR");
            errorFormato.setContentText("Cedula no registrada como Residente.\nIngrese otra vez.");
            errorFormato.initOwner(App.ventanaPrincipal);
            errorFormato.show();
            return;
        }catch(PermisoNoEncontrado p){
            
            codigoEliminar.clear();
            Alert errorFormato= new Alert(AlertType.ERROR);
            errorFormato.setTitle("ERROR");
            errorFormato.setContentText("Ningun permiso está asociado a ese codigo.\nIngrese otra vez.");
            errorFormato.initOwner(App.ventanaPrincipal);
            errorFormato.show();
            return;
        }
    }
    
    
    
    
    
    private void verificarCamposLlenos(){
        errores.clear();
        if(cedulaEliminar.getText().isEmpty()){
            errores.add("Cedula incorrecta");
        }
        if(codigoEliminar.getText().isEmpty()){
            errores.add("Codigo Invalido");
        }
    }
    @FXML
    private void regresarPermisosMenu() throws IOException{
        
        App.setRoot("PermisosMenu");
    }
}



/*excepciones:
Caused by: java.lang.NullPointerException: Cannot invoke "Modelo.Permiso.toString()" because "eliminado" is null
Caused by: java.lang.NumberFormatException: For input string: "sdsfdf"
Caused by: java.lang.NullPointerException: Cannot invoke "Modelo.Permiso.toString()" because "eliminado" is null

*/
