package org.juegoboxeo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.juegoboxeo.model.Boxeador;
import org.juegoboxeo.service.CrearBoxeadorService;
import org.juegoboxeo.utils.Navegador;

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
                vida = Integer.parseInt(vidaField.getText());
                stamina = Integer.parseInt(staminaField.getText());
            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "El año de nacimiento, la vida y la stamina debe ser un número válido");
                return;
            }
            
            if (vida <= 0 || stamina <= 0 || stamina > 300 || vida > 300) {
                mostrarAlerta("Error", "Vida y stamina deben ser mayores que 0 y menores que 300");
                return;
            }
            
            Boxeador b = new Boxeador();
            b.setNombre(nombre);
            b.setAnyoNacimiento(anyo);
            b.setDescripcion(descripcion);
            b.setVidaMax(vida);
            b.setStaminaMax(stamina);
            
            int idBoxeadorJugadorCreado = service.crearJugadorEnPartida(b, idPartida);
            
            irASeleccionDeContrincante(event, idBoxeadorJugadorCreado);
            
        } catch (Exception e) {
            mostrarAlerta("Error inesperado", e.getMessage());
        }
    }
    
    private void irASeleccionDeContrincante(ActionEvent event, int idBoxeadorJugadorCreado) {
        
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        
        FXMLLoader loader = Navegador.cambiarEscena(stage, "/views/escogerContrincante.fxml", "/styles/escogerContrincante.css");
        
        EscogerContrincanteController controller = loader.getController();
        controller.settearDatos(this.idPartida, idBoxeadorJugadorCreado);
    }
    
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    public void volverAEscogerPartida(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        
        FXMLLoader loader = Navegador.cambiarEscena(stage, "/views/escogerPartida.fxml", "/styles/escogerPartida.css");
        
        EscogerPartidaController controller = loader.getController();
    }
}