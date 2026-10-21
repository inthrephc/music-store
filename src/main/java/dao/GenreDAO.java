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
import model.Genre;

/**
 *
 * @author ADMIN
 */
public class GenreDAO extends DBContext {

    public Genre getById(int id) {
        try {
            String sql = "select * from Genre where GenreId = (?);";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, String.valueOf(id));

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Genre(id, result.getString(2));
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(ArtistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Genre> getList() {
        List<Genre> result = new ArrayList<>();
        try {
            String sql = "select * from Genre";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Genre genre = new Genre(id, name);
                result.add(genre);
            }
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(ArtistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int create(String name) {
        try {
            String sql = "insert into Genre (name) values (?);";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, name);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArtistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int update(int id, String name) {
        try {
            String sql = "update Genre set Name = ? where GenreId = ?;";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, id);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArtistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int delete(int id) {
        try {
            String sql = "delete from Genre where GenreId = ?;";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ArtistDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int getTotalPages(int pageSize) {
        int totalRows = getTotalRows();
        return (int) Math.ceil((double) totalRows / pageSize);
    }

    public int getTotalRows() {
        try {
            String sql = "select count(*) from Genre;";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.getLogger(ArtistDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return -1;
    }

    public List<Genre> getList(int currentPage, int pageSize) {
        List<Genre> result = new ArrayList<>();

        try {

            // Cau truy van
            String sql = "SELECT * FROM Genre ORDER BY GenreId ASC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            int offset = (currentPage - 1) * pageSize;

            // Chuan bi cau lenh
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, offset);
            statement.setInt(2, pageSize);

            // Thuc thi cau lenh
            // executeQuery() danh cho truy van SELECT
            // executeUpdate() danh cho truy van INSERT/UPDATE/DELETE
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1); // Co the truyen so thu tu cua cot tinh tu trai sang phai (tinh tu 1), hoac ten cua cot
                String name = rs.getString(2);
                Genre genre = new Genre(id, name);
                result.add(genre);
            }

            return result;
        } catch (SQLException ex) {
            Logger.getLogger(GenreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
}
