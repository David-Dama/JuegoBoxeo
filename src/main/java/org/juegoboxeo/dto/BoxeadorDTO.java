package org.juegoboxeo.dto;

public class BoxeadorDTO {
    
    private String nombre;
    private int anyoNacimiento;
    private String descripcion;
    private int vida;
    private int stamina;
    private int victorias;
    private int derrotas;
    
    public BoxeadorDTO(String nombre, int anyoNacimiento, String descripcion, int vida, int stamina, int victorias, int derrotas) {
        this.nombre = nombre;
        this.anyoNacimiento = anyoNacimiento;
        this.descripcion = descripcion;
        this.vida = vida;
        this.stamina = stamina;
        this.victorias = victorias;
        this.derrotas = derrotas;
    }
    
    public String getNombre() {return nombre;}
    
    public int getAnyoNacimiento() {return anyoNacimiento;}
    
    public String getDescripcion() {return descripcion;}
    
    public int getVida() {return vida;}
    
    public int getStamina() {return stamina;}
    
    public int getVictorias() {return victorias;}
    
    public int getDerrotas() {return derrotas;}
}