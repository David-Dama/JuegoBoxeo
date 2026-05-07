package org.juegoboxeo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.juegoboxeo.dao.BoxeadorDAO;
import org.juegoboxeo.dao.PartidaBoxeadorDAO;
import org.juegoboxeo.dao.impl.BoxeadorDAOImpl;
import org.juegoboxeo.dao.impl.PartidaBoxeadorDAOImpl;
import org.juegoboxeo.exceptions.NoSeEncuentranRegistrosException;
import org.juegoboxeo.model.PartidaBoxeador;

import java.util.Objects;

public class EscogerPartidaController {
    
    // Partida 1
    @FXML
    private Label nombre1;
    @FXML
    private Label victorias1;
    @FXML
    private Label derrotas1;
    @FXML
    private Button boton1;
    
    // Partida 2
    @FXML
    private Label nombre2;
    @FXML
    private Label victorias2;
    @FXML
    private Label derrotas2;
    @FXML
    private Button boton2;
    
    // Partida 3
    @FXML
    private Label nombre3;
    @FXML
    private Label victorias3;
    @FXML
    private Label derrotas3;
    @FXML
    private Button boton3;
    
    //DAOs
    private final PartidaBoxeadorDAO partidaBoxeadorDAO = new PartidaBoxeadorDAOImpl();
    private final BoxeadorDAO boxeadorDAO = new BoxeadorDAOImpl();
    
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
    
    @FXML
    public void initialize() {
        
        cargarPartida(1, nombre1, victorias1, derrotas1);
        cargarPartida(2, nombre2, victorias2, derrotas2);
        cargarPartida(3, nombre3, victorias3, derrotas3);
    }
    
    private void cargarPartida(int idPartida, Label nombre, Label victorias, Label derrotas) {
        
        try {
            
            Integer idBoxeador = partidaBoxeadorDAO.idBoxeadorDePartida(idPartida);
            
            if (idBoxeador == null) {
                mostrarVacio(nombre, victorias, derrotas);
                return;
            }
            
            PartidaBoxeador pb = partidaBoxeadorDAO.cargarPartidaBoxeador(idBoxeador, idPartida);
            
            String nombreBoxeador = boxeadorDAO.nombreBoxeador(idBoxeador, idPartida);
            
            nombre.setText(nombreBoxeador);
            victorias.setText(String.valueOf(pb.getVictorias()));
            derrotas.setText(String.valueOf(pb.getDerrotas()));
            
        } catch (NoSeEncuentranRegistrosException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void mostrarVacio(Label nombre, Label victorias, Label derrotas) {
        nombre.setText("Crear partida");
        victorias.setText("-");
        derrotas.setText("-");
    }
}