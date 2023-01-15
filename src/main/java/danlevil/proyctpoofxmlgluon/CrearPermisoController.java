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
import Excepciones.*;
import static Modelo.Permiso.definirHora;
import java.net.URL;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author DANIEL
 */
public class CrearPermisoController implements Initializable{
    @FXML private TextField cedulaResidente;
    @FXML private Label fechaActual;
    @FXML private DatePicker fechaIngreso;
    @FXML private TextField horaEntrada ;
    
    @FXML private TextField cedulaVisitante;
    @FXML private TextField duracionVisita;
    
    @FXML private Button crearPermiso;
    @FXML private Button salirCrearPermiso;
    @FXML private Label infoPermisoCreado;
    
    private ArrayList <String> errores;
    private Residente res=new Residente();
    private Visitante vis=new Visitante();
    private LocalDate fechaHoy;
    //ESCRIBIR EN EL ARCHIVO CVS LOS PERMISOS VA AQUI.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fechaHoy= LocalDate.now();
        fechaActual.setText(fechaHoy.toString());
        errores=new ArrayList<>();
        
        
        
    }
    
    @FXML
    private void crear(){
        verificarCamposLlenos();
        if(errores.size()>0){
            Alert espaciosVacios= new Alert(AlertType.WARNING);
            espaciosVacios.setTitle("ADVERTENCIA. Espacios sin completar");
            espaciosVacios.setContentText("TIENE QUE LLENAR TODOS LOS CAMPOS");
            espaciosVacios.initOwner(App.ventanaPrincipal);
            espaciosVacios.show();
            return;
        }

        
        LocalDateTime fechaCreacion=LocalDateTime.now();
        try{
            verificarResidente();
            verificarVisitante();
            verificarHoraIngreso();
            verificarDuracionVisita();
            verificarDiaDelPermiso();
        }catch(NoResidente nr){
            cedulaResidente.clear();
            Alert noResidente= new Alert(AlertType.ERROR);
            noResidente.setTitle("ERROR");
            noResidente.setContentText("Cédula Incorrecta.No registrado como habitante\nPorfavor Ingrese una cédula válida");
            noResidente.initOwner(App.ventanaPrincipal);
            noResidente.show();
            return;
        }catch(NoVisitante nv){
            cedulaVisitante.clear();
            Alert noVisitante= new Alert(AlertType.ERROR);
            noVisitante.setTitle("ERROR");
            noVisitante.setContentText("Cédula Incorrecta.No registrado como Visitante\nPorfavor Ingrese una cédula válida");
            noVisitante.initOwner(App.ventanaPrincipal);
            noVisitante.show();
            return;
        }catch(HoraEntradaInvalida hI){
            horaEntrada.clear();
            Alert horaEntadaInvalida= new Alert(AlertType.ERROR);
            horaEntadaInvalida.setTitle("ERROR");
            horaEntadaInvalida.setContentText("Porfavor Ingrese una hora de ingreso en el formato correcto: (hh:mm)");
            horaEntadaInvalida.initOwner(App.ventanaPrincipal);
            horaEntadaInvalida.show();
            return;
        }catch(DuracionVisitaInvalida Div){
            duracionVisita.clear();
            Alert duracionInvalida= new Alert(AlertType.ERROR);
            duracionInvalida.setTitle("ERROR");
            duracionInvalida.setContentText("Porfavor Ingrese una Duracion de visita en el formato correcto: (hh:mm)");
            duracionInvalida.initOwner(App.ventanaPrincipal);
            duracionInvalida.show();
            return;
        }catch(DiaNoValido dnv){
            Alert diaInvalido= new Alert(AlertType.ERROR);
            diaInvalido.setTitle("ERROR");
            diaInvalido.setContentText("El Permiso solo puede ser para el mismo día o un día máximo de anticipo\nSeleccione de nuevo Porfavor.");
            diaInvalido.initOwner(App.ventanaPrincipal);
            diaInvalido.show();
            return;
        }

        
        Permiso permisoNuevo=new Permiso(Estado.ACTIVO,
                fechaCreacion,
                fechaIngreso.getValue(),
                Permiso.definirHora(horaEntrada.getText()),
                res,
                vis,
                Permiso.definirHora(duracionVisita.getText())     
        
        );

        infoPermisoCreado.setText(permisoNuevo.toString());
        res.añadirPermisos(permisoNuevo);
        horaEntrada.clear();
        cedulaResidente.clear();
        cedulaVisitante.clear();
        duracionVisita.clear();
        System.out.println(res.getPermisos());
        
        
        
        
        /*LocalDate mes=mesActual.getValue();
        System.out.println(mes.getDayOfMonth());*/
    }
    
    private void verificarCamposLlenos(){
        errores.clear();
        if(cedulaResidente.getText().isEmpty()){
            errores.add("Cedula del residente no ingresada");
        }
        if(cedulaVisitante.getText().isEmpty()){
            errores.add("Cedula del Visitante no ingresada");
        }
        if(horaEntrada.getText().isEmpty()){
            errores.add("hora de entrada  no ingresada");
        }
        if(duracionVisita.getText().isEmpty()){
            errores.add("Duración de la visita no ingresada");
        }
        if(fechaIngreso.getValue()==null){
            errores.add("Fecha de ingreso no seleccionada");
        }

    }
    private void verificarResidente(){
        
        if(App.urb.encontrarPersona(cedulaResidente.getText())instanceof Residente) {
            res=(Residente)App.urb.encontrarPersona(cedulaResidente.getText());

        }
        else{
            res=null;
            throw  new NoResidente("La cedula no pertenece a ninguno de los residentes");
        }
    }
    private void verificarVisitante(){
        if(App.urb.encontrarPersona(cedulaVisitante.getText())instanceof Visitante) {
            vis=(Visitante)App.urb.encontrarPersona(cedulaVisitante.getText());
        }else{
            vis=null;
            throw new NoVisitante("Cedula No registrada");
        }
    }
    
    
    private void verificarHoraIngreso(){
        
        try{
            LocalTime hI= Permiso.definirHora(horaEntrada.getText());
        }catch(ArrayIndexOutOfBoundsException aBe){
            throw new HoraEntradaInvalida();
        }catch(NumberFormatException nE){
            throw new HoraEntradaInvalida();
        }catch(DateTimeException dtE){
            throw new HoraEntradaInvalida();
        }
    }
    
    private void verificarDuracionVisita(){
        
        try{
            LocalTime hI= Permiso.definirHora(duracionVisita.getText());
        }catch(ArrayIndexOutOfBoundsException aBe){
            throw new DuracionVisitaInvalida();
        }catch(NumberFormatException nE){
            throw new DuracionVisitaInvalida();
        }catch(DateTimeException dtE){
            throw new DuracionVisitaInvalida();
        }
    }
    
    private void verificarDiaDelPermiso(){
        int diaActual=fechaHoy.getDayOfMonth();
        int diaSeleccionado=fechaIngreso.getValue().getDayOfMonth();
        if((diaActual==diaSeleccionado)||(diaSeleccionado==diaActual+1)){
        }else{
            throw new DiaNoValido();
        }
    }
    
    
    @FXML
    private void regresarPermisosMenu() throws IOException{
        
        App.setRoot("PermisosMenu");
    }
    
   
            
}
