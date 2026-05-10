package org.juegoboxeo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.juegoboxeo.dto.BoxeadorDTO;
import org.juegoboxeo.exceptions.NoSeEncuentranRegistrosException;
import org.juegoboxeo.service.EscogerContrincanteService;
import org.juegoboxeo.utils.Navegador;

public class EscogerContrincanteController {
    
    // UI
    @FXML
    private Label nombre1, nombre2, nombre3, nombre4, nombre5;
    @FXML
    private Label anyoNacimiento1, anyoNacimiento2, anyoNacimiento3, anyoNacimiento4, anyoNacimiento5;
    @FXML
    private Text descripcion1, descripcion2, descripcion3, descripcion4, descripcion5;
    @FXML
    private Label vida1, vida2, vida3, vida4, vida5;
    @FXML
    private Label stamina1, stamina2, stamina3, stamina4, stamina5;
    @FXML
    private Label victoriasYderrotas1, victoriasYderrotas2, victoriasYderrotas3, victoriasYderrotas4, victoriasYderrotas5;
    
    private int idPartida;
    private int idBoxeadorJugador;
    
    private final EscogerContrincanteService service = new EscogerContrincanteService();
    
    public void settearDatos(int idPartida, int idBoxeadorJugador) {
        this.idPartida = idPartida;
        this.idBoxeadorJugador = idBoxeadorJugador;
        
        cargarTodo();
    }
    
    private void cargarTodo() {
        cargar(1, nombre1, anyoNacimiento1, descripcion1, vida1, stamina1, victoriasYderrotas1);
        cargar(2, nombre2, anyoNacimiento2, descripcion2, vida2, stamina2, victoriasYderrotas2);
        cargar(3, nombre3, anyoNacimiento3, descripcion3, vida3, stamina3, victoriasYderrotas3);
        cargar(4, nombre4, anyoNacimiento4, descripcion4, vida4, stamina4, victoriasYderrotas4);
        cargar(5, nombre5, anyoNacimiento5, descripcion5, vida5, stamina5, victoriasYderrotas5);
    }
    
    private void cargar(int idBoxeador, Label nombre, Label anyoNacimiento, Text descripcion, Label vida, Label stamina, Label stats) {
        try {
            BoxeadorDTO c = service.obtenerContrincante(idBoxeador, idPartida);
            
            nombre.setText(c.nombre());
            anyoNacimiento.setText("Año: " + c.anyoNacimiento());
            descripcion.setText(c.descripcion());
            vida.setText("Vida: " + c.vida());
            stamina.setText("Stamina: " + c.stamina());
            stats.setText("V: " + c.victorias() + " - D: " + c.derrotas());
        } catch (NoSeEncuentranRegistrosException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    @FXML
    public void jugarContraContrincante1(ActionEvent event) {irAPelear(event, 1);}
    
    @FXML
    public void jugarContraContrincante2(ActionEvent event) {irAPelear(event, 2);}
    
    @FXML
    public void jugarContraContrincante3(ActionEvent event) {irAPelear(event, 3);}
    
    @FXML
    public void jugarContraContrincante4(ActionEvent event) {irAPelear(event, 4);}
    
    @FXML
    public void jugarContraContrincante5(ActionEvent event) {irAPelear(event, 5);}
    
    private void irAPelear(ActionEvent event, int idBoxeadorContrincante) {
        
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        
        FXMLLoader loader = Navegador.cambiarEscena(stage, "/views/pelear.fxml", "/styles/pelear.css");
        
        PelearController controller = loader.getController();
        controller.settearDatos(this.idBoxeadorJugador, idBoxeadorContrincante, this.idPartida);
    }
}