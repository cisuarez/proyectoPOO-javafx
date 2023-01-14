package danlevil.proyctpoofxmlgluon;

import Modelo.Sistema;
import Modelo.Urbanizacion;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * JavaFX App
 */
public class App extends Application {
    static Urbanizacion urb= Sistema.inicializarSistema();
    
    public static Stage ventanaPrincipal;
    private static Scene scene;


    @Override
    public void start(Stage stage) throws IOException {
        ventanaPrincipal=stage;
        Parent root= loadFXML("MenuPrincipalUrbanizacion");
        
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
        //Sistema.menuSistema();
    }

}