/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danlevil.proyctpoofxmlgluon;


import Modelo.Permiso;
import Modelo.Residente;
import Excepciones.ResidenteNoEncontrado;
import Excepciones.PermisoNoEncontrado;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
            espaciosVacios.setTitle("ADVERTENCIA. Espacios sin completar");
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
        eliminarDelCSV(codigoUnico,cedulaEliminar.getText());
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
        }catch(ClassCastException cnc){
            cedulaEliminar.clear();
            codigoEliminar.clear();
            Alert errorFormato= new Alert(AlertType.ERROR);
            errorFormato.setTitle("ERROR");
            errorFormato.setContentText("Cédula Registrada pero no como Residente\nIngrese otra vez.");
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
    private void eliminarDelCSV(int codigo,String cedula){
        try(BufferedReader bf=new BufferedReader(new FileReader(App.filespath+"Registro de Permisos.csv"))){
            String linea;
            while((linea=bf.readLine())!=null){
                if(linea.contains(cedulaEliminar.getText())&&linea.contains(codigoEliminar.getText())){
                    linea.replace("ACTIVO", "INACTIVO");
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void regresarPermisosMenu() throws IOException{
        
        App.setRoot("PermisosMenu");
    }
}



