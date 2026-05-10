package org.juegoboxeo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.juegoboxeo.utils.Navegador;

public class PaginaInicioController {
    @FXML
    private Button botonEmpezar;
    
    @FXML
    private void irAEscogerPartida() {
        
        Stage stage = (Stage) botonEmpezar.getScene().getWindow();
        
        Navegador.cambiarEscena(stage, "/views/escogerPartida.fxml", "/styles/escogerPartida.css");
    }
}
