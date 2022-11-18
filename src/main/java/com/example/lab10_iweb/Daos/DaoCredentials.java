package com.example.lab10_iweb.Daos;
import com.example.lab10_iweb.Beans.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoCredentials extends DaoBase{

    public Credentials validarUsuarioPassword(String username, String password) {

        Credentials credentials = null;

        String sql = "SELECT * FROM credentials WHERE nro_documento = ? AND hashedPassword = sha2(?,256)";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery();) {
                if(rs.next()){
                    credentials = new Credentials();
                    credentials.setNumeroDocumento(rs.getString(1));
                    credentials.setTipoUsuario(rs.getInt(2));

                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return credentials;
    }


}