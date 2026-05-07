package org.juegoboxeo.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Objects;

public class PaginaInicioController {
    @FXML
    private Button botonEmpezar;
    
    @FXML
    private void irAEscogerPartida() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/escogerPartida.fxml"));
            
            Scene scene = new Scene(loader.load(), 1750, 950);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/escogerPartida.css")).toExternalForm());
            
            Stage stage = (Stage) botonEmpezar.getScene().getWindow();
            stage.setScene(scene);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
