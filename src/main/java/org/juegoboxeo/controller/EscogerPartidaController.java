package org.juegoboxeo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.juegoboxeo.dto.PartidaInfoDTO;
import org.juegoboxeo.exceptions.NoSeEncuentranRegistrosException;
import org.juegoboxeo.service.EscogerPartidaService;
import org.juegoboxeo.utils.Navegador;

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
            
            nombre.setText(info.nombre());
            victorias.setText(String.valueOf(info.victorias()));
            derrotas.setText(String.valueOf(info.derrotas()));
        } catch (NoSeEncuentranRegistrosException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void mostrarVacio(Label nombre, Label victorias, Label derrotas) {
        nombre.setText("Crear partida");
        victorias.setText("-");
        derrotas.setText("-");
    }
    
    //Seleccionar partida
    @FXML
    private void seleccionarPartida1(ActionEvent event) {
        entrarEnPartida(event, 1);
    }
    
    @FXML
    private void seleccionarPartida2(ActionEvent event) {
        entrarEnPartida(event, 2);
    }
    
    @FXML
    private void seleccionarPartida3(ActionEvent event) {
        entrarEnPartida(event, 3);
    }
    
    private void entrarEnPartida(ActionEvent event, int idPartida) {
        
        boolean hayJugador = service.existeJugadorEnPartida(idPartida);
        
        if (!hayJugador) {
            abrirCrearBoxeador(event, idPartida);
        } else {
            abrirEscogerContrincante(event, idPartida, service.idBoxeadorDePartida(idPartida));
        }
    }
    
    private void abrirCrearBoxeador(ActionEvent event, int idPartida) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        
        FXMLLoader loader = Navegador.cambiarEscena(stage, "/views/crearBoxeador.fxml", "/views/crearBoxeador.css");
        
        CrearBoxeadorController controller = loader.getController();
        
        controller.setIdPartida(idPartida);
    }
    
    private void abrirEscogerContrincante(ActionEvent event, int idPartida, int idBoxeadorJugador) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        
        FXMLLoader loader = Navegador.cambiarEscena(stage, "/views/escogerContrincante.fxml", "/styles/escogerContrincante.css");
        
        EscogerContrincanteController controller = loader.getController();
        
        controller.settearDatos(idPartida, idBoxeadorJugador);
    }
    
    //Eliminar partidas
    @FXML
    private void eliminarPartida1() {
        confirmarEliminarPartida(1);
    }
    
    @FXML
    private void eliminarPartida2() {
        confirmarEliminarPartida(2);
    }
    
    @FXML
    private void eliminarPartida3() {
        confirmarEliminarPartida(3);
    }
    
    @FXML
    private void confirmarEliminarPartida(int idPartida) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("Eliminar partida");
        alert.setContentText("¿Estás seguro de que quieres eliminar esta partida?");
        
        ButtonType resultado = alert.showAndWait().orElse(ButtonType.CANCEL);
        
        if (resultado == ButtonType.OK) {
            service.eliminarJugador(idPartida);
            cargarPartidas();
        }
    }
}