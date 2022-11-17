package com.example.lab10_iweb.Daos;

import com.example.lab10_iweb.Beans.Cliente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoCliente extends DaoBase{

    public ArrayList<Cliente> obtenerListaDeClientes() {

        ArrayList<Cliente> listaClientes = new ArrayList<>();

        String sql = "select * from jm_client_bii ";

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNumeroDocumento(rs.getString(1));
                cliente.setNombreCliente(rs.getString(2));
                cliente.setEdad(rs.getString(3));
                cliente.setTipoCliente(rs.getString(4));
                cliente.setTipoDocumento(rs.getString(5));

                listaClientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaClientes;
    }
}
