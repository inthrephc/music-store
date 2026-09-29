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
    public int create(String artistName) {
        try {
//            Tuyet doi khong duoc chen chuoi nhu the nay vi nguy co bi tan cong SQL Injection:
//            String sql = "insert into Artist (name) values (" + artistName + ");";

//            Cach lam dung
            String sql = "insert into Artist (name) values (?);";

            PreparedStatement statement = this.getConnection().prepareStatement(sql);

//                   .setString(param1 la so thu tu cua dau "?", param2 la gia tri se duoc dat vao dau "?" -
//                                                                        la gia tri can dien cua cau query)
//                   .setString() ví cột (name) trong database là kiểu NVARCHAR
            statement.setString(1, artistName);

            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArtistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

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
    public Artist getById(int id) {
        try {
            Artist artist = new Artist();
            String sql = "select * from Artist where ArtistId = (?);";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, String.valueOf(id));

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String artistName = result.getString(2);
                artist.setId(id);
                artist.setName(artistName);
                return artist;
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(ArtistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int update(int id, String name) {
        try {
            String sql = "update Artist set name = ? where ArtistId = ?;";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, String.valueOf(id));
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArtistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    // delete
}
