package danlevil.proyctpoofxmlgluon;
import Modelo.Permiso;
import Modelo.Sistema;
import Modelo.Urbanizacion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * JavaFX App
 */
public class App extends Application  {
    static Urbanizacion urb= Sistema.inicializarSistema();
    
    public static String imgspath = "Imagenes/";
    public static String filespath = "documentos/";
    
    public static Stage ventanaPrincipal;
    private static Scene scene;

    
    public void init(){
        System.out.println("Incializando recursos...");
        Permiso.permisos=Permiso.desencriptarPermisos("Permisos.ser");
        System.out.println(Permiso.permisos);
    }
    @Override
    public void start(Stage stage) throws IOException {
        ventanaPrincipal=stage;
        Parent root= loadFXML("MenuPrincipalUrbanizacion");
        
        FileInputStream input = new FileInputStream(imgspath + "logo1.png");
        Image icon = new Image(input);
        stage.getIcons().add(icon);
        

        
        scene = new Scene(root);
        ventanaPrincipal.setScene(scene);
        ventanaPrincipal.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
       
    }

    private void actualizarArchivoCSV(ArrayList <Permiso>lista){
        try(PrintWriter pw = new PrintWriter(new FileWriter(App.filespath+"Registro de Permisos.csv"))){
        pw.println("ESTADO,CODIGO,CEDULA RESIDENTE,RESIDENTE,CEDULA VISITANTE,VISITANTE,FECHA CREACION,FECHA ENTRADA,HORA ENTRADA,DURACION VISITA,OBSERVACIONES,");
            for(Permiso permiso:lista){
            
        
            if(permiso.getObservacion()==null){
                pw.println(permiso.getEstado().toString()+","+permiso.getCodigoUnico()+","
                    +permiso.getCreador().getCedula().toString()+","+permiso.getCreador().getNombre()
                    +","+permiso.getVisita().getCedula().toString()+","+permiso.getVisita().getNombre()
                    +","+permiso.getFechaHoraCreacion()+","+permiso.getFechaIngreso()
                    +","+permiso.getHoraIngreso()+","+permiso.getDuracionVisita()
                    );
            }else{
                pw.println(permiso.getEstado().toString()+","+permiso.getCodigoUnico()+","
                    +permiso.getCreador().getCedula().toString()+","+permiso.getCreador().getNombre()
                    +","+permiso.getVisita().getCedula().toString()+","+permiso.getVisita().getNombre()
                    +","+permiso.getFechaHoraCreacion()+","+permiso.getFechaIngreso()
                    +","+permiso.getHoraIngreso()+","+permiso.getDuracionVisita()+","+permiso.getObservacion()
                    );
            }
            }
            
        }catch(FileNotFoundException fnot){
            System.out.println("archivo no encontrado");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            System.out.println("archivo no ioe");
        }
    }
    
    
    public void stop() throws FileNotFoundException{
        
        
        
        Permiso.serializarPermisos();
        ArrayList<Permiso>actualizados=Permiso.desencriptarPermisos("Permisos.ser");
        actualizarArchivoCSV(actualizados);
        System.out.println("...cerrando recursos");
    }
    
}