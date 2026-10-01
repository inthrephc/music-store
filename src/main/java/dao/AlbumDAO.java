/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Album;
import model.Artist;

/**
 *
 * @author Nguyen Sy Nguyen
 */
public class AlbumDAO extends DBContext {
    
    
    public List<Album> getList() {
        List<Album> result = new ArrayList<>();

        try {
            String sql = "select al.AlbumId, al.Title, al.ArtistId, ar.Name from Album al join Artist ar on al.ArtistId = ar.ArtistId order by al.AlbumId asc";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1); 
                String title = rs.getString(2);
                int artistId = rs.getInt(3);
                String artistName = rs.getString(4);
                Album album = new Album(id, title, new Artist(artistId, artistName));
                result.add(album);
            }

            return result;
        } catch (SQLException ex) {
            Logger.getLogger(ArtistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public int create(String title, int artistId) {
        try {
            String sql = "insert into Album (Title, ArtistId) values (?, ?);";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, title);
            statement.setInt(2, artistId);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public Album getById(int id) {
        try {
            String sql = "select * from Album where AlbumId = ?";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Album(rs.getInt(1), rs.getString(2), new Artist(rs.getInt(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int update(int id, String title, int artistId) {
        try {
            String sql = "update Album set Title = ?, ArtistId = ? where AlbumId = ?";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, title);
            statement.setInt(2, id);
            statement.setInt(3, artistId);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    
}
