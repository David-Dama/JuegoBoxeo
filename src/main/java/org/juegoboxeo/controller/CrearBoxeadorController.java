package org.juegoboxeo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.juegoboxeo.model.Boxeador;
import org.juegoboxeo.service.CrearBoxeadorService;

import java.util.Objects;

public class CrearBoxeadorController {
    
    @FXML
    private TextField nombreField;
    
    @FXML
    private TextField anyoField;
    
    @FXML
    private TextField descripcionField;
    
    @FXML
    private TextField vidaField;
    
    @FXML
    private TextField staminaField;
    
    private int idPartida;
    
    private final CrearBoxeadorService service = new CrearBoxeadorService();
    
    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }
    
    @FXML
    private void crearBoxeador(ActionEvent event) {
        
        try {
            String nombre = nombreField.getText();
            String descripcion = descripcionField.getText();
            
            if (nombre.isBlank()) {
                mostrarAlerta("Error", "El nombre no puede estar vacío");
                return;
            }
            
            if (descripcion.isBlank()) {
                mostrarAlerta("Error", "La descripción no puede estar vacía");
                return;
            }
            
            int anyo;
            int vida;
            int stamina;
            
            try {
                anyo = Integer.parseInt(anyoField.getText());
            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "El año de nacimiento debe ser un número válido");
                return;
            }
            
            try {
                vida = Integer.parseInt(vidaField.getText());
            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "La vida debe ser un número válido");
                return;
            }
            
            try {
                stamina = Integer.parseInt(staminaField.getText());
            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "La stamina debe ser un número válido");
                return;
            }
            
            if (vida <= 0 || stamina <= 0) {
                mostrarAlerta("Error", "Vida y stamina deben ser mayores que 0");
                return;
            }
            
            Boxeador b = new Boxeador();
            b.setNombre(nombre);
            b.setAnyoNacimiento(anyo);
            b.setDescripcion(descripcion);
            b.setVidaMax(vida);
            b.setStaminaMax(stamina);
            
            int idBoxeadorJugadorCreado = service.crearJugadorEnPartida(b, idPartida);
            
            irASeleccionDeContrincante(event,  idBoxeadorJugadorCreado);
            
        } catch (Exception e) {
            mostrarAlerta("Error inesperado", e.getMessage());
        }
    }
    
    private void irASeleccionDeContrincante(ActionEvent event, int idBoxeadorJugadorCreado) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/escogerContrincante.fxml"));
            
            Scene scene = new Scene(loader.load(), 1750, 950);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/escogerContrincante.css")).toExternalForm());
            
            EscogerContrincanteController controller = loader.getController();
            controller.settearDatos(this.idPartida, idBoxeadorJugadorCreado);
            
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            
            stage.setScene(scene);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}