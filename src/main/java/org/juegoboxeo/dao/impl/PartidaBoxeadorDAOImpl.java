package org.juegoboxeo.dao.impl;

import org.juegoboxeo.config.DatabaseConnection;
import org.juegoboxeo.dao.PartidaBoxeadorDAO;
import org.juegoboxeo.exceptions.NoSeEncuentranRegistrosException;
import org.juegoboxeo.model.PartidaBoxeador;
import org.juegoboxeo.model.Rol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PartidaBoxeadorDAOImpl implements PartidaBoxeadorDAO {
    
    //Create
    @Override
    public void insertarPartidaBoxeador(PartidaBoxeador pb) {
        
        String sql = "INSERT INTO partida_boxeador (id_partida, id_boxeador, rol, victorias, derrotas) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, pb.getIdPartida());
            ps.setInt(2, pb.getIdBoxeador());
            ps.setString(3, pb.getRol().name());
            ps.setInt(4, pb.getVictorias());
            ps.setInt(5, pb.getDerrotas());
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar partida_boxeador", e);
        }
    }
    
    //Read
    @Override
    public PartidaBoxeador cargarPartidaBoxeador(int idBoxeador, int idPartida) throws NoSeEncuentranRegistrosException {
        
        String sql = "SELECT id_partida, id_boxeador, rol, victorias, derrotas FROM partida_boxeador WHERE id_boxeador = ? AND id_partida = ? ";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setInt(1, idBoxeador);
            pstm.setInt(2, idPartida);
            
            try (ResultSet res = pstm.executeQuery()) {
                
                if (res.next()) {
                    
                    int idPart = res.getInt("id_partida");
                    int idBox = res.getInt("id_boxeador");
                    Rol rol = Rol.valueOf(res.getString("rol"));
                    int victorias = res.getInt("victorias");
                    int derrotas = res.getInt("derrotas");
                    
                    return new PartidaBoxeador(idPart, idBox, rol, victorias, derrotas);
                    
                } else {
                    throw new NoSeEncuentranRegistrosException("PartidaBoxeador no encontrado");
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al cargar PartidaBoxeador", e);
        }
    }
    
    @Override
    public boolean existeJugador(int idPartida) {
        String sql = "SELECT 1 FROM partida_boxeador WHERE id_partida = ? AND rol = 'JUGADOR' LIMIT 1";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idPartida);
            
            try (ResultSet rs = ps.executeQuery()) {
                
                return rs.next();
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al comprobar si existe jugador", e);
        }
    }
    
    @Override
    public Integer idBoxeadorDePartida(int idPartida) {
        
        String sql = "SELECT id_boxeador FROM partida_boxeador WHERE id_partida = ? AND rol = 'JUGADOR'";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idPartida);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_boxeador");
                } else {
                    return null;
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public int obtenerVictorias(int idBoxeador, int idPartida) throws NoSeEncuentranRegistrosException {
        
        String sql = "SELECT victorias FROM partida_boxeador WHERE id_boxeador = ? AND id_partida = ?";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setInt(1, idBoxeador);
            pstm.setInt(2, idPartida);
            
            try (ResultSet res = pstm.executeQuery()) {
                if (res.next()) {
                    return res.getInt("victorias");
                } else {
                    throw new NoSeEncuentranRegistrosException("No se ha encontrado un boxeador con dicho id para dicha partida.");
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener victorias", e);
        }
    }
    
    @Override
    public int obtenerDerrotas(int idBoxeador, int idPartida) throws NoSeEncuentranRegistrosException {
        
        String sql = "SELECT derrotas FROM partida_boxeador WHERE id_boxeador = ? AND id_partida = ?";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setInt(1, idBoxeador);
            pstm.setInt(2, idPartida);
            
            try (ResultSet res = pstm.executeQuery()) {
                if (res.next()) {
                    return res.getInt("derrotas");
                } else {
                    throw new NoSeEncuentranRegistrosException("No se ha encontrado un boxeador con dicho id para dicha partida.");
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener derrotas", e);
        }
    }
    
    
    //Update
    @Override
    public void sumarVictorias(int idBoxeador, int idPartida) throws NoSeEncuentranRegistrosException {
        
        String sql = "UPDATE partida_boxeador SET victorias = victorias + 1 WHERE id_boxeador = ? AND id_partida = ?";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setInt(1, idBoxeador);
            pstm.setInt(2, idPartida);
            
            int filas = pstm.executeUpdate();
            
            if (filas == 0) {
                throw new NoSeEncuentranRegistrosException("No se encontró el registro para actualizar");
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al sumar victorias", e);
        }
    }
    
    @Override
    public void sumarDerrotas(int idBoxeador, int idPartida) throws NoSeEncuentranRegistrosException {
        
        String sql = "UPDATE partida_boxeador SET derrotas = derrotas + 1 WHERE id_boxeador = ? AND id_partida = ?";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setInt(1, idBoxeador);
            pstm.setInt(2, idPartida);
            
            int filas = pstm.executeUpdate();
            
            if (filas == 0) {
                throw new NoSeEncuentranRegistrosException("No se encontró el registro para actualizar");
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al sumar derrotas", e);
        }
    }
    
    @Override
    public void setVictorias(int valor, int idBoxeador, int idPartida) throws NoSeEncuentranRegistrosException {
        
        String sql = "UPDATE partida_boxeador SET victorias = ? WHERE id_boxeador = ? AND id_partida = ?";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setInt(1, valor);
            pstm.setInt(2, idBoxeador);
            pstm.setInt(3, idPartida);
            
            int filas = pstm.executeUpdate();
            
            if (filas == 0) {
                throw new NoSeEncuentranRegistrosException("No se encontró el registro");
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar victorias", e);
        }
    }
    
    @Override
    public void setDerrotas(int valor, int idBoxeador, int idPartida) throws NoSeEncuentranRegistrosException {
        
        String sql = "UPDATE partida_boxeador SET derrotas = ? WHERE id_boxeador = ? AND id_partida = ?";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setInt(1, valor);
            pstm.setInt(2, idBoxeador);
            pstm.setInt(3, idPartida);
            
            int filas = pstm.executeUpdate();
            
            if (filas == 0) {
                throw new NoSeEncuentranRegistrosException("No se encontró el registro");
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar derrotas", e);
        }
    }
    
    //Delete
    @Override
    public void eliminarJugador(int idPartida, int idBoxeador) {
        
        String sql = "DELETE FROM partida_boxeador WHERE id_partida = ? AND id_boxeador = ? AND rol = 'JUGADOR'";
        
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idPartida);
            ps.setInt(2, idBoxeador);
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar jugador de partida", e);
        }
    }
}
