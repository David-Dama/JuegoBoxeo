package org.juegoboxeo.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Navegador {
    
    public static FXMLLoader cambiarEscena(Stage stage, String fxml, String css) {
        
        try {
            
            FXMLLoader loader = new FXMLLoader(Navegador.class.getResource(fxml));
            
            Scene scene = new Scene(loader.load(), 1750, 950);
            
            if (css != null) {
                scene.getStylesheets().add(Objects.requireNonNull(Navegador.class.getResource(css)).toExternalForm());
            }
            
            stage.setScene(scene);
            
            return loader;
            
        } catch (Exception e) {
            throw new RuntimeException("Error al cambiar de escena", e);
        }
    }
}