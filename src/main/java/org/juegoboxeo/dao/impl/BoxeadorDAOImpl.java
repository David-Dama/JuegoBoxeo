package org.juegoboxeo.dao.impl;

import org.juegoboxeo.config.DatabaseConnection;
import org.juegoboxeo.dao.BoxeadorDAO;
import org.juegoboxeo.exceptions.NoSeEncuentranRegistrosException;
import org.juegoboxeo.model.Boxeador;
import org.juegoboxeo.model.Golpe;
import org.juegoboxeo.model.InformacionGolpe;

import java.sql.*;
import java.util.Map;

public class BoxeadorDAOImpl implements BoxeadorDAO {
    //Create
    @Override
    public int insertarBoxeador(Boxeador b) {
        String sql = "INSERT INTO boxeador (nombre, anyo_nacimiento, descripcion, vida, stamina) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstm.setString(1, b.getNombre());
            pstm.setInt(2, b.getAnyoNacimiento());
            pstm.setString(3, b.getDescripcion());
            pstm.setInt(4, b.getVidaMax());
            pstm.setInt(5, b.getStaminaMax());
            
            pstm.executeUpdate();
            
            try (ResultSet rs = pstm.getGeneratedKeys()) {
                
                if (rs.next()) {
                    return rs.getInt(1);
                }
                
                throw new SQLException("No se generó ID");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar boxeador", e);
        }
    }
    
    //Read
    @Override
    public Boxeador cargarBoxeador(int idBoxeador, int idPartida, Map <Golpe, InformacionGolpe> golpeInformacionGolpeMap) throws NoSeEncuentranRegistrosException {
        String sql = "SELECT b.nombre, b.anyo_nacimiento, b.descripcion, b.vida, b.stamina FROM boxeador b INNER JOIN partida_boxeador pb ON b.id = pb.id_boxeador WHERE b.id = ? AND pb.id_partida = ?";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setInt(1, idBoxeador);
            pstm.setInt(2, idPartida);
            
            try (ResultSet res = pstm.executeQuery()) {
                
                if (res.next()) {
                    //Guardamos en variables la información de la query
                    String nombre = res.getString("nombre");
                    int anyoNacimiento = res.getInt("anyo_nacimiento");
                    String descripcion = res.getString("descripcion");
                    int vidaMax = res.getInt("vida");
                    int staminaMax = res.getInt("stamina");
                    
                    return new Boxeador(idBoxeador, nombre, anyoNacimiento, descripcion, vidaMax, staminaMax, golpeInformacionGolpeMap);
                } else {
                    throw new NoSeEncuentranRegistrosException("Boxeador no encontrado");
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar boxeador", e);
        }
    }
    
    @Override
    public String nombreBoxeador(int idBoxeador, int idPartida) throws NoSeEncuentranRegistrosException {
        String sql = "SELECT nombre FROM boxeador INNER JOIN partida_boxeador pb ON boxeador.id = pb.id_boxeador WHERE pb.id_boxeador = ? AND pb.id_partida = ?";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setInt(1, idBoxeador);
            pstm.setInt(2, idPartida);
            
            try (ResultSet res = pstm.executeQuery()) {
                if (res.next()) {
                    return res.getString("nombre");
                } else {
                    throw new NoSeEncuentranRegistrosException("Boxeador no encontrado");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar boxeador", e);
        }
    }
    
    //Update
    @Override
    public void actualizarStatsBoxeador(int idBoxeador, int idPartida) throws NoSeEncuentranRegistrosException {
        String sql = "UPDATE boxeador b INNER JOIN partida_boxeador pb ON b.id = pb.id_boxeador SET b.vida = ?, b.stamina = ? WHERE b.id = ? AND pb.id_partida = ?";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setInt(1, 150);
            pstm.setInt(2, 150);
            pstm.setInt(3, idBoxeador);
            pstm.setInt(4, idPartida);
            
            int filasAfectadas = pstm.executeUpdate();
            
            if (filasAfectadas == 0) {
                throw new NoSeEncuentranRegistrosException("No se actualizó ningún boxeador");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //Delete
    @Override
    public void eliminarBoxeador(int idBoxeador) {
        
        String sql = "DELETE FROM boxeador WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idBoxeador);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar boxeador", e);
        }
    }
}
    
