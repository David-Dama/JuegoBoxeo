package org.juegoboxeo.dto;

public class GolpeDTO {
    
    private String nombre;
    private int dano;
    private int precision;
    private int critico;
    private int costeStamina;
    
    public GolpeDTO(String nombre, int dano, int precision, int critico, int costeStamina) {
        this.nombre = nombre;
        this.dano = dano;
        this.precision = precision;
        this.critico = critico;
        this.costeStamina = costeStamina;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public int getDano() {
        return dano;
    }
    
    public int getPrecision() {
        return precision;
    }
    
    public int getCritico() {
        return critico;
    }
    
    public int getCosteStamina() {
        return costeStamina;
    }
}
