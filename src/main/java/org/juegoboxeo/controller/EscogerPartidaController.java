package org.juegoboxeo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.juegoboxeo.dto.PartidaInfoDTO;
import org.juegoboxeo.exceptions.NoSeEncuentranRegistrosException;
import org.juegoboxeo.service.EscogerPartidaService;

import java.util.Objects;

public class EscogerPartidaController {
    
    // UI
    @FXML
    private Label nombre1, nombre2, nombre3;
    @FXML
    private Label victorias1, victorias2, victorias3;
    @FXML
    private Label derrotas1, derrotas2, derrotas3;
    
    // SERVICE
    private final EscogerPartidaService service = new EscogerPartidaService();
    
    @FXML
    public void initialize() {
        cargarPartidas();
    }
    
    private void cargarPartidas() {
        cargar(1, nombre1, victorias1, derrotas1);
        cargar(2, nombre2, victorias2, derrotas2);
        cargar(3, nombre3, victorias3, derrotas3);
    }
    
    private void cargar(int idPartida, Label nombre, Label victorias, Label derrotas) {
        try {
            PartidaInfoDTO info = service.obtenerInfoPartida(idPartida);
            
            if (info == null) {
                mostrarVacio(nombre, victorias, derrotas);
                return;
            }
            
            nombre.setText(info.getNombre());
            victorias.setText(String.valueOf(info.getVictorias()));
            derrotas.setText(String.valueOf(info.getDerrotas()));
        } catch (NoSeEncuentranRegistrosException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void mostrarVacio(Label nombre, Label victorias, Label derrotas) {
        nombre.setText("Crear partida");
        victorias.setText("-");
        derrotas.setText("-");
    }
    
    @FXML
    private void seleccionarPartida1(ActionEvent event) {
        escogerContrincante(event, 1);
    }
    
    @FXML
    private void seleccionarPartida2(ActionEvent event) {
        escogerContrincante(event, 2);
    }
    
    @FXML
    private void seleccionarPartida3(ActionEvent event) {
        escogerContrincante(event, 3);
    }
    
    private void escogerContrincante(ActionEvent event, int idPartida) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/escogerContrincante.fxml"));
            
            Scene scene = new Scene(loader.load(), 1750, 950);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/escogerContrincante.css")).toExternalForm());
            
            EscogerContrincanteController controller = loader.getController();
            controller.setIdPartida(idPartida);
            
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            
            stage.setScene(scene);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}