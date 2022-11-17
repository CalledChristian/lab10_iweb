package com.example.lab10_iweb.Daos;
import com.example.lab10_iweb.Beans.*;
import com.example.lab10_iweb.Dtos.cantidadContratosDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoContrato extends DaoBase{

    public ArrayList<Contrato> listarContratos() {
        ArrayList<Contrato> listaDeContratos = new ArrayList<>();

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery( "SELECT * FROM lab9.partidos;");){

            while (rs.next()) {
                Contrato contrato = new Contrato();

                contrato.setNumeroDeContrato(rs.getString(1));
                contrato.setIdCliente(rs.getString(2));
                contrato.setDivisa(rs.getString(3));
                contrato.setEstado(Integer.parseInt(rs.getString(4)));
                contrato.setMesesEnEseEstado(Integer.parseInt(rs.getString(5)));

                listaDeContratos.add(contrato);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaDeContratos;
    }

    public ArrayList<cantidadContratosDto> mostrarCantidadContratos(){
        ArrayList<cantidadContratosDto> lista = new ArrayList<>();

        String sql= "";

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()){
                cantidadContratosDto cantidad = new cantidadContratosDto();
                cantidad.setContratosEstado0(Integer.parseInt(rs.getString(1)));
                cantidad.setContratosEstado1(Integer.parseInt(rs.getString(2)));
                cantidad.setContratosEstado2(Integer.parseInt(rs.getString(3)));
                lista.add(cantidad);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;

    }


}
