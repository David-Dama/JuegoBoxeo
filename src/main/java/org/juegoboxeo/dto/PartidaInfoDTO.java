package org.juegoboxeo.dto;

public class PartidaInfoDTO {
    private String nombre;
    private int victorias;
    private int derrotas;
    
    public PartidaInfoDTO(String nombre, int victorias, int derrotas) {
        this.nombre = nombre;
        this.victorias = victorias;
        this.derrotas = derrotas;
    }
    
    public String getNombre() {return nombre;}
    
    public int getVictorias() {return victorias;}
    
    public int getDerrotas() {return derrotas;}
}
