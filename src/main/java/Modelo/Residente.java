package Modelo;

import java.io.Serializable;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;


public class Residente extends Persona implements Serializable {   
    private int mz;
    private int villa;
    private final int habitantes=1;
    private String urbanizacion;
    private Estado estado; 
    private ArrayList <Permiso> permisosPersonales= new ArrayList();
    
    private transient ArrayList <Persona> personas= Persona.getListaPersonas();
    private transient static ArrayList <Residente> residentes= new ArrayList();
    
    private transient static Permiso p= new Permiso();
    
    //constructor
    public Residente(){ 
    }
    //constructor 2
    public Residente(String nombre, String email, String cedula, String telefono,
        int mz, int villa, String urbanizacion, Estado estado) {
        super(nombre, email, cedula, telefono);
        this.mz = mz;
        this.villa = villa;
        this.urbanizacion = urbanizacion.toUpperCase();
        this.estado = estado;
    }
    
    //setters y getters
    public int getMz() {
        return mz;
    }

    public void setMz(int mz) {
        this.mz = mz;
    }

    public int getVilla() {
        return villa;
    }

    public void setVilla(int villa) {
        this.villa = villa;
    }

    public String getUrbanizacion() {
        return urbanizacion;
    }

    private void setUrbanizacion(String urbanizacion) {
        this.urbanizacion = urbanizacion;
    }
    
    private void setEstado(Estado estado){
        this.estado= estado;
    }
    public static ArrayList getListaResidentes(){
        return residentes;
    }

    public ArrayList<Permiso> getPermisos() {
        for(Permiso per:Permiso.permisos){
            if(per.getCreador().getCedula().equals(this.getCedula())){
                permisosPersonales.add(per);
            }
        }
        return permisosPersonales;
    }

    public void setPermisos(ArrayList<Permiso> permisos) {
        this.permisosPersonales = permisos;
    }
    
    
    //carga en memoria la informacion de un residente. 
    public void inicializar(){
        Residente r0=new Residente("Daniel Villamar",
        "Daniel@java","095753","098722",
        1,1 ,"Metropolis", Estado.ACTIVO );
        Residente r1=new Residente("Residente Tortuga","@espolJava","091213",
        "099999",2,2,"Villa PasarPOO",Estado.ACTIVO);
        
        personas.add(r0);
        residentes.add(r0);
        personas.add(r1);
        residentes.add(r1);
    }

    public Permiso eliminarPermiso(int codigo){
        for(Permiso p: Permiso.permisos){
            if (p.getEstado().equals(Estado.ACTIVO) && (p.getCodigoUnico()==codigo)){
                p.eliminarPermiso();
                return p;
            }
        }
        return null;

    }
    /*Ubica al residente que viva en la mz y villa y muestra sus 
    permisos*/
    public  ArrayList ubicarMzVilla(int mz,int villa){

        encontrarPermisosMzVilla( mz, villa);
        
        for (Residente r: residentes){
            if(r.mz==mz && r.villa==villa){
                
                return r.getPermisos();

            }

        }
        return null;
        
    }
    private  void encontrarPermisosMzVilla(int mz,int villa){
          for(Permiso per:Permiso.permisos){
            if(per.getCreador().getMz()==mz &&per.getCreador().getVilla()==villa){
                permisosPersonales.add(per);
             
            }
        }
    }

    @Override
    public String toString() {
        return "Residente{ " + super.toString()+ " mz= " + mz + ", villa= " 
        + villa + ", habitantes= " + habitantes + 
        ", urbanizacion= " + urbanizacion + ", estado= " + estado + '}';
    }
    public void añadirPermisos(Permiso permisoNuevo){
        Permiso.permisos.add(permisoNuevo);
        permisosPersonales.add(permisoNuevo);
    }
    
    
    
}
