package org.juegoboxeo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/paginaInicio.fxml"));
        
        Scene scene = new Scene(loader.load(), 1750, 950);
        
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/paginaInicio.css")).toExternalForm());
        
        Font.loadFont(getClass().getResourceAsStream("/fonts/Poppins-Regular.ttf"), 10);
        
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Logo.png"))));
        
        stage.setScene(scene);
        stage.setTitle("Juego de Boxeo");
        stage.setResizable(false);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}