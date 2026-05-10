package org.juegoboxeo.dto;

public class ResultadoTurnoDTO {
    
    private final String jugador;
    private final String contrincante;
    
    public ResultadoTurnoDTO(String jugador, String contrincante) {
        this.jugador = jugador;
        this.contrincante = contrincante;
    }
    
    public String getJugador() {
        return jugador;
    }
    
    public String getContrincante() {
        return contrincante;
    }
}