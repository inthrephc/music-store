/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.DBContext;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author ADMIN
 */
public class UserDAO extends DBContext {

    public User login(String username, String rawPassword) {
        try {
            String sql = "select * from users where username = ? and password = ?";
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, this.hashMd5(rawPassword));
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), null);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private String hashMd5(String raw) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] mess = md.digest(raw.getBytes());
           
            StringBuilder sb = new StringBuilder();
            for (byte b: mess) {
                sb.append(String.format("%02x", b));
            }
           
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

}
