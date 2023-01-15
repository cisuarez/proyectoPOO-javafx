package Modelo;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;


public class Residente extends Persona {   
    private int mz;
    private int villa;
    private final int habitantes=1;
    private String urbanizacion;
    private Estado estado; 
    private ArrayList <Permiso> permisos= new ArrayList();
    
    private ArrayList <Persona> personas= Persona.getListaPersonas();
    private static ArrayList <Residente> residentes= new ArrayList();
    private Scanner entra= new Scanner (System.in);
    private static Permiso p= new Permiso();
    
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
        return permisos;
    }

    public void setPermisos(ArrayList<Permiso> permisos) {
        this.permisos = permisos;
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
    /*Metodos private para definir los datos de un residente, son usa-
    -dos en definirDatos() */
    private int definirManzana(){
        System.out.println("Ingrese el numero de la manzana: ");
        int mz= entra.nextInt();
        return mz;
    }
    private int definirVilla(){
        System.out.println("Ingrese el numero de la villa: ");
        int villa= entra.nextInt();
        return villa;
    }
    private String  definirUrbanizacion(){
        System.out.println("Ingrese el nombre de la urbanizacion: ");
        entra.nextLine();
        String urbanizacion= entra.nextLine().toUpperCase();
        return urbanizacion;
    }
    public void mostrarReportes(){
        for(Permiso per: permisos){
            System.out.println(per);
        }
    }
    //cambia el estado a Inactivo. eliminacion del residente.
    @Override
    public void eliminarme(){
        this.setEstado(Estado.INACTIVO);
    }
    
    /*El residente es el que crea el permiso, por eso tiene su 
    arrayList de permisos, puede crear varios */
    /*public void crearPermiso(){
        
        Permiso permiso= p.permisoNuevo(this);
        if(permiso!=null){
            permisos.add(permiso);
            System.out.println("permiso creado con exito");
        }else{
            System.out.println("Oh no!algo ha salido mal, permiso no creado");
        }
        
    }*/
    /*cambia a inactivo el estado del permiso una vez comprueba el 
    codigo*/
    public Permiso eliminarPermiso(int codigo){
        for(Permiso p: permisos){
            if (p.getEstado().equals(Estado.ACTIVO) && (p.getCodigoUnico()==codigo)){
                p.eliminarPermiso();
                return p;
            }
        }
        return null;

    }
    /*Ubica al residente que viva en la mz y villa y muestra sus 
    permisos*/
    public static ArrayList ubicarMzVilla(int mz,int villa){
        boolean bandera= false;
        int indice=0;
        

        for (Residente r: residentes){
            if(r.mz==mz && r.villa==villa){
                bandera=true;
                return r.getPermisos();

            }

        }
        return null;
        
    }
//recorre la lista de permisos del residente y los muestra en pantalla
    public void mostrarPermisoResidente(){
        System.out.println("Permisos de  "+this.getNombre()+"{");
        for(Permiso per: permisos){
            System.out.println(per);
        }
        System.out.println("}");
    }
//metodo utilizado en "ubicarMzVilla"
    private void mostrarPermisosMZVilla(){
        System.out.println("Mostrando los permisos por mz y villa...");
        if(permisos.size()!=0){
            for(Permiso per:permisos){
            System.out.println(per);
            }
        }else{
            System.out.println("No hay permisos para mostrar...");
        }
        
    }
    //Polimorfismo de definirDatos 
    @Override
    public Persona definirDatos(){
        
        Persona p= super.definirDatos();
        int mz= definirManzana();
        int villa= definirVilla();
        String urbanizacion= definirUrbanizacion();
        Residente r=new Residente(p.getNombre(),p.getEmail(),
        p.getCedula(),p.getTelefono(),
                mz,villa,urbanizacion,Estado.ACTIVO);
        residentes.add(r);
        return r ;
    }
    
    //Polimorfismo de modificarDatos
    @Override
    public void modificarDatos(){
        super.modificarDatos();
        setMz(definirManzana());
        setVilla(definirVilla());
        setUrbanizacion(definirUrbanizacion());
    }
    @Override
    public String toString() {
        return "Residente{ " + super.toString()+ " mz= " + mz + ", villa= " 
        + villa + ", habitantes= " + habitantes + 
        ", urbanizacion= " + urbanizacion + ", estado= " + estado + '}';
    }
    
    
    
    
}
