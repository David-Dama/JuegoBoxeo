package org.juegoboxeo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.juegoboxeo.dao.BoxeadorDAO;
import org.juegoboxeo.dao.PartidaBoxeadorDAO;
import org.juegoboxeo.dao.impl.BoxeadorDAOImpl;
import org.juegoboxeo.dao.impl.PartidaBoxeadorDAOImpl;
import org.juegoboxeo.exceptions.NoSeEncuentranRegistrosException;
import org.juegoboxeo.model.Boxeador;

import java.util.Objects;

public class EscogerContrincanteController {
    
    // CONTRINCANTE 1
    @FXML
    private Label nombre1;
    @FXML
    private Label anyoNacimiento1;
    @FXML
    private Text descripcion1;
    @FXML
    private Label vida1;
    @FXML
    private Label stamina1;
    @FXML
    private Label victoriasYderrotas1;
    
    // CONTRINCANTE 2
    @FXML
    private Label nombre2;
    @FXML
    private Label anyoNacimiento2;
    @FXML
    private Text descripcion2;
    @FXML
    private Label vida2;
    @FXML
    private Label stamina2;
    @FXML
    private Label victoriasYderrotas2;
    
    // CONTRINCANTE 3
    @FXML
    private Label nombre3;
    @FXML
    private Label anyoNacimiento3;
    @FXML
    private Text descripcion3;
    @FXML
    private Label vida3;
    @FXML
    private Label stamina3;
    @FXML
    private Label victoriasYderrotas3;
    
    // CONTRINCANTE 4
    @FXML
    private Label nombre4;
    @FXML
    private Label anyoNacimiento4;
    @FXML
    private Text descripcion4;
    @FXML
    private Label vida4;
    @FXML
    private Label stamina4;
    @FXML
    private Label victoriasYderrotas4;
    
    // CONTRINCANTE 5
    @FXML
    private Label nombre5;
    @FXML
    private Label anyoNacimiento5;
    @FXML
    private Text descripcion5;
    @FXML
    private Label vida5;
    @FXML
    private Label stamina5;
    @FXML
    private Label victoriasYderrotas5;
    
    //Id de la partida
    private int idPartida;
    
    private final PartidaBoxeadorDAO partidaBoxeadorDAO = new PartidaBoxeadorDAOImpl();
    private final BoxeadorDAO boxeadorDAO = new BoxeadorDAOImpl();
    
    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
        
        // CONTRINCANTE 1
        cargarContrincantes(1, nombre1, anyoNacimiento1, descripcion1, vida1, stamina1, victoriasYderrotas1);
        
        // CONTRINCANTE 2
        cargarContrincantes(2, nombre2, anyoNacimiento2, descripcion2, vida2, stamina2, victoriasYderrotas2);
        
        // CONTRINCANTE 3
        cargarContrincantes(3, nombre3, anyoNacimiento3, descripcion3, vida3, stamina3,  victoriasYderrotas3);
        
        // CONTRINCANTE 4
        cargarContrincantes(4, nombre4, anyoNacimiento4, descripcion4, vida4, stamina4, victoriasYderrotas4);
        
        // CONTRINCANTE 5
        cargarContrincantes(5, nombre5, anyoNacimiento5, descripcion5, vida5, stamina5, victoriasYderrotas5);
    }
    
    private void cargarContrincantes(int idBoxeador, Label nombre, Label anyoNacimiento, Text descripcion, Label vida, Label stamina, Label victoriasYderrotas) {
        try {
            Boxeador b = boxeadorDAO.cargarBoxeador(idBoxeador, this.idPartida);
            
            //Ponemos la información en los labels
            nombre.setText(b.getNombre());
            anyoNacimiento.setText("Año: " + String.valueOf(b.getAnyoNacimiento()));
            descripcion.setText(b.getDescripcion());
            vida.setText("Vida: " + String.valueOf(b.getVida()));
            stamina.setText("Stamina: " + String.valueOf(b.getStamina()));
            victoriasYderrotas.setText("V: " + String.valueOf(partidaBoxeadorDAO.obtenerVictorias(idBoxeador, this.idPartida)) + " - D: " + String.valueOf(partidaBoxeadorDAO.obtenerDerrotas(idBoxeador, this.idPartida)));
            
        } catch (NoSeEncuentranRegistrosException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //Cambiar a la pagina de lucha
    private void pelearContraContrincanteEscogido(ActionEvent event, int idPartida) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Pelear.fxml"));
            
            Scene scene = new Scene(loader.load(), 1750, 950);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/pelear.css")).toExternalForm());
            
            EscogerContrincanteController controller = loader.getController();
            controller.setIdPartida(idPartida);
            
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            
            stage.setScene(scene);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}