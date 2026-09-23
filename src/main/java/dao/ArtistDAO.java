package dao;

import model.Artist;
import db.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Artist Data Access Object (DAO) class
 * 
 * @author Nguyen Sy Nguyen
 */
public class ArtistDAO extends DBContext {
    // create

    // read
    public List<Artist> getList() {
        
        // Bien chua ket qua
        List<Artist> result = new ArrayList<>();

        try {

            // Cau truy van
            String sql = "select * from Artist";

            // Chuan bi cau lenh
            PreparedStatement statement = this.getConnection().prepareStatement(sql);

            // Thuc thi cau lenh
            // executeQuery() danh cho truy van SELECT
            // executeUpdate() danh cho truy van INSERT/UPDATE/DELETE
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1); // Co the truyen so thu tu cua cot tinh tu trai sang phai (tinh tu 1), hoac ten cua cot
                String name = rs.getString(2);
                Artist artist = new Artist(id, name);
                result.add(artist);
            }
            
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(ArtistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }

    // update
    // delete
}
