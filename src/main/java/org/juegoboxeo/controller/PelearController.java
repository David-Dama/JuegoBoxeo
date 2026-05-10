package org.juegoboxeo.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.juegoboxeo.dto.ResultadoTurnoDTO;
import org.juegoboxeo.exceptions.NoSeEncuentranRegistrosException;
import org.juegoboxeo.model.Boxeador;
import org.juegoboxeo.model.Golpe;
import org.juegoboxeo.model.InformacionGolpe;
import org.juegoboxeo.service.PelearService;
import org.juegoboxeo.utils.Navegador;

public class PelearController {
    
    @FXML
    private Label infoJabContrincante;
    
    @FXML
    private Label infoJabJugador;
    
    @FXML
    private Label infoHookContrincante;
    
    @FXML
    private Label infoHookJugador;
    
    @FXML
    private Label infoCrossContrincante;
    
    @FXML
    private Label infoCrossJugador;
    
    @FXML
    private Label infoUpperContrincante;
    
    @FXML
    private Label infoUpperJugador;
    
    @FXML
    private Label vidaJugador;
    
    @FXML
    private Label vidaContrincante;
    
    @FXML
    private Label staminaJugador;
    
    @FXML
    private Label staminaContrincante;
    
    @FXML
    private ImageView imagenJugador;
    
    @FXML
    private ImageView imagenContrincante;
    
    @FXML
    private Label resultadoJugadorLabel;
    
    @FXML
    private Label resultadoContrincanteLabel;
    
    private int idBoxeadorJugador;
    private int idBoxeadorContrincante;
    private int idPartida;
    
    private final PelearService service = new PelearService();
    
    public void settearDatos(int idBoxeadorJugador, int idBoxeadorCotrincante, int idPartida) {
        
        this.idBoxeadorJugador = idBoxeadorJugador;
        this.idBoxeadorContrincante = idBoxeadorCotrincante;
        this.idPartida = idPartida;
        
        try {
            
            service.iniciarPelea(this.idBoxeadorJugador, this.idBoxeadorContrincante, this.idPartida);
            
            actualizarPantalla();
            cargarInfoGolpes(service.getJugador(), true);
            cargarInfoGolpes(service.getContrincante(), false);
            
        } catch (NoSeEncuentranRegistrosException e) {
            e.printStackTrace();
        }
    }
    
    private void cargarInfoGolpes(Boxeador b, boolean esJugador) {
        
        String jab = formatear(b.getGolpes().get(Golpe.JAB));
        String cross = formatear(b.getGolpes().get(Golpe.CROSS));
        String hook = formatear(b.getGolpes().get(Golpe.HOOK));
        String upper = formatear(b.getGolpes().get(Golpe.UPPER));
        
        if (esJugador) {
            infoJabJugador.setText(jab);
            infoCrossJugador.setText(cross);
            infoHookJugador.setText(hook);
            infoUpperJugador.setText(upper);
        } else {
            infoJabContrincante.setText(jab);
            infoCrossContrincante.setText(cross);
            infoHookContrincante.setText(hook);
            infoUpperContrincante.setText(upper);
        }
    }
    
    private String formatear(InformacionGolpe g) {
        
        if (g == null) {
            return "-";
        }
        
        return g.getDanyo() + " - " + g.getCosteStamina() + " - " + g.getPrecision() + "% - " + g.getProbabilidadCritico() + "%";
    }
    
    @FXML
    private void usarJab() {
        atacar(Golpe.JAB);
    }
    
    @FXML
    private void usarCross() {
        atacar(Golpe.CROSS);
    }
    
    @FXML
    private void usarHook() {
        atacar(Golpe.HOOK);
    }
    
    @FXML
    private void usarUpper() {
        atacar(Golpe.UPPER);
    }
    
    private void atacar(Golpe golpe) {
        
        ResultadoTurnoDTO resultado = service.turnoJugador(golpe);
        
        resultadoJugadorLabel.setText(resultado.getJugador());
        resultadoContrincanteLabel.setText(resultado.getContrincante());
        
        actualizarPantalla();
        
        comprobarGanador();
    }
    
    private void actualizarPantalla() {
        
        vidaJugador.setText("Vida = " + service.getJugador().getVidaActual());
        
        vidaContrincante.setText("Vida = " + service.getContrincante().getVidaActual());
        
        staminaJugador.setText("Stamina = " + service.getJugador().getStaminaActual());
        
        staminaContrincante.setText("Stamina = " + service.getContrincante().getStaminaActual());
        
        imagenJugador.setImage(new Image("/images/Fondo.png"));
        
        imagenContrincante.setImage(new Image("/images/Fondo.png"));
    }
    
    private void comprobarGanador() {
        
        try {
            if (!service.getJugador().estaVivo()) {
                
                service.finalizarPelea();
                
                mostrarMensaje("Has perdido");
                
                volverAEscogerContrincante();
                
            }
            
            if (!service.getContrincante().estaVivo()) {
                
                service.finalizarPelea();
                
                mostrarMensaje("Has ganado");
                
                volverAEscogerContrincante();
            }
        } catch (NoSeEncuentranRegistrosException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void volverAEscogerContrincante() {
        
        try {
            
            Stage stage = (Stage) vidaContrincante.getScene().getWindow();
            
            FXMLLoader loader = Navegador.cambiarEscena(stage, "/views/escogerContrincante.fxml", "/styles/escogerContrincante.css");
            
            EscogerContrincanteController controller = loader.getController();
            
            controller.settearDatos(idPartida, idBoxeadorJugador);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void mostrarMensaje(String mensaje) {
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        alert.setHeaderText(null);
        
        alert.setContentText(mensaje);
        
        alert.showAndWait();
    }
}
