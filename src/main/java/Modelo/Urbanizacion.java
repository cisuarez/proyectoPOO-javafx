package Modelo;

import static Modelo.Permiso.permisos;
import java.io.Serializable;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class Urbanizacion implements Serializable {
    
    
    private static Residente creador= new Residente();
    private static Visitante creadorv = new Visitante();
    private static Colaborador creadorc= new Colaborador();
    public ArrayList <Persona> personas= Persona.getListaPersonas();

    private String nombre;

    private String etapa;

    private String email;

    private String constructora;
    private Colaborador administrador;
    
    
    public Urbanizacion(String nombre, String etapa, String email,
            String constructora, Colaborador administrador) {
        this.nombre = nombre.toUpperCase();
        this.etapa = etapa;
        this.email = email;
        this.constructora = constructora;
        this.administrador= administrador;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConstructora() {
        return constructora;
    }

    public void setConstructora(String constructora) {
        this.constructora = constructora;
    }

    public void setAdministrador(Colaborador administrador) {
        this.administrador = administrador;
    }

    public Colaborador getAdministrador() {
        return administrador;
    }
    
    
//inicializarSistema carga los datos del residente, visitante, colabs
    public void inicializar(){
        creador.inicializar();
        creadorv.inicializar();
        creadorc.inicializar(); 
    }
    
    
/*1.Urbanizacion. 
El metodo menuUrbanizacion contiene a los metodos
que permiten 1. mostrar la informacion o 2. modificarla y el metodo
modificar informacion, contiene a los metodos que ingresan los
cambios*/ 
    
    /*public void menuUrbanizacion(){
        mostrarInfoUrbanizacion();
        System.out.println( " 1. actualizar informacion 2. salir.");
        String opcion= entra.next();
        
        switch(opcion){
            case "1":
                System.out.println("Que desea modificar: "
                + "\n1. nombre urbanizacion 2. etapa numero, "
                        + "3. email adm . 4adm. 5.Constructora");
                int numero= entra.nextInt();
                
                modificarInfoUrbanizacion(numero);
                System.out.println("datos actualizados correctamente");
                break;
            case "2":
                System.out.println("Regresando al menú principal...");
                break;
            default:
                System.out.println("opcion invalida.");
                break;
        }
    }*/
    
//imprime por pantalla los datos de la urbanizacion.
    private void mostrarInfoUrbanizacion(){
        System.out.println(this);  
        
    }
//menu con opciones para modificar la informacion de la urbanizacion
    /*private void modificarInfoUrbanizacion(int opcion){
        entra.skip("\n");
        switch(opcion){
            case 1:
                cambiarNombreUrbanizacion();
                break;
            case 2:
                cambiarEtapaUrbanizacion();
                break;
            case 3:
                cambiarCorreoUrbanizacion();
                break;
            case 4:
                //cambiarAdminUrbanizacion();
                break;
            case 5:
                cambiarConstructora();
                break;
            default:
                System.out.println("No se encuentra entre las opciones"
                        + "de menu\regresando al menu principal...");
                break;
        }
        System.out.println(this);
    }*/
//metodos para actualizar la informacion. privados
    /*private void cambiarNombreUrbanizacion(){
        System.out.println("Ingrese nuevo nombre: ");
        
        String nombre= entra.nextLine().toUpperCase();
        setNombre(nombre);
    }
    private void cambiarEtapaUrbanizacion(){
        System.out.println("Ingrese el numero de la etapa: ");
        String etapa= entra.next();
        setEtapa(etapa);
    }
    private void cambiarCorreoUrbanizacion(){
        System.out.println("Ingrese nuevo correo: ");
        String correo= entra.next();
        setEmail(correo);
    }*/
    public void cambiarAdminUrbanizacion(String cedula){
        if(cedula!=null){
            Persona objetivo=encontrarPersona(cedula);
            if(objetivo instanceof Colaborador ){
                Colaborador empleado=(Colaborador)objetivo;
                empleado.setTipoempleado(Empleo.ADMINISTRADOR);
                setAdministrador(empleado);
            }else{
                setAdministrador(null);
            }
        }else{
            setAdministrador(null);
        }
        
        
    }

    public void crearPermiso(String cedula){
        /*System.out.println("Ingrese la cedula del residente que va a crear"
                + " el permiso: ");
        entra.nextLine();
        String cedula= entra.nextLine();*/
        Persona per =encontrarPersona(cedula);
        
        if( per !=null &&  per instanceof Residente){
            Residente creador= (Residente) per;
            //creador.crearPermiso();
            //creador.mostrarPermisoResidente();
        }else{
            System.out.println("Error, numero de cedula incorrecto...");
        }
    }

/*Se consulta la manzana y villa de un residente. se muestran sus 
    permisos*/
    private void consultarPermisosMZVilla(){
       //creador.ubicarMzVilla();
    }

//FIN PERMISOS de entrada.
    
//6. Revision de Ingreso.menuRevision Usa entradaUrbanizacion.
    public void menuRevision(){
        //entradaUrbanizacion();
    }
//revisa el codigo del permiso y la cedula del residente
    public Permiso entradaUrbanizacion( int codigo,String cedulaV){
        for(Persona c:personas){
            if (c instanceof Colaborador){
                Colaborador guardia= (Colaborador)c;
                if(guardia.getTipoempleado().equals(Empleo.GUARDIA)){
                    Permiso verificado=guardia.verificarPermisos(codigo, cedulaV);
                    return verificado;
                }
            }
            
        }
        return null;
    }
//FIN REVISION ENTRADA.
    
//7.reportes menuReportes Usa "Reportes()".
    public void menuReportes(){
       //reportes();
}
//De un residente muestra todos los reportes creados
    public ArrayList<Permiso> reportes(String cedula){

        Persona p=encontrarPersona(cedula);
        if(p!=null && p instanceof Residente){
            Residente residenteObjetivo= (Residente)p;
            return residenteObjetivo.getPermisos();
        }
        return null;
    }
        

/*Metodo que encuentra un objeto Persona usando su cedula
y lo retorna */
    public Persona encontrarPersona(String cedula){
        int posicionPersona=0;
        boolean confirmacion= false;
        for(Persona persona :personas){
            if(persona.getCedula().equals(cedula)){
                posicionPersona=personas.indexOf(persona);
                confirmacion=true;
                break;
            }
        }
        return (confirmacion)? personas.get(posicionPersona):null;
    }
/*Recorre la lista de Personas y hace un downcasting para diferenciar
entre residentes, visitantes y colaboradores*/
    private void mostrarPersonas(int opcion){
        for(Persona persona: personas){
             if(opcion==2 && persona instanceof Residente ){
                 
                System.out.println(persona);
                System.out.println("");
            }else if(opcion==3 && persona instanceof Visitante){
                System.out.println(persona);
                System.out.println("");
            }else if(opcion==4 && persona instanceof Colaborador){

                System.out.println(persona);
                System.out.println("");
            }
        }
    }


    @Override
    public String toString() {
        if (administrador == null ){
            return 
                   "Urbanizacion" + "\nNombre= " + nombre + "\netapa= " + etapa + 
            "\nemail= " + email + "\nconstructora= " + constructora + 
                    "\nNo hay administrador " ;
        }else{
            return "Urbanizacion:" + "\nNombre= " + nombre + "\netapa= " + etapa + 
            "\nemail= " + email + "\nconstructora= " + constructora + 
            "\nADMINISTRADOR= "+ administrador;
        }
        
    }
    
}
