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
    
    
}
